package com.example.github;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTest {

    private static WireMockServer wireMock;

    @BeforeAll
    static void start() {
        wireMock = new WireMockServer(8089);
        wireMock.start();
        configureFor(8089);
    }

    @AfterAll
    static void stop() {
        wireMock.stop();
    }

    @Test
    void shouldReturnRepositories(MockMvc mockMvc) throws Exception {
        stubFor(get("/users/test/repos").willReturn(okJson("""
            [{
              "name": "repo",
              "fork": false,
              "owner": { "login": "test" }
            }]
        """)));

        stubFor(get("/repos/test/repo/branches").willReturn(okJson("""
            [{
              "name": "main",
              "commit": { "sha": "abc123" }
            }]
        """)));

        mockMvc.perform(get("/api/github/users/test/repositories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("repo"))
                .andExpect(jsonPath("$[0].branches[0].commit.sha").value("abc123"));
    }
}
