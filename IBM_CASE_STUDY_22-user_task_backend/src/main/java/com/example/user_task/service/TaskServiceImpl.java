package com.example.user_task.service;

import com.example.user_task.dto.Dto;
import com.example.user_task.exception.EmptyListException;
import com.example.user_task.exception.TaskNotFoundException;
import com.example.user_task.model.UserTask;
import com.example.user_task.repo.TaskRepository;
import com.example.user_task.ui.RequestModel;

import com.example.user_task.ui.ResponseModel;
import com.example.user_task.ui.UpdateStatusModel;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
@Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Dto createTask(Dto dto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserTask userTask=modelMapper.map(dto,UserTask.class);
//        StringBuilder stringBuilder=new StringBuilder(dto.getPassword());
//        userTask.setEncryptedPassword(stringBuilder.reverse().toString());
        userTask= taskRepository.save(userTask);

        return modelMapper.map(userTask,Dto.class);
    }

    @Override
    public List<Dto> getTask() {
    List<Dto> list=new ArrayList<>();


    Iterable<UserTask> iterable= taskRepository.findAll();
        Iterator<UserTask> iterator= iterable.iterator();
        while (iterator.hasNext())
        {
            list.add(modelMapper.map(iterator.next(),Dto.class));
        }
        if(list.isEmpty()){
            throw new EmptyListException("list is empty");
        }
            return list;

    }

    @Override
    public Dto findTaskByTaskId(Integer uniqueTaskId) {

    UserTask userTask= findTaskId(uniqueTaskId);
        if(userTask==null)
        {
            throw new TaskNotFoundException("Task with id: "+uniqueTaskId+" not found");
        }
        return modelMapper.map(userTask,Dto.class);
    }

    @Override
    public void deleteTaskByTaskId(Integer uniqueTaskId) {
        UserTask userTask=findTaskId(uniqueTaskId);
        if(userTask==null)
        {
            throw new TaskNotFoundException("Task with id: "+uniqueTaskId+" not found");
        }
        taskRepository.delete(userTask);
    }

    @Override
    public Dto updateTaskByTaskId(RequestModel requestModel, Integer uniqueTaskId) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserTask entity=findTaskId(uniqueTaskId);
        if (entity==null)
        {
            throw new TaskNotFoundException("Task with "+uniqueTaskId+" not found");
        }
        entity.setTaskName(requestModel.getTaskName());
        entity.setTaskDescription(requestModel.getTaskDescription());
        entity.setAssignedTo(requestModel.getAssignedTo());
        entity.setAssignedBy(requestModel.getAssignedBy());
        entity.setCompleted(requestModel.isCompleted());
        entity.setStartDate(requestModel.getStartDate());
        entity.setEndDate(requestModel.getEndDate());
        taskRepository.save(entity);
        return modelMapper.map(entity,Dto.class);


    }
// It finds all the tasks assigned to particular assignedTo user.
    @Override
    public List<ResponseModel> findByAssignedTo(String assignedTo) {
        List<ResponseModel> list=new ArrayList<>();
        Iterable<UserTask> iterable= taskRepository.findByAssignedTo(assignedTo);
        Iterator<UserTask> iterator= iterable.iterator();
        while (iterator.hasNext())
        {
            list.add(modelMapper.map(iterator.next(), ResponseModel.class));
        }
        if(list.isEmpty()){
            throw new EmptyListException("list is empty");
        }
        return list;
    }


    @Override
    public Dto updateByAssignedTo(UpdateStatusModel updateStatusModel, String assignedTo) {
        UserTask userTask=taskRepository.findByUniqueTaskId(updateStatusModel.getUniqueTaskId());
        userTask.setCompleted(updateStatusModel.isCompleted());
        userTask=taskRepository.save(userTask);
        return modelMapper.map(userTask,Dto.class);

    }

    @Override
    public List<ResponseModel> findByAssignedBy(String assignedBy) {
        List<ResponseModel> list=new ArrayList<>();
        Iterable<UserTask> iterable= taskRepository.findByAssignedBy(assignedBy);
        Iterator<UserTask> iterator= iterable.iterator();
        while (iterator.hasNext())
        {
            list.add(modelMapper.map(iterator.next(), ResponseModel.class));
        }
        if(list.isEmpty()){
            throw new EmptyListException("list is empty");
        }
        return list;
    }


    private UserTask findTaskId(Integer uniqueTaskId) {

        UserTask userTask= taskRepository.findByUniqueTaskId(uniqueTaskId);
        return userTask;
    }
}
