package ro.uaic.info.apigateway.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ro.uaic.info.apigateway.QueryOptions;
import ro.uaic.info.appointmentschedulingservice.DoctorInvestigationExpenseDto;
import ro.uaic.info.appointmentschedulingservice.MedicalInvestigationDto;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MedicalInventoryServiceClient<T> extends AbstractRestClient<T> {

    @Value("${medicalinventoryservice.url}")
    private String url = "http://localhost:8080";

    @Override
    public List<T> filterAndSortList(String uri, QueryOptions queryOptions) {
        ResponseEntity<List> response = this.create(url)
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(uri)
                        .path("/filter-and-sort-list")
                        .build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer "
                        + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsInVzZXJJZCI6MCwicm9sZSI6IkdBVEVXQVkifQ.I3_PxafH0k6yRuq0ARq6-34psRIszuLExcGsozXajHQ")
                .bodyValue(queryOptions)
                .retrieve()
                .toEntity(List.class)
                .block();

        return response != null ? response.getBody() : null;
    }

    @Override
    public T getById(String uri, Long id) {
        ResponseEntity<T> response = this.create(url)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(uri)
                        .path("/")
                        .path(String.valueOf(id))
                        .build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer "
                        + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsInVzZXJJZCI6MCwicm9sZSI6IkdBVEVXQVkifQ.I3_PxafH0k6yRuq0ARq6-34psRIszuLExcGsozXajHQ")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<T>() {
                })
                .block();

        return response != null ? response.getBody() : null;
    }

    @Override
    public T create(String uri, T t) {
        ResponseEntity<T> response = this.create(url)
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(uri)
                        .build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer "
                        + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsInVzZXJJZCI6MCwicm9sZSI6IkdBVEVXQVkifQ.I3_PxafH0k6yRuq0ARq6-34psRIszuLExcGsozXajHQ")
                .bodyValue(t)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<T>() {
                })
                .block();

        return response != null ? response.getBody() : null;
    }

    public List<MedicalInvestigationDto> findAllByDoctorId(String uri, Long id) {
        ResponseEntity<List> response = this.create(url)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(uri)
                        .path("/find-all-by-doctor-id/")
                        .path(String.valueOf(id))
                        .build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer "
                        + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsInVzZXJJZCI6MCwicm9sZSI6IkdBVEVXQVkifQ.I3_PxafH0k6yRuq0ARq6-34psRIszuLExcGsozXajHQ")
                .retrieve()
                .toEntity(List.class)
                .block();

        return response != null ? response.getBody() : null;
    }


    public List<DoctorInvestigationExpenseDto> findAllByInvestigationId(String uri, Long investigationId) {
        ResponseEntity<List> response = this.create(url)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(uri)
                        .path("/")
                        .path(String.valueOf(investigationId))
                        .build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer "
                        + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsInVzZXJJZCI6MCwicm9sZSI6IkdBVEVXQVkifQ.I3_PxafH0k6yRuq0ARq6-34psRIszuLExcGsozXajHQ")
                .retrieve()
                .toEntity(List.class)
                .block();

        return response != null ? response.getBody() : null;
    }

    public String getFullName(String uri, Long id) {
        ResponseEntity<String> response = this.create(url)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(uri)
                        .path("/")
                        .path(String.valueOf(id))
                        .path("/get-full-name")
                        .build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer "
                        + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsInVzZXJJZCI6MCwicm9sZSI6IkdBVEVXQVkifQ.I3_PxafH0k6yRuq0ARq6-34psRIszuLExcGsozXajHQ")
                .retrieve()
                .toEntity(String.class)
                .block();

        return response != null ? response.getBody() : null;
    }
}
