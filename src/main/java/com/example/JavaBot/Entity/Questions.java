package com.example.JavaBot.Entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Component
public class Questions {

    @Id
    @GeneratedValue
    private long id;
    private String question;
    private String answer;

    public Questions() {
    }

    public Questions(String question, String answer){
        this.question = question;
        this.answer = answer;
    }
}
