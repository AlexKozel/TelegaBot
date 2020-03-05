package com.example.JavaBot.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Component
public class LoadQuestions {

    @Autowired
    QuestionsRepository repository;

    public void load(String sourse) throws FileNotFoundException {

        Scanner in = new Scanner(new File(sourse));
        while (in.hasNextLine()) {
            String s = in.nextLine();
            String[] parts = s.split("\\|",2);

            Questions q = new Questions(parts[0].trim(), parts[1].trim());
            repository.save(q);
        }
    }
}
