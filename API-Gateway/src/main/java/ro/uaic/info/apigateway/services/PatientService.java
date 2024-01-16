package ro.uaic.info.apigateway.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.info.apigateway.clients.AppointmentSchedulingServiceClient;
import ro.uaic.info.userauthenticationserviceapi.SensitivePersonalData;

@RequiredArgsConstructor
@Service
public class PatientService {

    private final AppointmentSchedulingServiceClient client;

    public Long createPatient(SensitivePersonalData personalData) {
        return client.createPatient(personalData);
    }
}
