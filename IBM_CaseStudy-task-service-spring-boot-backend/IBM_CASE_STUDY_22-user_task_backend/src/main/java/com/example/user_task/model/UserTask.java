package com.example.user_task.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "task_management")
public class UserTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "unique_task_id", nullable = false)
    private Integer uniqueTaskId;


    @Column(name = "task_name", nullable = false)
    private String taskName;

    @Column(name = "task_description")
    private String taskDescription;

    @Column(name = "status", nullable = false)
    private boolean completed;

    @Column(name = "start_date",nullable = false)
    private String startDate;

    @Column(name = "end_date", nullable = false)
    private String endDate;

    @Column(name = "assigned_by_person", nullable = false)
    private String assignedBy;

    @Column(name = "assigned_to_person", nullable = false)
    private String assignedTo;

//    @Column(name = "email", nullable = false)
//    private String email;

//    @Column(name = "user_name",nullable = false,unique = true)
//    private String userName;
//
//    @Column(name = "user_encrypted_password",nullable = false)
//    private String encryptedPassword;
}
