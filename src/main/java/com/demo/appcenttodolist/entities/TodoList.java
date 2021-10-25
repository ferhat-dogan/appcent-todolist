package com.demo.appcenttodolist.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class TodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "todoList")
    private List<Task> tasks;
}
