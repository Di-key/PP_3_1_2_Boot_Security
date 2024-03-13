package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> allUsers = userService.readAllUsers();
        model.addAttribute("all", allUsers);
        return "all-users";
    }

    @GetMapping("/addNewUser")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("userAdd", user);
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "user-create";
    }

    @PostMapping("/saveUser")
    public String create(@ModelAttribute("newUser") User user) {
        userService.create(user);
        return "redirect:/admin";
    }

    @PostMapping("/updateInfo")
    public String updateUser(@ModelAttribute("newUser") User user) {
        userService.update(user);

        return "redirect:/admin";
    }


    @GetMapping("/findUser")
    public String findUser(@RequestParam("username") String username, Model model) {
        model.addAttribute("user", userService.findByUsername(username));
        model.addAttribute("roles", roleService.getAllRoles());
        return "user-info";
    }

    //deleteUser
    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("userID") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
