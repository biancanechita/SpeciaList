package ro.uaic.info.userauthenticationservice.config;

import ro.uaic.info.userauthenticationservice.entities.User;

import java.util.Map;

public interface JwtGeneratorInterface {

    String generateJwtToken(User user);
}
