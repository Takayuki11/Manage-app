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
    private int id;
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Column(name = "task_name", nullable = false)
    private String taskName;
    @Column(name = "task_note")
    private String taskNote;
    @Column(name = "task_status")
    private String taskStatus;
    @Column(name = "sort_number")
    private int sortNumber;
    @Column(name = "schedule_status")
    private boolean scheduleStatus;
    @Column(name = "coordinator")
    private String coordinator;
    @Column(name = "customers_company_id")
    private int customersCompanyId;
    @Column(name = "task_limit")
    private Date taskLimit;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}
