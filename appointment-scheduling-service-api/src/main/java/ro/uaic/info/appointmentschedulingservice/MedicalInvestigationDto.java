package ro.uaic.info.appointmentschedulingservice;

import lombok.Data;

import java.lang.reflect.Field;

@Data
public class MedicalInvestigationDto {

    private Long id;
    private String investigation;
    private Long specializationId;

    public boolean validateNonNullFields() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(this);
                if (value == null) {
                    return false;
                } else if (value instanceof String && ((String) value).isEmpty()) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return true;
    }
}