package com.example.githubapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubRepository(
        String name,
        boolean fork,
        Owner owner,
        List<GithubBranch> branches
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Owner(String login) {}
}
