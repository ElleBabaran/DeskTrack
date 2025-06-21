package io.reflectoring.demo.entity;

import jakarta.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String toDo;

    private String userID;

    public Task() {}

    public Task(String toDo) {
        this.toDo = toDo;
    }

    public Task(String toDo, String userID) {
        this.toDo = toDo;
        this.userID = userID;
    }

    public Long getId() {
        return id;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
