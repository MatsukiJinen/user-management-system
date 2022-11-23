package com.example.springboot3.rest;

import com.example.springboot3.domain.user.model.User;
import com.example.springboot3.domain.user.service.UserService;
import com.example.springboot3.form.SignupForm;
import com.example.springboot3.form.UserDetailForm;
import com.example.springboot3.form.UserListForm;
import com.example.springboot3.form.ValidateGroupOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;
    @GetMapping("/get/list")
    public List<User> getUserList(UserListForm form) {
        User user = modelMapper.map(form, User.class);
        return userService.getUsers(user);
    }

    @PostMapping("/signup/rest")
    public RestResult postSignup(@Validated(ValidateGroupOrder.class) SignupForm form,
                                 BindingResult bindingResult, Locale locale) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                String errorMessage = messageSource.getMessage(error, locale);
                errors.put(error.getField(), errorMessage);
            }
            return new RestResult(90, errors);
        }
        User user = modelMapper.map(form, User.class);
        userService.register(user);
        return new RestResult(0, null);
    }

    @PutMapping("/update")
    public int updateUser(UserDetailForm form) {
        userService.updateUserOne(form.getUserId(), form.getPassword(), form.getUserName());
        return 0;
    }

    @DeleteMapping("/delete")
    public int deleteUser(UserDetailForm form) {
        userService.deleteUserOne(form.getUserId());
        return 0;
    }
}
