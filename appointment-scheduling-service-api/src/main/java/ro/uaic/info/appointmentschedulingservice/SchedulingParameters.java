package ro.uaic.info.appointmentschedulingservice;

import lombok.Data;

import java.lang.reflect.Field;
import java.time.LocalDate;

@Data
public class SchedulingParameters {

    private Long specializationId;
    private Long cityId;
    private Long doctorId;
    private LocalDate dateAfter;

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