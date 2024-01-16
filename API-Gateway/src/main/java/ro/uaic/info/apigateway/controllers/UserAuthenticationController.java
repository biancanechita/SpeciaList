package ro.uaic.info.apigateway.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ro.uaic.info.apigateway.services.UserAuthenticationService;
import ro.uaic.info.userauthenticationserviceapi.UserCredentials;
import ro.uaic.info.userauthenticationserviceapi.UserRegistrationData;

@RequiredArgsConstructor
@RequestMapping("/user-authentication")
@RestController
@CrossOrigin
public class UserAuthenticationController {

    private final UserAuthenticationService service;

    @PostMapping("/login")
    public String login(@RequestBody UserCredentials userCredentials) {
        return service.login(userCredentials);
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody UserRegistrationData registrationData) {
        try {
            service.signup(registrationData);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }
}
