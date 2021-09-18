package com.example.manage.controllers;

import com.example.manage.entity.Task;
import com.example.manage.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8081/")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public List<Task> fetchTask(){
        return taskRepository.findTasks();
    }

    @GetMapping("/tasks/{id}")
    public Optional<Task> getTask(@PathVariable Long id){
        return taskRepository.findById(id);
    }

    @PostMapping("/tasks/create")
    public void create(@RequestBody Task task){
        taskRepository.save(task);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task){
        Optional<Task> target = taskRepository.findById(id);
        if(target.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Task editTask = target.get();
            editTask.setEmail(task.getEmail());
            editTask.setName(task.getName());
            editTask.setTask(task.getTask());
            taskRepository.save(editTask);
            System.out.print(editTask);
            return new ResponseEntity<Task>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable("id") Long id){
        taskRepository.deleteById(id);
    }
}
