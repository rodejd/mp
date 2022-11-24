package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void Test() {

        /*
        * [2, 1, 1, 2, 3, 1, 2, 3, 1]	2
          [1, 3, 2, 1, 2, 1, 3, 1, 2]	0
        * 1,2,3,4 순으로 정렬 되어 있을떄 리턴
        * 배열의 순서 확인
        * */
        int[] ingredient = {2, 1, 1, 2, 3, 1, 2, 3, 1};

        Stack<Integer> bugger = new Stack<>();

        int cnt = 0;

        for (int i = 0; i < ingredient.length; i++) {

            bugger.push(ingredient[i]);

            if (ingredient[i] == 1 && bugger.size() > 3) {
                if (bugger.get(bugger.size() - 2) == 3) {
                    if (bugger.get(bugger.size() - 3) == 2) {
                        if (bugger.get(bugger.size() - 4) == 1) {
                            bugger.pop();
                            bugger.pop();
                            bugger.pop();
                            bugger.pop();
                            cnt++;
                        }
                    }
                }
            }
        }

        System.out.print(cnt);


    }


    public static int[] remove(int[] a, int index) {
        return IntStream.range(0, a.length)
                .filter(i -> i != index)
                .filter(i -> i != index - 1)
                .filter(i -> i != index - 2)
                .filter(i -> i != index - 3)
                .map(i -> a[i])
                .toArray();
    }


    @Test
    public void test() {

        String number = "194246";
        int del = 3;

/*
        int[] intArrays = new int[number.length()];

        for(int i = 0; i< number.length(); i++){
            intArrays[i] = Integer.parseInt(number.split("")[i]);
        }


        int[] test = Arrays.copyOfRange(intArrays, intArrays.length - del, intArrays.length);


        int[] result = IntStream.of(test).boxed().sorted(Comparator.reverseOrder())
                .filter(o -> o >= 0)
                .mapToInt(i -> i)
                .toArray();

        StringBuilder answer = new StringBuilder();
        for(int i : test){
            answer.append(i);
        }

        int k = 3;
*/
        int k = 3;

        // 삭제될 배열 제외 하고 삽입
        char[] resultTest = new char[number.length() - k];

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < number.length(); i++) {
            Character c = number.charAt(i);
            while (!stack.isEmpty() && stack.peek() < c && k-- > 0) {
                stack.pop();
            }
            stack.push(c);
        }

        for (int i = 0; i < resultTest.length; i++) {
            resultTest[i] = stack.get(i);
        }
        System.out.println(Arrays.toString(resultTest));


    }


    @Test
    public void jjakSu() {

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


    @Test
    public void checkHangeul() {

        String value = "one162twosixfour5five";

        String[] check = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < check.length; i++) {
            value = value.replaceAll(check[i], Integer.toString(i));
            System.out.println(Integer.toString(i));
        }

        System.out.println(value);


    }


    @Test
    public void minusPlusCheck() {

        int[] test = {4, 7, 12};
        boolean[] testS = {true, false, true};

        int result = 0;

        for (int i = 0; i < test.length; i++) {
            result += testS[i] ? test[i] * -1 : test[i];
        }

        System.out.println(result);


    }


    @Test
    public void handPhoneTouchFinger() {

        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right";

        String result = "";

        List<Integer> leftHand = new ArrayList<>(Arrays.asList(1, 4, 7));
        List<Integer> rightHand = new ArrayList<>(Arrays.asList(3, 6, 9));

        int leftPosition = 0;
        int rightPosition = 0;

        for (int i = 0; i < numbers.length; i++) {

            if (leftHand.contains(numbers[i])) {
                result += "L";
                leftPosition = numbers[i];
                continue;
            }

            if (rightHand.contains(numbers[i])) {
                result += "R";
                rightPosition = numbers[i];
                continue;
            }

            if (leftPosition + 1 == numbers[i] && rightPosition - 1 != numbers[i]) {
                result += "L";
                leftPosition = numbers[i] - 1;
                continue;
            }

            if (leftPosition + 1 != numbers[i] && rightPosition - 1 == numbers[i]) {
                result += "R";
                rightPosition = numbers[i] - 1;
                continue;
            }

            // 중간에 있는 값이라면
            if (leftPosition + 1 == numbers[i] && rightPosition - 1 == numbers[i]) {
                result += hand.equals("right") ? "R" : "L";
            }

            if (leftPosition > rightPosition) {
                result += "L";
                leftPosition = numbers[i] - 1;
                continue;
            }

            if (leftPosition < rightPosition) {
                result += "R";
                rightPosition = numbers[i] - 1;
                continue;
            }


        }

        System.out.println(result);
    }


    @Test
    public void oneMorePhone() {

/*        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right";*/

        int[] numbers = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
        String hand = "left";

        int[][] pos = {
                {0, 0}, {0, 1}, {0, 2},
                {1, 0}, {1, 1}, {1, 2},
                {2, 0}, {2, 1}, {2, 2},
                {3, 0}, {3, 1}, {3, 2}
        };

        int[] nowLeft = {3, 0};
        int[] nowRight = {3, 2};

        StringBuilder stringBuilder = new StringBuilder();


        /*
        *
        * [1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5]	"right"	"LRLLLRLLRRL"  LRLLLRLLRRL
        * [7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2]	"left"	"LRLLRRLLLRR" LRLLRRLLLRR
        * [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]	"right"	"LLRLLRLLRL"
        *
        *
        * */



        for (int i = 0; i < numbers.length; i++) {

            switch (numbers[i]) {
                case 0:
                    if (getDistance(nowLeft, nowRight, hand, new int[]{3, 1}).equals("L")) {
                        nowLeft = new int[]{3, 1};
                        stringBuilder.append("L");
                        break;
                    } else {
                        nowRight = new int[]{3, 1};
                        stringBuilder.append("R");
                        break;
                    }
                case 1:
                case 4:
                case 7:
                    stringBuilder.append("L");
                    nowLeft = pos[numbers[i] - 1];
                    break;
                case 3:
                case 6:
                case 9:
                    stringBuilder.append("R");
                    nowRight = pos[numbers[i] - 1];
                    break;
                case 2:
                case 5:
                case 8:
                    if (getDistance(nowLeft, nowRight, hand, pos[numbers[i] - 1]).equals("L")) {
                        nowLeft = pos[numbers[i] - 1];
                        stringBuilder.append("L");
                        break;
                    } else {
                        nowRight = pos[numbers[i] - 1];
                        stringBuilder.append("R");
                        break;
                    }
            }
        }

        System.out.println(stringBuilder.toString());

    }

    public String getDistance(int[] leftNow, int[] rightNow, String hand, int[] num) {

        if(leftNow[0] == num[0] && leftNow[1] == num[1]){
            return "L";
        }
        if(rightNow[0] == num[0] && rightNow[1] == num[1]){
            return "R";
        }

        if (leftNow[0] == num[0] && rightNow[0] == num[0]) {
            return hand.equals("right") ? "R" : "L";
        }

        int a = Math.abs(leftNow[0] - num[0]) + Math.abs(leftNow[1] - num[1]);
        int b = Math.abs(rightNow[0] - num[0]) + Math.abs(rightNow[1] - num[1]);

        if (a == b) {
            return hand.equals("right") ? "R" : "L";
        }

        if (a > b) {
            return "R";
        } else {
            return "L";
        }

    }


}