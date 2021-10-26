package com.demo.appcenttodolist.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;
    private String state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "todolist_id")
    private TodoList todoList;

    public Task(String text, String state, TodoList todoList){
        this.text = text;
        this.state = state;
        this.todoList = todoList;
    }
}
