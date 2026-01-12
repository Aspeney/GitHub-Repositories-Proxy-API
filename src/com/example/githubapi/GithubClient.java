package com.example.githubapi;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Component
class GithubClient {

    private static final String BASE_URL = "https://api.github.com";
    private final RestTemplate restTemplate = new RestTemplate();

    List<GithubRepository> getRepositories(String username) {
        try {
            return Arrays.asList(
                    restTemplate.getForObject(
                            BASE_URL + "/users/" + username + "/repos",
                            GithubRepository[].class
                    )
            );
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "GitHub user '" + username + "' does not exist"
            );
        }
    }

    List<GithubBranch> getBranches(String username, String repo) {
        return Arrays.asList(
                restTemplate.getForObject(
                        BASE_URL + "/repos/" + username + "/" + repo + "/branches",
                        GithubBranch[].class
                )
        );
    }
}
