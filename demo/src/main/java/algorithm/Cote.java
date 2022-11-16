package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Component
@Slf4j
public class Cote {

    /**
    * 없는 숫자 더하기
    * */
    public void findInArraysInt(){

        int[] numbers = {1,2,3,4,5,8,9};

        int answer = 0;
        // 1 배열에 있는지 반복문 만큼 돌면서 확인 없는 순차 누적
        for(int i = 9; i >= 0; i--){
            int cnt = i;
            if(IntStream.of(numbers).anyMatch(v -> v == cnt)){
                continue;
            }
            answer += i;
        }

        // 2 (전체 값 더해서 배열에 값과 빼주기)
        int total = 45;
        for(int i : numbers) {
            total -= i;
        }

        System.out.println(answer);
    }


    /**
     * 잔액 구하기
     * */
    public void lackMoney(){

        int money = 40;
        int count = 4;
        int price = 3;

        long answer = (long) money;

        for(long i = count; i > 0; i--){
            answer -= (i * price);
        }

        if(answer > 0){
//             잔액 0 이상
        }

//        return Math.max(price * (count * (count + 1) / 2) - money, 0);

        System.out.println(answer);

    }










}


