package io.reflectoring.demo.controller;

import io.reflectoring.demo.entity.User;
import io.reflectoring.demo.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class adminController {

    private final String USERNAME = "admin";
    private final String PASSWORD = "123";


    private final UserRepository userRepository;

    public adminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/admintable")
    public String getAdminTable(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admintable";
    }

    @PostMapping("/admin")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            session.setAttribute("adminLoggedIn", true);
            return "redirect:/admintable";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "admin";
        }
    }

    @PostMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/admintable";
    }
}
