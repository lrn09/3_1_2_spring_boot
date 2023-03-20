package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "index";
    }

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @GetMapping("/users/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        return "show";
    }

    @PostMapping("/")
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.findOne(id));
        return "edit";
    }

    @PatchMapping(value = "/users/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
