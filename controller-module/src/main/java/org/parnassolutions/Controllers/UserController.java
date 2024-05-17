package org.parnassolutions.Controllers;

import lombok.RequiredArgsConstructor;
import org.parnassolutions.DTOs.UserDTO;
import org.parnassolutions.Entities.User;
import org.parnassolutions.Services.UserService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable("userId") Integer userId) {
        return userService.getUserById(userId);
    }

    @PostMapping(value = "/add", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody UserDTO dto) {
        return userService.createUser(dto);
    }

    @DeleteMapping(value = "/delete/{userId}", produces = APPLICATION_JSON_VALUE)
    public void deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
    }
}
