package ro.uaic.info.appointmentschedulingservice.beans;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class AvailableTimes {

    private Long doctorId;
    private LocalDate date;
    private List<LocalTime> startTimes;
}
