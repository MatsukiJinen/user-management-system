package com.example.springboot3.repository;

import com.example.springboot3.domain.user.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("select user from User user where userId = :userId")
    public User findLoginUser(@Param("userId") String userId);

    @Modifying
    @Query("update User set password = :password, userName = :userName where userId = :userId")
    public Integer updateUser(@Param("userId") String userId,
                              @Param("password") String password,
                              @Param("userName") String userName);
}
