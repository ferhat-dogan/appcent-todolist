package com.demo.appcenttodolist.repositories;

import com.demo.appcenttodolist.entities.TodoList;
import org.springframework.data.repository.CrudRepository;

public interface TodoListRepository extends CrudRepository<TodoList, Integer> {

}
