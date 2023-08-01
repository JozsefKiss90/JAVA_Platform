package org.platform.controller;

import org.platform.data.User;
import org.platform.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            userService.save(user);
            return new ResponseEntity<>("User created successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userService.getUser(username);
    }

    @PutMapping("/{username}")
    public void updateUser(@PathVariable String username, @RequestBody User user) {
        User user1 = userService.getUser(username);
        userService.updateUser(user1);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }
}
