package com.demo.appcenttodolist;

import static org.assertj.core.api.Assertions.assertThat;
import com.demo.appcenttodolist.entities.Task;
import com.demo.appcenttodolist.entities.TodoList;
import com.demo.appcenttodolist.entities.User;
import com.demo.appcenttodolist.repositories.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    TaskRepository repository;

    @Test
    public void saveTask(){
        User user = new User("Ali Yılmaz", "aliyilmaz@gmail.com", "123456");
        entityManager.persist(user);
        TodoList todoList = new TodoList("Test List", user);
        entityManager.persist(todoList);
        Task task = new Task("Test Task", "completed", todoList);
        entityManager.persistAndFlush(task);
        assertThat(task.getId()).isNotNull();
    }

    @Test
    public void deleteTasks(){
        User user1 = new User("Ali Yılmaz", "aliyilmaz@gmail.com", "123456");
        User user2 = new User("Deniz Çetin", "denizcetin@gmail.com", "123456");
        entityManager.persist(user1);
        entityManager.persist(user2);
        TodoList todoList1 = new TodoList("Test List 1", user1);
        TodoList todoList2 = new TodoList("Test List 2", user2);
        entityManager.persist(todoList1);
        entityManager.persist(todoList2);
        repository.deleteAll();
        assertThat(repository.findAll()).isEmpty();
    }
}
