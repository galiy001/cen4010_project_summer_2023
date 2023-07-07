package com.geektext.geektext_backend_api.controller;

import com.geektext.geektext_backend_api.entity.UserEntity;
import com.geektext.geektext_backend_api.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
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
    public UserEntity createNewUser(@RequestBody UserEntity userEntity) {
        return userService.createNewUser(userEntity);
    }

    @PutMapping
    public UserEntity updateUser(@RequestBody UserEntity userEntity) {
        return userService.updateUser(userEntity);
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
