package com.example.springboot3.domain.user.service.impl;

import com.example.springboot3.domain.user.model.User;
import com.example.springboot3.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // get login user information
        User loginUser = userService.getLoginUser(username);
        if (loginUser == null) {
            throw new UsernameNotFoundException("user not found:" + username);
        }

        // create authority list
        GrantedAuthority authority = new SimpleGrantedAuthority(loginUser.getRole());
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(authority);

        // create userDetails
        return new org.springframework.security.core.userdetails.User(loginUser.getUserId(), loginUser.getPassword(), authorityList);
    }
}
