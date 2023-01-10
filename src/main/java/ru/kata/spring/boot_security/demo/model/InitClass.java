package ru.kata.spring.boot_security.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.service.UserService;

@Component
public class InitClass {

    @Autowired
    private UserService userService;


    public  void createUser() {

        User user = new User("user@gmail.com", "111", "user", 20L, "user");
        user.addRole(new Role("ROLE_USER"));
        User admin = new User("admin@gmail.com", "111", "admin", 220L, "admin");
        admin.addRole(new Role("ROLE_ADMIN"));

        userService.saveUser(admin);
        userService.saveUser(user);


    }
}
