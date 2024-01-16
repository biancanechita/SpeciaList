package ro.uaic.info.userauthenticationserviceapi;

import lombok.Data;

import java.lang.reflect.Field;

@Data
public class SensitivePersonalData {

    private String firstName;
    private String lastName;
    private String cnp;
    private Long userId;

    public boolean validateNonNullFields() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if (fieldName.equals("userId")) {
                continue; // Skip the userId field
            }
            try {
                Object value = field.get(this);
                if (value == null) {
                    return false;
                } else {
                    if (value instanceof String && ((String) value).isEmpty()) {
                        return false;
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return true;
    }
}
