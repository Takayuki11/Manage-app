package com.example.manage;

import com.example.manage.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> incompleteTaskList = new ArrayList<>();
    public List<Task> processingTaskList = new ArrayList<>();
    public List<Task> completedTaskList = new ArrayList<>();
}
