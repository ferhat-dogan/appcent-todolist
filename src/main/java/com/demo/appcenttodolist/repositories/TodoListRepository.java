package com.demo.appcenttodolist.repositories;

import com.demo.appcenttodolist.entities.TodoList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepository extends CrudRepository<TodoList, Integer> {

}
