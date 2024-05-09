package ru.rendaxx.lab8server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rendaxx.lab8server.repository.UsersRepository;
import ru.rendaxx.lab8server.service.UserService;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UsersRepository usersRepository, UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity addUser(@RequestBody UserDto user) {
        userService.createUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
