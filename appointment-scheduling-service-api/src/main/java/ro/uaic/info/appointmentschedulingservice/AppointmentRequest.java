package ro.uaic.info.appointmentschedulingservice;

import lombok.Data;

@Data
public class AppointmentRequest {

    private Long doctorId;
    private Long medicalInvestigationId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
