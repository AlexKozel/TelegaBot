package com.example.JavaBot.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Users {
    @Id
    @GeneratedValue
    long id;
    String name;
    long points;

    public Users() {
    }
}
