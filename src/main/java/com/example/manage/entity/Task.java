package com.example.manage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private long user_id;
    @Column(nullable = false)
    private String task_name;
    @Column
    private Date task_limit;
    @Column
    private String task_note;
    @Column(name = "task_processing")
    private boolean isProcessing;
    @Column(name = "task_completed")
    private boolean isCompleted;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}
