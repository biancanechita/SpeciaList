package ro.uaic.info.userauthenticationservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.info.userauthenticationservice.config.JwtGeneratorInterface;
import ro.uaic.info.userauthenticationservice.entities.User;
import ro.uaic.info.userauthenticationservice.exceptions.UserException;
import ro.uaic.info.userauthenticationservice.repositories.UserRepository;
import ro.uaic.info.userauthenticationserviceapi.UserCredentials;

import static ro.uaic.info.userauthenticationservice.entities.UserRole.PATIENT;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtGeneratorInterface jwtGenerator;

    public Long save(UserCredentials userCredentials) throws UserException {
        if (!userCredentials.validateNonNullFields()) {
            throw new UserException("The [field name] field cannot be empty or null.");
        }

        return userRepository
                .save(new User(userCredentials.getEmail(), userCredentials.getPassword(), PATIENT))
                .getId();
    }

    public String login(UserCredentials credentials) {
        return userRepository.findByEmailAndPassword(credentials.getEmail(), credentials.getPassword())
                .map(jwtGenerator::generateJwtToken)
                .orElse(null);
    }
}
