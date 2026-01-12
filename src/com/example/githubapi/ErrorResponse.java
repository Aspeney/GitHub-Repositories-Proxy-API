package com.example.githubapi;

public record ErrorResponse(
        int status,
        String message
) {}