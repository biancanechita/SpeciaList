package ro.uaic.info.apigateway.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ro.uaic.info.appointmentschedulingservice.AppointmentDto;
import ro.uaic.info.appointmentschedulingservice.AppointmentRequest;
import ro.uaic.info.appointmentschedulingservice.AvailableTimes;
import ro.uaic.info.appointmentschedulingservice.SchedulingParameters;
import ro.uaic.info.userauthenticationserviceapi.SensitivePersonalData;

import java.util.List;

@Component
public class AppointmentSchedulingServiceClient extends RestClient {

    @Value("${appointmentschedulingservice.url}")
    private String url;

    public Long createPatient(SensitivePersonalData personalData) {
        ResponseEntity<Long> response = this.create("http://localhost:8081/patients")
                .build()
                .post()
                .header(HttpHeaders.AUTHORIZATION, "Bearer "
                        + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsInVzZXJJZCI6MCwicm9sZSI6IkdBVEVXQVkifQ.I3_PxafH0k6yRuq0ARq6-34psRIszuLExcGsozXajHQ")
                .bodyValue(personalData)
                .retrieve()
                .toEntity(Long.class)
                .block();

        return response != null ? response.getBody() : null;
    }

    public List<AvailableTimes> fetchAvailableTimes(SchedulingParameters schedulingParameters) {
        ResponseEntity<List> response = this.create("http://localhost:8081/appointments/fetch-available-times")
                .build()
                .post()
                .header(HttpHeaders.AUTHORIZATION, "Bearer "
                        + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsInVzZXJJZCI6MCwicm9sZSI6IkdBVEVXQVkifQ.I3_PxafH0k6yRuq0ARq6-34psRIszuLExcGsozXajHQ")
                .bodyValue(schedulingParameters)
                .retrieve()
                .toEntity(List.class)
                .block();

        return response != null ? response.getBody() : null;
    }

    public List<AppointmentDto> findAllByUserId(Long patientId) {
        ResponseEntity<List> response = this.create("http://localhost:8081/appointments/" + patientId)
                .build()
                .get()
                .header(HttpHeaders.AUTHORIZATION, "Bearer "
                        + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsInVzZXJJZCI6MCwicm9sZSI6IkdBVEVXQVkifQ.I3_PxafH0k6yRuq0ARq6-34psRIszuLExcGsozXajHQ")
                .retrieve()
                .toEntity(List.class)
                .block();

        return response != null ? response.getBody() : null;
    }

    public AppointmentDto save(AppointmentDto appointmentDto) {
        ResponseEntity<AppointmentDto> response = this.create("http://localhost:8081/appointments/restricted")
                .build()
                .post()
                .header(HttpHeaders.AUTHORIZATION, "Bearer "
                        + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsInVzZXJJZCI6MCwicm9sZSI6IkdBVEVXQVkifQ.I3_PxafH0k6yRuq0ARq6-34psRIszuLExcGsozXajHQ")
                .bodyValue(appointmentDto)
                .retrieve()
                .toEntity(AppointmentDto.class)
                .block();

        return response != null ? response.getBody() : null;
    }

    public void deleteById(Long id) {
        this.create("http://localhost:8081/appointments/" + id)
                .build()
                .delete()
                .header(HttpHeaders.AUTHORIZATION, "Bearer "
                        + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsInVzZXJJZCI6MCwicm9sZSI6IkdBVEVXQVkifQ.I3_PxafH0k6yRuq0ARq6-34psRIszuLExcGsozXajHQ")
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public Long findPatientByUserId(String userId) {
        ResponseEntity<Long> response = this.create("http://localhost:8081/patients/" + userId)
                .build()
                .get()
                .header(HttpHeaders.AUTHORIZATION, "Bearer "
                        + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsInVzZXJJZCI6MCwicm9sZSI6IkdBVEVXQVkifQ.I3_PxafH0k6yRuq0ARq6-34psRIszuLExcGsozXajHQ")
                .retrieve()
                .toEntity(Long.class)
                .block();

        return response != null ? response.getBody() : null;
    }

    public void scheduleVisitorMode(AppointmentRequest appointmentRequest) {
        System.out.println(appointmentRequest);
        this.create("http://localhost:8081/appointments/req")
                .build()
                .post()
                .header(HttpHeaders.AUTHORIZATION, "Bearer "
                        + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsInVzZXJJZCI6MCwicm9sZSI6IkdBVEVXQVkifQ.I3_PxafH0k6yRuq0ARq6-34psRIszuLExcGsozXajHQ")
                .bodyValue(appointmentRequest)
                .retrieve()
                .toEntity(AppointmentDto.class)
                .block();
    }
}
