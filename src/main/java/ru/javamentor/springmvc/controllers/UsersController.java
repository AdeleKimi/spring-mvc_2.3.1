package ru.javamentor.springmvc.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springmvc.dao.UserDaoManager;
import ru.javamentor.springmvc.models.User;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserDaoManager userDao;
    @Autowired
    public UsersController(UserDaoManager userDao) {
        this.userDao = userDao;
    }


    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userDao.getAll());

        return "users/AllUsers";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userDao.getUserById(id));

        return "users/byId";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/new";
        }

        userDao.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userDao.getUserById(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }

        userDao.updateUser(id,user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userDao.deleteUser(id);
        return "redirect:/users";
    }
}
