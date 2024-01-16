package ro.uaic.info.userauthenticationservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ro.uaic.info.userauthenticationservice.exceptions.UserException;
import ro.uaic.info.userauthenticationservice.services.UserService;
import ro.uaic.info.userauthenticationserviceapi.UserCredentials;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public Long registration(@RequestBody UserCredentials userCredentials) {
        try {
            return userService.save(userCredentials);
        } catch (UserException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody UserCredentials credentials) {
        return userService.login(credentials);
    }
}
