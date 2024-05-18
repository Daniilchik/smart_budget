package org.parnassolutions.Controllers;

import lombok.RequiredArgsConstructor;
import org.parnassolutions.DTOs.UserDTO;
import org.parnassolutions.Entities.User;
import org.parnassolutions.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public User findByUserId(@PathVariable("userId") Long userId) {
        return userService.findByUserId(userId);
    }

    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping(value = "/count")
    public Long getUsersCount() {
        return userService.getUsersCount();
    }

    @PostMapping(value = "/add", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody UserDTO dto) {
        return userService.addUser(dto);
    }

    @PatchMapping(value = "/update/{userId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public User updateUser(@PathVariable("userId") Long userId, @RequestBody UserDTO dto) {
        return userService.updateUser(userId, dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/delete/{userId}", produces = APPLICATION_JSON_VALUE)
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }
}
