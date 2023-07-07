package com.geektext.geektext_backend_api.service.implementation;

import com.geektext.geektext_backend_api.entity.UserEntity;
import com.geektext.geektext_backend_api.repository.UserRepository;
import com.geektext.geektext_backend_api.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> returnAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> searchByUserId(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<UserEntity> searchByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity createNewUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserEntity updateUserByUsername(String username, UserEntity userEntity) {
        Optional<UserEntity> existingUserOptional = userRepository.findByUsername(username);

        if (existingUserOptional.isPresent()) {
            UserEntity existingUser = existingUserOptional.get();

            // Update the fields of existingUser with the fields of userEntity.
            // For example:
            existingUser.setUsername(userEntity.getUsername());
            existingUser.setPassword(userEntity.getPassword());
            existingUser.setFirstName(userEntity.getFirstName());
            existingUser.setLastName(userEntity.getLastName());
            existingUser.setHomeAddress(userEntity.getHomeAddress());

            // Update the user in the database.
            userRepository.save(existingUser);

            return existingUser;
        } else {
            throw new RuntimeException("User not found with username: " + username);
        }
    }
}
