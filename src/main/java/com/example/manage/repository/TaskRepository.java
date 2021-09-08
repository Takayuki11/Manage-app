package com.example.manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.manage.entity.Task;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "SELECT * FROM task ORDER BY id ASC", nativeQuery = true)
    public List<Task>findTasks();
}
