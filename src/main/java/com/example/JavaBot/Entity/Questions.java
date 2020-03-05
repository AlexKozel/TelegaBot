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

    public Questions(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
}
