package ro.uaic.info.userauthenticationserviceapi;

import lombok.Data;

import java.lang.reflect.Field;

@Data
public class UserRegistrationData {

    private String email;
    private String password;
    private String phoneNumber;
    private SensitivePersonalData personalData;

    public boolean validateNonNullFields() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(this);
                if (value == null) {
                    return false;
                } else {
                    if ((value instanceof String && ((String) value).isEmpty()) ||
                            !personalData.validateNonNullFields()) {
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
