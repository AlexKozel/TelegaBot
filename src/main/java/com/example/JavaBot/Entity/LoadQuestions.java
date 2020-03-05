package com.example.JavaBot.Entity;

import com.example.JavaBot.TestDb.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Component
public class LoadQuestions {

    @Autowired
    ApplicationContext context;

    @Autowired
    QuestionsRepository repository;


    public void load(String sourse) throws FileNotFoundException {

        Scanner in = new Scanner(new File(sourse));
        Login login = new Login();
        while (in.hasNextLine()) {
            String s = in.nextLine();
            String[] parts = s.split("\\|",2);

            Questions q = new Questions(parts[0].trim(), parts[1].trim());
            System.out.println(login.setQuestion(parts));
        }
        System.out.println();
    }
}
