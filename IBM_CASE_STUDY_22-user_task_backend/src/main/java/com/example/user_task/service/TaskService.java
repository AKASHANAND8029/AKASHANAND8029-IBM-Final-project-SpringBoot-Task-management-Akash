package com.example.user_task.service;

import com.example.user_task.dto.Dto;
import com.example.user_task.model.UserTask;
import com.example.user_task.ui.RequestModel;
import com.example.user_task.ui.ResponseModel;
import com.example.user_task.ui.UpdateStatusModel;


import java.util.List;

public interface TaskService {
    public Dto createTask(Dto dto);
    public List<Dto> getTask();
    public Dto findTaskByTaskId(Integer uniqueTaskId);
    public void deleteTaskByTaskId(Integer uniqueTaskId);
    public Dto updateTaskByTaskId(RequestModel requestModel, Integer uniqueTaskId);
    public List<ResponseModel> findByAssignedTo(String assignedTo);
    public Dto updateByAssignedTo(UpdateStatusModel updateStatusModel, String assignedTo);
    public List<ResponseModel> findByAssignedBy(String assignedBy);
}
