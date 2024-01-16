package ro.uaic.info.appointmentschedulingservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ro.uaic.info.appointmentschedulingservice.services.AppointmentService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AppointmentSchedulingServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveAppointment() throws Exception {
        AppointmentDto appointmentDto = appointmentService.findById(6L);

        if (appointmentDto != null) {
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/appointments/restricted")
                            .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsInVzZXJJZCI6MCwicm9sZSI6IkdBVEVXQVkifQ.I3_PxafH0k6yRuq0ARq6-34psRIszuLExcGsozXajHQ")
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(appointmentDto)))
                    .andExpect(MockMvcResultMatchers.status().is5xxServerError());
        }
    }

}
