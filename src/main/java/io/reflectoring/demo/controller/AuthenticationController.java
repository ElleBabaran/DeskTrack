package io.reflectoring.demo.controller;

import io.reflectoring.demo.entity.DashboardStatus;
import io.reflectoring.demo.entity.User;
import io.reflectoring.demo.repository.DashboardStatusRepository;
import io.reflectoring.demo.repository.UserRepository;
import io.reflectoring.demo.service.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    private final CustomerDetailsService customerDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final DashboardStatusRepository repository;

    public AuthenticationController(CustomerDetailsService customerDetailsService, PasswordEncoder passwordEncoder, UserRepository userRepository, DashboardStatusRepository repository) {
        this.customerDetailsService = customerDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.repository = repository;
    }

    @GetMapping("/")
    public String showLandingPage() {
        return "opening";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam String username,
                            @RequestParam String password,
                            Model model) {

        Optional<User> optionalUser = customerDetailsService.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return "redirect:/homepage";
            } else {
                model.addAttribute("message", "Incorrect username or password");
            }
        } else {
            model.addAttribute("message", "User not found");
        }

        return "login";
    }

    @GetMapping("/signin")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "signin";
    }

    @PostMapping("/signin")
    public String saveUser(@ModelAttribute("user") User user) {
        user.setEnabled(true);
        customerDetailsService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/forgot-password")
    public String showForgotPassword(Model model) {
        model.addAttribute("user", new User());
        return "forgot-password";
    }


    @GetMapping("/homepage")
    public String home() {
        return "homepage"; //
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        String username = principal.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("fullName", user.getFullName());
            model.addAttribute("email", user.getUsername());
            model.addAttribute("program", user.getProgram());
        }
        return "user";
    }

    @GetMapping("/index")
    public String viewDashboard(Model model) {
        List<DashboardStatus> statuses = repository.findAll();
        System.out.println("Statuses loaded: " + statuses.size());
        for (DashboardStatus s : statuses) {
            System.out.println("User: " + s.getFullName() + " | Status: " + s.getStatus() + " | Notes: " + s.getNotes());
        }
        model.addAttribute("statuses", statuses);
        return "index";
    }


    @GetMapping("/admin")
    public String showAdmin() {
        return "admin";
    }
}



