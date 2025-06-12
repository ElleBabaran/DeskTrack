package io.reflectoring.demo.service;
import io.reflectoring.demo.entity.Task;


import io.reflectoring.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }
    public List<Task> getAllTasks() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void addTask(String name, String userID) {
        Task task = new Task(name, userID);
        repository.save(task);
    }


    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
    public void editTask(Long id, String newToDo, String username) {
        Task task = repository.findById(id).orElse(null);
        if (task != null && task.getUserID().equals(username)) {
            task.setToDo(newToDo);
            repository.save(task);
        }
    }

    public void deleteTask(Long id, String username) {
        Task task = repository.findById(id).orElse(null);
        if (task != null && task.getUserID().equals(username)) {
            repository.delete(task);
        }
    }
    public List<Task> getTasksForUser(String userID) {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .filter(task -> userID.equals(task.getUserID())) // null-safe
                .collect(Collectors.toList());
    }


}
