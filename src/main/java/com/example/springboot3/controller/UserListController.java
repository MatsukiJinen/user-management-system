package com.example.springboot3.controller;

import com.example.springboot3.domain.user.model.User;
import com.example.springboot3.domain.user.service.UserService;
import com.example.springboot3.form.UserListForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserListController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public String getUserList(@ModelAttribute UserListForm form,
                              Model model) {
        // form -> User change
        User user = modelMapper.map(form, User.class);
        List<User> userList = userService.getUsers(user);
        model.addAttribute("userList", userList);
        return "user/list";
    }

    @PostMapping("/list")
    public String postUserList(@ModelAttribute UserListForm form, Model model) {
        User user = modelMapper.map(form, User.class);
        List<User> userList = userService.getUsers(user);
        model.addAttribute("userList", userList);
        return "user/list";
    }
}
