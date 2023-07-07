package com.geektext.geektext_backend_api.service;

import com.geektext.geektext_backend_api.entity.BookEntity;
import com.geektext.geektext_backend_api.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserEntity> returnAllUsers(); //Return all users.
    Optional<UserEntity> searchByUserId(Long userId); //Find a user by user_id.
    Optional<UserEntity> searchByUsername(String username); //Find a user by username.
    UserEntity createNewUser(UserEntity userEntity); //Create a new user.
    UserEntity updateUser(UserEntity userEntity); //Update an existing user.
    void deleteUser(Long userId); //Delete a user.
    UserEntity updateUserByUsername(String username, UserEntity userEntity); //Update a user's information.

}
