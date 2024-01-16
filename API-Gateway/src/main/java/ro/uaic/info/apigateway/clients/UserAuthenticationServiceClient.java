package ro.uaic.info.apigateway.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ro.uaic.info.userauthenticationserviceapi.UserCredentials;

import java.util.Map;

@Component
public class UserAuthenticationServiceClient extends RestClient {

    @Value("${userauthenticationservice.url}")
    private String url;

    public String login(UserCredentials userCredentials) {
        ResponseEntity<String> response = this.create("http://localhost:8083/users")
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/login")
                        .build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer "
                        + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsInVzZXJJZCI6MCwicm9sZSI6IkdBVEVXQVkifQ.I3_PxafH0k6yRuq0ARq6-34psRIszuLExcGsozXajHQ")
                .bodyValue(userCredentials)
                .retrieve()
                .toEntity(String.class)
                .block();

        return response != null ? response.getBody() : null;
    }

    public Long signup(Map<String, String> map) {
        ResponseEntity<Long> response = this.create("http://localhost:8083/users")
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/signup")
                        .build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer "
                        + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsInVzZXJJZCI6MCwicm9sZSI6IkdBVEVXQVkifQ.I3_PxafH0k6yRuq0ARq6-34psRIszuLExcGsozXajHQ")
                .bodyValue(map)
                .retrieve()
                .toEntity(Long.class)
                .block();

        return response != null ? response.getBody() : null;
    }
}
