package com.example.manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.manage.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query(value = "SELECT * FROM tasks WHERE task_status = 'incomplete' ORDER BY sort_number ASC", nativeQuery = true)
    public List<Task>findTasks();

    @Query(value = "SELECT * FROM tasks WHERE task_status = 'processing' ORDER BY sort_number ASC", nativeQuery = true)
    public List<Task>findProcessingTasks();

    @Query(value = "SELECT * FROM tasks WHERE task_status = 'completed' ORDER BY sort_number ASC", nativeQuery = true)
    public List<Task>findCompletedTasks();
}
