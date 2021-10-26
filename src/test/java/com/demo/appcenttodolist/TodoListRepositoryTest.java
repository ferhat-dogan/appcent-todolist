package com.demo.appcenttodolist;

import static org.assertj.core.api.Assertions.assertThat;
import com.demo.appcenttodolist.entities.TodoList;
import com.demo.appcenttodolist.entities.User;
import com.demo.appcenttodolist.repositories.TodoListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class TodoListRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    TodoListRepository repository;

    @Test
    public void saveTodoList(){
        User user = new User("Ali Yılmaz", "aliyilmaz@gmail.com", "123456");
        entityManager.persist(user);
        TodoList todoList = new TodoList("Test List", user);
        entityManager.persistAndFlush(todoList);
        assertThat(todoList.getId()).isNotNull();
    }

    @Test
    public void deleteTodoLists(){
        User user1 = new User("Ali Yılmaz", "aliyilmaz@gmail.com", "123456");
        User user2 = new User("Deniz Çetin", "denizcetin@gmail.com", "123456");
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persistAndFlush(new TodoList("Test List 1", user1));
        entityManager.persistAndFlush(new TodoList("Test List 2", user2));
        repository.deleteAll();
        assertThat(repository.findAll()).isEmpty();
    }
}
