package se.example.Apispring;

import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

import static org.springframework.http.RequestEntity.post;


@RestController
public class GistController {
    @PostMapping("/gists")
    public Object createGist(
            @RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient oauthClient,
            @RequestBody String gistContent
            ) {
        String token = oauthClient.getAccessToken().getTokenValue();

        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.github.com")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();

        return webClient.post()
                .uri("/gists")
                .bodyValue(Map.of(
                        "description", "test",
                        "public", false,
                        "files", Map.of("Test.Gist", Map.of("content", gistContent))
                ))
                .retrieve()
                .toEntity(String.class)
                .block();
    }
}
