package com.example.user_task.ui;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestModel {


    private String taskName;
    private String taskDescription;
    private boolean completed;
    private String startDate;
    private String endDate;
    private String assignedBy;
    private String assignedTo;

}
