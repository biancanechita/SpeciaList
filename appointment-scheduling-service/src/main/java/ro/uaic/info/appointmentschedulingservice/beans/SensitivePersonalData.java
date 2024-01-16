package ro.uaic.info.appointmentschedulingservice.beans;

import lombok.Data;

@Data
public class SensitivePersonalData {

    private String firstName;
    private String lastName;
    private String cnp;
    private Long userId;
}