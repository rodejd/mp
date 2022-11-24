package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.IntStream;

@Component
@Slf4j
public class Cote {

    /**
     * 없는 숫자 더하기
     */
    public void findInArraysInt() {

        int[] numbers = {1, 2, 3, 4, 5, 8, 9};

        int answer = 0;
        // 1 배열에 있는지 반복문 만큼 돌면서 확인 없는 순차 누적
        for (int i = 9; i >= 0; i--) {
            int cnt = i;
            if (IntStream.of(numbers).anyMatch(v -> v == cnt)) {
                continue;
            }
            answer += i;
        }

        // 2 (전체 값 더해서 배열에 값과 빼주기)
        int total = 45;
        for (int i : numbers) {
            total -= i;
        }

        System.out.println(answer);
    }


    /**
     * 잔액 구하기
     */
    public void lackMoney() {

        int money = 40;
        int count = 4;
        int price = 3;

        long answer = (long) money;

        for (long i = count; i > 0; i--) {
            answer -= (i * price);
        }

        if (answer > 0) {
//             잔액 0 이상
        }

//        return Math.max(price * (count * (count + 1) / 2) - money, 0);

        System.out.println(answer);

    }


    /*
     * 최소 공약수
     * */
    public static int divisor(int num) {

        int result = 0;

        for (int i = 1; i <= num; i++) {

            if (num % i == 1) {
                return i;
            }

        }
        return result;
    }


    /*
    *배열 값 쌍 이루기
    * */
    public void arrayCompare() {

        String answer = "";

        int[] intX = new int[10];
        int[] intY = new int[10];

        String x = "100";
        String y = "203045";

        for (String c : x.split("")) {
            // 해당 배열의 카운트
            // ex) 4 일경우 4번쨰 배열에 1 추가
            // 직접적으로 4를 넣는것이 아니라 포함되어 있음을 뜻함
            intX[Integer.parseInt(c)]++;
        }

        for (String c : y.split("")) {
            intY[Integer.parseInt(c)]++;
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 9; i >= 0; i--) {
            while (intX[i] > 0 && intY[i] > 0) {
                intX[i]--;
                intY[i]--;
                answer += i;
            }
        }

        System.out.println(answer);

    }


    /*
    * 한글로 변환하기
    * */
    public void checkHangeul(){

        String value = "one162twosixfour5five";

        String[] check = {"one","two","three","four","five","six","seven","eight","nine"};

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < check.length; i++){
            value = value.replaceAll(check[i], Integer.toString(i));
            System.out.println(Integer.toString(i));
        }

        System.out.println(value);


    }


    /*
    * 음수 양수 확인함수
    * */
    public void minusPlusCheck(){


        int[] test = {1,2,3,4,5,6,7,8,9};
        String[] testS = {"true","false","true","false","true","false","true","false","true"};
        boolean check = true;

        int result = 0;

        for(int i : test){
            test[i] = testS[i].equals("false") ? test[i] * -1 :  test[i];
            result += test[i];
        }

        System.out.println(result);


    }







}


