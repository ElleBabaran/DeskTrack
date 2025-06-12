package io.reflectoring.demo.controller;

import io.reflectoring.demo.entity.DashboardStatus;
import io.reflectoring.demo.repository.DashboardStatusRepository;
import io.reflectoring.demo.repository.UserRepository;
import io.reflectoring.demo.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardStatusRepository repository;
    private final UserRepository userRepository;
    private final DashboardService dashboardService;

    public DashboardController(DashboardStatusRepository repository,
                               UserRepository userRepository,
                               DashboardService dashboardService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public String showDashboard(Model model, Principal principal) {
        String userId = principal.getName();
        DashboardStatus status = repository.findByUserId(userId).orElse(new DashboardStatus());
        model.addAttribute("dashboardStatus", status);
        return "dashboard";
    }

    @PostMapping("/update-status")
    public String updateStatus(@RequestParam("status") String status, Principal principal) {
        String userId = principal.getName();
        DashboardStatus dashboardStatus = repository.findByUserId(userId).orElse(new DashboardStatus());
        dashboardStatus.setUserId(userId);
        dashboardStatus.setStatus(status);
        dashboardStatus.setNotes(status);
        userRepository.findByUsername(userId).ifPresent(user -> dashboardStatus.setFullName(user.getFullName()));
        repository.save(dashboardStatus);
        return "redirect:/dashboard";
    }

    @PostMapping("/save-note")
    public String saveNote(@RequestParam("note") String note, Principal principal) {
        String userId = principal.getName();
        DashboardStatus dashboardStatus = repository.findByUserId(userId).orElse(new DashboardStatus());
        dashboardStatus.setUserId(userId);
        dashboardStatus.setNotes(note);
        repository.save(dashboardStatus);
        return "redirect:/dashboard";
    }

    @PostMapping("/delete-note")
    public String deleteNote(Principal principal) {
        String userId = principal.getName();
        DashboardStatus dashboardStatus = repository.findByUserId(userId).orElse(null);
        if (dashboardStatus != null) {
            dashboardStatus.setNotes("");
            repository.save(dashboardStatus);
        }
        return "redirect:/dashboard";
    }

}