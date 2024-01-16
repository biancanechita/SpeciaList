package ro.uaic.info.appointmentschedulingservice;

import lombok.Data;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class AvailableTimes {

    private Long doctorId;
    private LocalDate date;
    private List<LocalTime> startTimes;

    public boolean validateNonNullFields() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.get(this) == null) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return true;
    }
}