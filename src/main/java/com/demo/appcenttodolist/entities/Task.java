package com.demo.appcenttodolist.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String text;
    private String state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "todolist_id")
    private TodoList todoList;
}
