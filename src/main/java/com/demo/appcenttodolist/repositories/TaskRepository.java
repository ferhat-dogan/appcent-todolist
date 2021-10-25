package com.demo.appcenttodolist.repositories;

import com.demo.appcenttodolist.entities.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {

}
