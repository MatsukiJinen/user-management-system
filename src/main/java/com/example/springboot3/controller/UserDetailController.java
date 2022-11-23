package com.example.springboot3.controller;

import com.example.springboot3.domain.user.model.User;
import com.example.springboot3.domain.user.service.UserService;
import com.example.springboot3.form.UserDetailForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserDetailController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/detail/{userId:.+}")
    public String getUserDetail(UserDetailForm form,
                                Model model,
                                @PathVariable("userId") String userId) {
        User user = userService.getUserByUserId(userId);
        user.setPassword(null);

        // user を form に変換
        form = modelMapper.map(user, UserDetailForm.class);
        form.setSalaryList(user.getSalaryList());

        model.addAttribute("userDetailForm", form);

        return "user/detail";
    }

    @PostMapping(value = "/detail", params = "update")
    public String updateUser(UserDetailForm form, Model model) {
        userService.updateUserOne(form.getUserId(),
                                  form.getPassword(),
                                  form.getUserName());
        return "redirect:/user/list";
    }

    @PostMapping(value = "/detail", params = "delete")
    public String deleteUser(UserDetailForm form, Model model) {
        userService.deleteUserOne(form.getUserId());
        return "redirect:/user/list";
    }
}
