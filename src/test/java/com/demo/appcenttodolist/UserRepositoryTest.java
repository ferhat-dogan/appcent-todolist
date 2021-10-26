package com.demo.appcenttodolist;

import static org.assertj.core.api.Assertions.assertThat;
import com.demo.appcenttodolist.entities.User;
import com.demo.appcenttodolist.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @Test
    public void saveUser(){
        User user = new User("Ali Yılmaz", "aliyilmaz@gmail.com", "123456");
        entityManager.persistAndFlush(user);
        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void deleteUsers(){
        entityManager.persistAndFlush(new User("Ali Yılmaz", "aliyilmaz@gmail.com", "123456"));
        entityManager.persistAndFlush(new User("Deniz Çetin", "denizcetin@gmail.com", "123456"));
        repository.deleteAll();
        assertThat(repository.findAll()).isEmpty();
    }
}
