package spbstu.courseProject.autoService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import spbstu.courseProject.autoService.auth.UserService;
import spbstu.courseProject.autoService.dto.RegistrationDto;
import spbstu.courseProject.autoService.exception.DuplicatedUsernameException;

import java.net.URI;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity addUser(@RequestBody RegistrationDto registrationDto) {
        try {
            userService.addUser(registrationDto);

            return ResponseEntity.ok().build();
        } catch (DuplicatedUsernameException e) {
            return ResponseEntity.status(HttpStatus.resolve(422)).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
