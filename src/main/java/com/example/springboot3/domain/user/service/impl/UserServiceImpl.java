package com.example.springboot3.domain.user.service.impl;

import com.example.springboot3.domain.user.model.User;
import com.example.springboot3.domain.user.service.UserService;
import com.example.springboot3.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
// MyBatis ↓
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    // @Autowired
    // private PasswordEncoder encoder;

    @Override
    public void register(User user) {
        user.setDepartmentId(1);
        user.setRole("ROLE_GENERAL");

        // String rawPassword = user.getPassword();
        // user.setPassword(encoder.encode(rawPassword));
        mapper.insertOne(user);
    }

    @Override
    public List<User> getUsers(User user) {
        return mapper.selectAll(user);
    }

    @Override
    public User getUserByUserId(String userId) {
        return mapper.findByUserId(userId);
    }

    @Transactional
    @Override
    public void updateUserOne(String userId, String password, String userName) {
    // String encryptPassword = encoder.encode(password);
        mapper.updateOne(userId, password, userName);
    }

    @Override
    public void deleteUserOne(String userId) {
        mapper.deleteOne(userId);
    }

    @Override
    public User getLoginUser(String userId) {
        return mapper.findLoginUser(userId);
    }
}