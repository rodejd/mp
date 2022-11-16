package com.example.demo;

import algorithm.Cote;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);


        Cote cote = new Cote();

        cote.findInArraysInt();



    }

}
