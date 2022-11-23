package com.example.springboot3.repository;

import com.example.springboot3.domain.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    public int insertOne(User user);

    public List<User> selectAll(User user);

    public User findByUserId(String userId);

    public void updateOne(@Param("userId") String userId,
                          @Param("password") String password,
                          @Param("userName") String userName);

    public int deleteOne(@Param("userId") String userId);

    public User findLoginUser(String userId);
 }