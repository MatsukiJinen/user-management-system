package com.example.springboot3.domain.user.service.impl;

import com.example.springboot3.domain.user.model.User;
import com.example.springboot3.domain.user.service.UserService;
import com.example.springboot3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserServiceJpaImpl implements UserService {

    @Autowired
    UserRepository repository;

    // @Autowired
    // private PasswordEncoder encoder;

    @Transactional
    @Override
    public void register(User user) {
        boolean exists = repository.existsById(user.getUserId());
        if (exists) {
            throw  new DataAccessException("ユーザーが既に存在"){};
        }
        user.setDepartmentId(1);
        user.setRole("ROLE_GENERAL");

        // String rawPassword = user.getPassword();
        // user.setPassword(encoder.encode(rawPassword));
        repository.save(user);
    }

    @Override
    public List<User> getUsers(User user) {
        ExampleMatcher matcher = ExampleMatcher
                .matching() // and
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // LIKE
                .withIgnoreCase(); // 大文字・小文字の両方
        return repository.findAll(Example.of(user, matcher));
    }

    @Override
    public User getUserByUserId(String userId) {
        Optional<User> option = repository.findById(userId);
        User user = option.orElse(null);
        return user;
    }

    @Transactional
    @Override
    public void updateUserOne(String userId, String password, String userName) {
        repository.updateUser(userId, password, userName);
    }

    @Transactional
    @Override
    public void deleteUserOne(String userId) {
        repository.deleteById(userId);
    }

    @Override
    public User getLoginUser(String userId) {
        return repository.findLoginUser(userId);
    }
}
