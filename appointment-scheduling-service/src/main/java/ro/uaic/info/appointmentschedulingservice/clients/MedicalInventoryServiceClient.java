package ro.uaic.info.appointmentschedulingservice.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ro.uaic.info.appointmentschedulingservice.beans.QueryOptions;

import java.util.ArrayList;
import java.util.List;

@Component
public class MedicalInventoryServiceClient extends RestClient {

    @Value("${medicalinventoryservice.url}")
    private String baseUrl;

    @Value("${medicalinventoryservice.token}")
    private String token;

    public List<Long> findAllDoctorsBySpecializationIdAndCityId(QueryOptions queryOptions) {
        ResponseEntity<List> responseEntity = this.create(baseUrl)
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/doctors/find-all-by-specialization-id-and-city-id")
                        .build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer "
                        + token)
                .bodyValue(queryOptions)
                .retrieve()
                .toEntity(List.class)
                .block();

        return responseEntity != null ? responseEntity.getBody() : new ArrayList<>();
    }
}
