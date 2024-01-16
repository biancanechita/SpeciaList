package ro.uaic.info.apigateway.clients;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserNotificationServiceClient extends RestClient {

    public void sendingNotificationsWithWhatsApp(Map<String, String> map) {
        this.create("http://localhost:8084/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/notifications")
                        .build())
                .bodyValue(map)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}