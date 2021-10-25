package com.demo.appcenttodolist.repositories;

import com.demo.appcenttodolist.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
