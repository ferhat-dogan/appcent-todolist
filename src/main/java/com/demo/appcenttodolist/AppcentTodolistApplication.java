package com.demo.appcenttodolist;

import com.demo.appcenttodolist.entities.Task;
import com.demo.appcenttodolist.entities.TodoList;
import com.demo.appcenttodolist.entities.User;
import com.demo.appcenttodolist.repositories.TaskRepository;
import com.demo.appcenttodolist.repositories.TodoListRepository;
import com.demo.appcenttodolist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppcentTodolistApplication {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TodoListRepository todoListRepo;

    @Autowired
    private TaskRepository taskRepo;

    public static void main(String[] args) {
        SpringApplication.run(AppcentTodolistApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(){
        User userDemo = new User("Ferhat Doğan", "ferhat.fda8@gmail.com", "$2a$10$zstxwjy/tIMkPDW0AG1ANe3UeXCpuORyXaVWIb2VyPs/74Pdhv0mC");
        TodoList todoListDemo = new TodoList("List 1", userDemo);

        return args -> {
            userRepo.save(userDemo);
            todoListRepo.save(todoListDemo);
            taskRepo.save(new Task("Task 1 Uncompleted", "uncompleted", todoListDemo));
            taskRepo.save(new Task("Task 2 Completed", "completed", todoListDemo));
        };
    }
}
