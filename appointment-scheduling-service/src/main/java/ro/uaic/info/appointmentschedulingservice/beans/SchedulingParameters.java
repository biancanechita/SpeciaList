package ro.uaic.info.appointmentschedulingservice.beans;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SchedulingParameters {

    private Long specializationId;
    private Long cityId;
    private Long doctorId;
    private LocalDate dateAfter;
}
