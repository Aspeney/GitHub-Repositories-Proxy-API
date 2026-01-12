package com.example.githubapi;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/github/users")
class GithubController {

    private final GithubService service;

    GithubController(GithubService service) {
        this.service = service;
    }

    @GetMapping("/{username}/repositories")
    List<GithubRepository> getRepositories(@PathVariable String username) {
        return service.getUserRepositories(username);
    }
}
