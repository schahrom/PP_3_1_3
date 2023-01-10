package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.InitClass;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InitClass initClass;


    @RequestMapping("/")
    public String homePage() {
        initClass.createUser();
        return "redirect:/login";
    }


    @RequestMapping("/user")
    public String mainPageUserInfo(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("userModel", user);
        return "user";
    }
}
