package com.example.githubapi;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class GithubService {

    private final GithubClient client;

    GithubService(GithubClient client) {
        this.client = client;
    }

    List<GithubRepository> getUserRepositories(String username) {
        return client.getRepositories(username).stream()
                .filter(repo -> !repo.fork())
                .map(repo -> new GithubRepository(
                        repo.name(),
                        repo.fork(),
                        repo.owner(),
                        client.getBranches(username, repo.name())
                ))
                .toList();
    }
}
