package com.example.user_task;

import com.example.user_task.exception.ErrorResponseModel;
import com.example.user_task.model.UserTask;
import com.example.user_task.repo.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
@EnableEurekaClient
public class UserTaskApplication implements CommandLineRunner {
    private final TaskRepository taskRepository;

    public UserTaskApplication(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(UserTaskApplication.class, args);
    }
@Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public ErrorResponseModel errorResponseModel()
    {
        return  new ErrorResponseModel();
    }

    @Override
    public void run(String... args) throws Exception {
       // taskRepository.save(new UserTask(1,"ProjectXYZ","Infrastructure Project",true,"02-03-22","31-03-22","Manager1","Abhinav"));
    }
}
