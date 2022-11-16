package com.mybroker.front.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.stream.IntStream;

@SpringBootApplication
public class FrontApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(FrontApiApplication.class, args);

        System.out.println("TEST");

        int[] numbers = {1,2,5,6,7,9};

        int answer = 0;
        for(int i = 9; i >= 0; i--){
            int cnt = i;
            if(IntStream.of(numbers).anyMatch(v -> v == cnt)){
                continue;
            }
            answer += i;
        }


        long test = 0;

        int price = 6;
        int money = 65;
        int count = 8;


        for(int i = 0; i <= count; i++){
            money -= (i * price);
        }




    }






}
