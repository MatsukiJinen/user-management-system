package com.example.springboot3.controller;

import com.example.springboot3.domain.user.model.User;
import com.example.springboot3.domain.user.service.UserService;
import com.example.springboot3.form.SignupForm;
import com.example.springboot3.form.ValidateGroupOrder;
import com.example.springboot3.service.UserApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/user")
@Slf4j
public class SignupController {

    @Autowired
    private UserApplicationService userApplicationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/signup")
    public String getSignup(Model model, Locale locale, @ModelAttribute SignupForm form) {
        Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
        model.addAttribute("genderMap", genderMap);
        return "user/signup";
    }

    @PostMapping("/signup")
    public String postSignup(Model model, Locale locale,
                             @ModelAttribute @Validated(ValidateGroupOrder.class) SignupForm form,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return getSignup(model, locale, form);
        }

        log.info(form.toString());

        // formをUserクラスに変換
        User user = modelMapper.map(form, User.class);

        userService.register(user);

        return "redirect:/login";
    }

    // データベース関連の例外処理
    @ExceptionHandler(DataAccessException.class)
    public String dataAccessExceptionHandler(DataAccessException e, Model model) {
        model.addAttribute("error", "");
        model.addAttribute("message", "SignupControllerで例外が発生しました。");
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return "error";
    }

    // その他の例外処理
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {
        model.addAttribute("error", "");
        model.addAttribute("message", "SignupControllerで例外が発生しました。");
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return "error";
    }
}
