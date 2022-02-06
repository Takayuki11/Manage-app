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

    @Autowired
    public TaskRepository taskRepository;

    TaskList taskList = new TaskList();

    public List<Task> getTasks(){
        taskList.incompleteTaskList = taskRepository.findByTasks();
        return taskList.incompleteTaskList;
    }

    public List<Task> getProcessingTask(){
        taskList.processingTaskList = taskRepository.findByProcessingTasks();
        return taskList.processingTaskList;
    }

    public List<Task> getCompletedTask(){
        taskList.completedTaskList = taskRepository.findByCompletedTasks();
        return taskList.completedTaskList;
    }

    public Optional<Task> getTask(int id){
        return taskRepository.findById(id);
    }

    public void save(Task task){
        taskRepository.save(task);
    }

    public void sortTasks(List<Integer> fromIds,List<Integer> toIds){
        if(fromIds.equals(toIds)){
            for(int i = 0; i < toIds.size(); i++){
                Optional<Task> target = taskRepository.findById(toIds.get(i));
                Task task = target.get();
                task.setSortNumber(i + 1);
                taskRepository.save(task);
            }
        } else {
            for(int i = 0; i < toIds.size(); i++){
                Optional<Task> target = taskRepository.findById(toIds.get(i));
                Task task = target.get();
                task.setSortNumber(i + 1);
                taskRepository.save(task);
            }
            for (int i = 0; i < fromIds.size(); i++){
                Optional<Task> target = taskRepository.findById(fromIds.get(i));
                Task task = target.get();
                task.setSortNumber(i + 1);
                taskRepository.save(task);
            }
        }
    }

    public void delete(int id){
        taskRepository.deleteById(id);
    }

    public void deleteOnlyScheduledTasks(){
        for(Task task : taskList.completedTaskList){
            if(task.isScheduleStatus() == true){
                taskRepository.delete(task);
            }
        }
    }


}
