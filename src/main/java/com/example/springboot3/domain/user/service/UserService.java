package com.example.springboot3.domain.user.service;

import com.example.springboot3.domain.user.model.User;

import java.util.List;

public interface UserService {
    public void register(User user);

    public List<User> getUsers(User user);

    public User getUserByUserId(String userId);

    public void updateUserOne(String userId, String password, String userName);

    public void deleteUserOne(String userId);

    public User getLoginUser(String userId);
}