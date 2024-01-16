package ro.uaic.info.apigateway.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.uaic.info.apigateway.clients.UserAuthenticationServiceClient;
import ro.uaic.info.apigateway.config.UserPrincipal;
import ro.uaic.info.userauthenticationserviceapi.SensitivePersonalData;
import ro.uaic.info.userauthenticationserviceapi.UserCredentials;
import ro.uaic.info.userauthenticationserviceapi.UserRegistrationData;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserAuthenticationService {

    private final UserAuthenticationServiceClient client;

    private final PatientService patientService;

    public String login(UserCredentials userCredentials) {
        return client.login(userCredentials);
    }

    public Long signup(UserRegistrationData registrationData) {
        if (!registrationData.validateNonNullFields()) {
            throw new IllegalArgumentException("The [field name] field cannot be empty or null.");
        }

        Long userId = client.signup(buildUserRegistrationDataMap(registrationData));
        SensitivePersonalData personalData = registrationData.getPersonalData();
        personalData.setUserId(userId);
        return patientService.createPatient(personalData);
    }

    private Map<String, String> buildUserRegistrationDataMap(UserRegistrationData registrationData) {
        Map<String, String> map = new HashMap<>();
        map.put("email", registrationData.getEmail());
        map.put("password", registrationData.getPassword());
        map.put("phoneNumber", registrationData.getPhoneNumber());
        return map;
    }
}
