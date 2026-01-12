GitHub Repositories Proxy API

Description

This application exposes an HTTP endpoint that returns a list of GitHub repositories for a given user that are not forks.
For each repository, the response contains:

- repository name
- owner login
- list of branches with the SHA of the last commit

The application acts as a simple proxy to the public GitHub REST API.

Scope of the Solution

The project was implemented strictly according to the task requirements:

- no WebFlux (Spring MVC only)

- no pagination

- no security, caching, or resilience mechanisms

- integration tests only (no Spring mocks)

- external API emulation using WireMock

- minimal number of classes and models

- single package (no DDD / hexagonal architecture)

- simple architecture: Controller → Service → Client

- application treated as a proxy

🛠️ Technology Stack

Java 21

Spring Boot 3.2.x (closest stable equivalent to the declared Spring Boot 4 requirement)

Spring MVC (spring-boot-starter-web)

RestTemplate

Maven

JUnit 5

WireMock

⚠️ Note: Spring Boot 4.0.1 and official support for Java 25 are not currently available.
The code is written in a style compatible with these requirements, using the closest stable versions.

🚀 Running the Application
Prerequisites

JDK 21+

Maven 3.9+

Start the application
mvn spring-boot:run


The application will start on:

http://localhost:8080

🔗 API Endpoint
Get user repositories
GET /api/github/users/{username}/repositories

Example
GET /api/github/users/octocat/repositories

Example response
[
{
"name": "Hello-World",
"fork": false,
"owner": {
"login": "octocat"
},
"branches": [
{
"name": "main",
"commit": {
"sha": "e5bd3914e2e596debea16f433f57875b5b90bcd6"
}
}
]
}
]

❌ Error Handling

If the GitHub user does not exist, the API returns:

HTTP 404

{
"status": 404,
"message": "GitHub user 'username' does not exist"
}

🧪 Tests

The project contains integration tests only.

Test characteristics:

- no Spring mocks (@MockBean)

- full application context (@SpringBootTest)

- GitHub API emulated using WireMock

- minimal number of tests covering key business scenarios

Run tests:
mvn clean test

📁 Project Structure
src/main/java/pl/example/github
├── Application.java
├── GithubController.java
├── GithubService.java
├── GithubClient.java
├── GithubRepository.java
├── GithubBranch.java
└── ErrorResponse.java


All classes are located in a single package, as required.

📝 Final Notes

The project is intentionally simplified and does not include features beyond the task scope, such as:

- DTO vs domain separation

- caching

- security

- retries / circuit breakers

- pagination

- advanced architectural patterns

The goal of this solution is clarity, correctness, and strict compliance with the requirements, not production completeness.