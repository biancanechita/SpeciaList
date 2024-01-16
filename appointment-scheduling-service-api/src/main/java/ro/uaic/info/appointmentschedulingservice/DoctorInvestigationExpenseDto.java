package ro.uaic.info.appointmentschedulingservice;

import lombok.Data;

import java.lang.reflect.Field;
import java.math.BigDecimal;

@Data
public class DoctorInvestigationExpenseDto {

    private Long id;
    private Long investigationId;
    private Long doctorId;
    private BigDecimal price;

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