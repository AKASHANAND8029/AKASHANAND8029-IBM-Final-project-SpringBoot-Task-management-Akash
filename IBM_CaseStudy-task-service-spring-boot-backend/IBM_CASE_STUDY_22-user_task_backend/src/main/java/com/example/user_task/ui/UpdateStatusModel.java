package com.example.user_task.ui;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateStatusModel {
    private Integer uniqueTaskId;
    private boolean completed;


}
