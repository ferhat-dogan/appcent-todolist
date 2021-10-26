package com.demo.appcenttodolist.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<TodoList> todoLists;

    public User(String fullName, String email, String password){
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }
}
