package ro.uaic.info.userauthenticationserviceapi;

import lombok.Data;

import java.lang.reflect.Field;

@Data
public class UserCredentials {

    private String email;
    private String password;

    public boolean validateNonNullFields() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.get(this) == null || field.get(this).toString().isEmpty()) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return true;
    }
}
