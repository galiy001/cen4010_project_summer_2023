package com.geektext.geektext_backend_api.service;

import com.geektext.geektext_backend_api.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserEntity> returnAllUsers(); //Return all users.
    Optional<UserEntity> searchByUserId(Long user_id); //Find a user by user_id.
    UserEntity createNewUser(UserEntity userEntity); //Create a new user.
    UserEntity updateUser(UserEntity userEntity); //Update an existing user.
    void deleteUser(Long user_id); //Delete a user.

}
