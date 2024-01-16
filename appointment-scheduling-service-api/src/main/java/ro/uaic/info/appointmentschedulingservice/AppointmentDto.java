package ro.uaic.info.appointmentschedulingservice;

import lombok.Data;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentDto {

    private Long id;
    private Long patientId;
    private Long doctorId;
    private Long medicalInvestigationId;
    private LocalDate date;
    private LocalTime startTime;

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