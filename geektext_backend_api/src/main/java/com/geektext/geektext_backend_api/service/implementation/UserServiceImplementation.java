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
    public Optional<UserEntity> searchByUserId(Long user_id) {
        return userRepository.findById(user_id);
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
    public void deleteUser(Long user_id) {
        userRepository.deleteById(user_id);
    }
}
