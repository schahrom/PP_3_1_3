package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.InitClass;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private static InitClass initClass;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/list")
    public String listUser(Model model, Principal principal) {
        Optional<User> user = userService.findByUserName(principal.getName());
        model.addAttribute("admin", user.get());
        List<User> allUser = userService.allUser();
        model.addAttribute("allUser", allUser);
        model.addAttribute("newUser", new User());

        model.addAttribute("allRoles", roleService.findAllRoles());
        return "admin-page";
    }
    @PostMapping
    public String create(@ModelAttribute("newUser") User user) {
        userService.saveUser(user);
        return "redirect:/admin/list";

    }



    @GetMapping("/findOne")
    @ResponseBody
    public User findUserById(Long id) {
        return userService.findUserById(id);
    }

    @PatchMapping("/save")
    public String update(User user) {
        userService.saveUser(user);
        return "redirect:/admin/list";
    }

    @GetMapping("/delete")
    public String deleteUser(Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/list";
    }




//    @GetMapping("/edit/{id}")
//    public ModelAndView editUser(@PathVariable(name = "id") Long id) {
//        User user = userService.findUserById(id);
//        ModelAndView mav = new ModelAndView("edit");
//        mav.addObject("user", user);
//        List<Role> roles = roleService.findAllRoles();
//        mav.addObject("allRoles", roles);
//
//        return mav;
//    }

//    @PostMapping ("/{id}")
//    public String update(@ModelAttribute("user") User user) {
//        userService.update(user);
//        return "redirect:/admin/list";
//
//    }
//
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") Long id) {
//        userService.deleteUser(id);
//        return "redirect:/admin/list";
//    }
}

