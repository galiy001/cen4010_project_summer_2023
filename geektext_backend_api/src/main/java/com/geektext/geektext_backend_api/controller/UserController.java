package com.geektext.geektext_backend_api.controller;

import com.geektext.geektext_backend_api.entity.UserEntity;
import com.geektext.geektext_backend_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
//The UserController class is typically a part of a web application's backend or server-side code.
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> returnAllUsers() {
        return userService.returnAllUsers();
    }

    @GetMapping("/{user_id}")
    public Optional<UserEntity> searchByUserId(@PathVariable("user_id") Long userId) {
        return userService.searchByUserId(userId);
    }

    @GetMapping("/username/{username}")
    public Optional<UserEntity> searchByUsername(@PathVariable("username") String username) {
        return userService.searchByUsername(username);
    }

    @PostMapping
    public ResponseEntity<UserEntity> createNewUser(@RequestBody UserEntity userEntity) {
        if (userEntity.getUsername() == null || userEntity.getPassword() == null) {
            return ResponseEntity.badRequest().build();
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(userEntity.getUsername());
        newUser.setPassword(userEntity.getPassword());

        newUser.setFirstName(userEntity.getFirstName() != null ? userEntity.getFirstName() : "");
        newUser.setLastName(userEntity.getLastName() != null ? userEntity.getLastName() : "");
        newUser.setHomeAddress(userEntity.getHomeAddress() != null ? userEntity.getHomeAddress() : "");
        newUser.setEmailAddress(userEntity.getEmailAddress() != null ? userEntity.getEmailAddress() : "");

        UserEntity savedUser = userService.createNewUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable("user_id") Long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping("/username/{username}")
    public void updateUser(@PathVariable("username") String username, @RequestBody UserEntity userEntity) {
        userService.updateUserByUsername(username, userEntity);
    }

}
