package ro.uaic.info.apigateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ro.uaic.info.userauthenticationserviceapi.SensitivePersonalData;
import ro.uaic.info.userauthenticationserviceapi.UserRegistrationData;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ApiGatewayApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenUserRegistrationData_then5xxServerError() throws Exception {
        UserRegistrationData userRegistrationData = new UserRegistrationData();
        userRegistrationData.setEmail("email");
        userRegistrationData.setPassword("password");
        userRegistrationData.setPersonalData(new SensitivePersonalData());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user-authentication/signup")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsInVzZXJJZCI6MSwicm9sZSI6IlBBVElFTlQifQ.LHppN5NPGGvQEVQi1YPwwoaLJb0YdoatADWTJaWUbl8")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userRegistrationData)))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @Test
    void givenUserRegistrationData_thenResponseOK() throws Exception {
        UserRegistrationData userRegistrationData = new UserRegistrationData();
        userRegistrationData.setEmail("email");
        userRegistrationData.setPassword("password");

        SensitivePersonalData personalData = new SensitivePersonalData();
        personalData.setFirstName("Jane");
        personalData.setLastName("Doe");
        personalData.setCnp("cnp");

        userRegistrationData.setPersonalData(personalData);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user-authentication/signup")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsInVzZXJJZCI6MSwicm9sZSI6IlBBVElFTlQifQ.LHppN5NPGGvQEVQi1YPwwoaLJb0YdoatADWTJaWUbl8")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userRegistrationData)))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }
}
