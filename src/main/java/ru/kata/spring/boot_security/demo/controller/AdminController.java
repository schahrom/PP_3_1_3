package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.InitClass;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private static InitClass initClass;


    @GetMapping("/list")
    public String listUser(Model model) {
        List<User> allUser = userService.allUser();
        model.addAttribute("allUser", allUser);
        return "admin-page";
    }

    @GetMapping("/new")
    public String newUserEntity(Model model) {
        model.addAttribute("user", new User());
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("allRoles", roles);
        return "new-user";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/list";

    }

    @GetMapping("/edit/{id}")
    public ModelAndView editUser(@PathVariable(name = "id") Long id) {
        User user = userService.findUserById(id);
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("user", user);

        List<Role> roles = roleService.findAllRoles();

        mav.addObject("allRoles", roles);

        return mav;
    }

    @PostMapping ("/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin/list";

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/list";
    }
}

