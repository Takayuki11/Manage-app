package com.example.manage.controllers;

import com.example.manage.Service.TaskService;
import com.example.manage.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public List<Task> getTasks(){
        return taskService.getTasks();
    }

    @GetMapping("/tasks/processing")
    public List<Task> getProcessingTasks(){
        return taskService.getProcessingTask();
    }

    @GetMapping("/tasks/completed")
    public List<Task> getCompletedTasks(){
        return taskService.getCompletedTask();
    }

    @GetMapping("/tasks/{id}")
    public Optional<Task> getTask(@PathVariable int id){
        return taskService.getTask(id);
    }

    @PostMapping("/tasks/create")
    public void create(@RequestBody Task task){
        taskService.save(task);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> update(@PathVariable int id, @RequestBody Task task){
        Optional<Task> target = taskService.getTask(id);
        if(target.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Task editTask = target.get();
            editTask.setTaskName(task.getTaskName());
            editTask.setTaskNote(task.getTaskNote());
            taskService.save(editTask);

            return new ResponseEntity<Task>(HttpStatus.OK);
        }
    }

    @PutMapping("/tasks/sort")
    public ResponseEntity<Task> sortTasks(@RequestBody Task task, @RequestParam("from") List<Integer> fromParams, @RequestParam("to") List<Integer> toParams) {
        taskService.sortTasks(fromParams, toParams);

        Optional<Task> target = taskService.getTask(task.getId());
        if(target.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Task editTask = target.get();
            editTask.setTaskStatus(task.getTaskStatus());
            taskService.save(editTask);

            return new ResponseEntity<Task>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable("id") int id){
        taskService.delete(id);
    }
}
