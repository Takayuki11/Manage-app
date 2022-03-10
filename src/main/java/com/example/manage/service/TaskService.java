package com.example.manage.service;

import com.example.manage.TaskList;
import com.example.manage.entity.Task;
import com.example.manage.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskService {

    public final TaskRepository taskRepository;
    public TaskList taskList = new TaskList();

    @Autowired
    public TaskService(TaskRepository repository){
        this.taskRepository = repository;
    }

    public List<Task> getTasks(){
        this.taskList.incompleteTaskList = taskRepository.findByTasks();
        return this.taskList.incompleteTaskList;
    }

    public List<Task> getProcessingTask(){
        this.taskList.processingTaskList = taskRepository.findByProcessingTasks();
        return this.taskList.processingTaskList;
    }

    public List<Task> getCompletedTask(){
        this.taskList.completedTaskList = taskRepository.findByCompletedTasks();
        return this.taskList.completedTaskList;
    }

    public Optional<Task> getTask(int id){
        return this.taskRepository.findById(id);
    }

    public void save(Task task){
        this.taskRepository.save(task);
    }

    public void sortTasks(List<Integer> fromIds,List<Integer> toIds){
        if(fromIds.equals(toIds)){
            for(int i = 0; i < toIds.size(); i++){
                Optional<Task> target = taskRepository.findById(toIds.get(i));
                Task task = target.get();
                task.setSortNumber(i + 1);
                this.taskRepository.save(task);
            }
        } else {
            for(int i = 0; i < toIds.size(); i++){
                Optional<Task> target = taskRepository.findById(toIds.get(i));
                Task task = target.get();
                task.setSortNumber(i + 1);
                this.taskRepository.save(task);
            }
            for (int i = 0; i < fromIds.size(); i++){
                Optional<Task> target = taskRepository.findById(fromIds.get(i));
                Task task = target.get();
                task.setSortNumber(i + 1);
                this.taskRepository.save(task);
            }
        }
    }

    public void delete(int id){
        this.taskRepository.deleteById(id);
    }

    public void deleteOnlyScheduledTasks(){
        for(Task task : this.taskList.completedTaskList){
            if(task.isScheduleStatus() == true){
                this.taskRepository.delete(task);
            }
        }
    }
}
