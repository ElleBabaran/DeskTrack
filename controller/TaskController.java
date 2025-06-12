package io.reflectoring.demo.controller;

import io.reflectoring.demo.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/status")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTasks(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("tasks", taskService.getTasksForUser(username));
        return "status";
    }

    @PostMapping("/addTask")
    public String addTask(@RequestParam String task, Principal principal) {
        String username = principal.getName();
        taskService.addTask(task, username);
        return "redirect:/status";
    }

    @PostMapping("/deleteTask")
    public String deleteTask(@RequestParam Long index, Principal principal) {
        String username = principal.getName();
        taskService.deleteTask(index, username);
        return "redirect:/status";
    }


}
