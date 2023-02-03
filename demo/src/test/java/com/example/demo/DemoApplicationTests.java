package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;
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

        if (leftNow[0] == num[0] && leftNow[1] == num[1]) {
            return "L";
        }
        if (rightNow[0] == num[0] && rightNow[1] == num[1]) {
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


    @Test
    public void highScoreReturn() {

        int[] score = {10, 100, 20, 150, 1, 100, 200};
        int k = 3;

        int[] result = new int[score.length];

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        int temp = 0;

        for (int i = 0; i < score.length; i++) {

            priorityQueue.add(score[i]);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }

            result[i] = priorityQueue.peek();
        }

        System.out.println(Arrays.toString(result));

    }


    @Test
    public void orangeSmallCheck() {

       /* int[] arr = new int[10000001];
        for(int i = 0; i < tangerine.length; i++){
            arr[tangerine[i]]++;
        }
        Arrays.sort(arr);
        int sum = 0;
        int answer = 0;
        for(int i = arr.length-1; i >= 0; i--){
            sum += arr[i];
            answer++;
            if(sum >= k){
                break;
            }
        }*/

/*
        int k = 2;
        int[] tangerine = {1, 1, 1, 1, 2, 2, 2, 3};*/


        int k = 6;
        int[] tangerine = {5, 3, 3, 5, 5, 5, 2, 3};

        int result = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i : tangerine) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(map.entrySet());

        list.sort((e1, e2) -> e2.getValue() - e1.getValue());

        for (Map.Entry<Integer, Integer> i : list) {
            k -= i.getValue();
            result++;
            if (k >= 0) {
                break;
            }
        }

        System.out.println(result);
    }


    @Test
    public void antAttack() {
//        int answer = 0;

        int general = 5;
        int sodier = 3;

        int hp = 19;

        /*while(hp != 0){

            if(hp >= general){
                hp -= general;
                answer++;
                continue;
            }

            if(hp >= sodier){
                hp -= sodier;
                answer++;

            } else {
                hp --;
                answer++;
            }

        }*/

        int answer = hp / 5;
        hp %= 5;

        answer += hp / 3;
        hp %= 3;

        answer += hp / 1;


        System.out.println(answer);

    }


    @Test
    public void aeiou() {

        String my_string = "bus";
        String[] answer = {"a", "e", "i", "o", "u"};

        Iterator temp = Arrays.stream(answer).iterator();
        while (temp.hasNext()) {
            my_string = my_string.replaceAll(String.valueOf(temp.next()), "");
        }
        System.out.println(my_string);


    }

    @Test
    public void reverseOrder() {

        int a = 12345;


        StringBuilder sb = new StringBuilder();
        sb.append(a).reverse();

        int[] answer = new int[sb.length()];

        for (int i = 0; i < sb.length(); i++) {
            answer[i] = Integer.parseInt(sb.substring(i, i + 1));
        }

        System.out.println(Arrays.toString(answer));


    }


    @Test
    public void upperLowerCase() {

        String s = "abc abc abc ";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {

            if (i % 2 == 0) {
                result.append(String.valueOf(s.charAt(i)).toUpperCase(Locale.ROOT));
            } else {
                result.append(String.valueOf(s.charAt(i)).toLowerCase(Locale.ROOT));
            }

        }

        System.out.printf(result.toString());

    }

    @Test
    public void compareTwoNumberArrays() {


        String t = "500220839878";
        String p = "7";

        int answer = 0;

        for (int i = 0; i < t.length() - p.length() + 1; i++) {
            System.out.println(p.length() + i);
            if (Long.parseLong(t.substring(i, p.length() + i)) <= Long.parseLong(p)) {
                answer++;
            }
            ;
        }


        System.out.println("answer : " + answer);

//        String[] a = {t.substring(0,2), }


    }


    @Test
    public void removeStringPair() {
        int answer = 0;

        String s = "abbcdaadca";

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (!stack.isEmpty() || stack.peek().equals(c)) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        if (stack.size() == 0) {
            answer = 1;
        }

        System.out.println(answer);

    }

    @Test
    public void postBox() {

        Stack<Integer> subStack = new Stack<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int[] order = {4, 3, 1, 2, 5};
        int answer = 0;
        int nowBox = 1;

        for (int i = 1; i <= order.length; i++) {
            queue.add(order[i]);
        }


        System.out.println(answer);


    }


    @Test
    public void asdasd() {
        int[] order = {4, 3, 1, 2, 5};
        int answer = 0;
        Stack<Integer> assistance = new Stack<>();
        int i = 0;

        for (int box = 1; box <= order.length; box++) {
            if (order[i] != box) {
                assistance.add(box);
                continue;
            }

            i++;
            answer++;

            while (assistance.size() != 0 && order[i] == assistance.peek()) {
                assistance.pop();
                i++;
                answer++;
            }
        }

        System.out.println(answer);

    }


    @Test
    public void findPair() {


        int[] poke = {3, 3, 1, 2, 3, 2};

        int answer = 0;

/*              [3,1,2,3]	2
                [3,3,3,2,2,4]	3
                [3,3,3,2,2,2]	2*/

        int temp = 0;

        for (int i = 0; i < poke.length; i++) {

            if (poke[temp] != poke[i]) {

            }

        }


    }


    @Test
    public void stringCheck() {

        String s = "banana";
//        "banana"	[-1, -1, -1, 2, 2, 2]
//        "foobar"	[-1, -1, 1, -1, -1, -1]

        int[] result = new int[s.length()];
        String temp = "";
        for (int i = 0; i < s.length(); i++) {

            if (i == 0) {
                result[i] = -1;
                temp += s.charAt(i);
                continue;
            }

            if (temp.indexOf(s.charAt(i)) == -1) {
                result[i] = -1;
                temp += s.charAt(i);
                continue;
            }

            if (temp.indexOf(s.charAt(i)) > -1) {
                result[i] = i - temp.lastIndexOf(s.charAt(i));
                temp += s.charAt(i);
            }
        }

        System.out.println(Arrays.toString(result));


    }


    @Test
    public void getCalender() {

        int a = 5;
        int b = 24;

        LocalDate localDate = LocalDate.of(2016, a, b);

        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        System.out.println(dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US).toUpperCase(Locale.ROOT));

    }

    @Test
    public void getCenterString() {

        String a = "abcdde";

        String answer = "";

        if (a.length() % 2 != 0) {
            answer += a.charAt(a.length() / 2);
        } else {
            answer += a.charAt(a.length() / 2 - 1);
            answer += a.charAt(a.length() / 2);
        }

        System.out.println(answer);

    }

    @Test
    public void removeSameNumberInArray() {

        int[] arr = {1, 1, 3, 3, 0, 1, 1};

/*        String temp = "";

        for(int i = 0; i < arr.length; i++){
            if(temp == ""){
                temp += arr[i];
                continue;
            }
            if(arr[i-1] != arr[i]){
                temp += arr[i];
            }
        }

        int[] answer = Stream.of(temp.split("")).mapToInt(Integer::parseInt).toArray();*/


        Stack<Integer> result = new Stack<Integer>();

        for (int i = 0; i < arr.length; i++) {

            if (result.isEmpty()) {
                result.push(arr[i]);
                continue;
            }

            if (result.peek() != arr[i]) {
                result.push(arr[i]);
            }

        }

        int[] answer = new int[result.size()];

        for (int i = result.size() - 1; i >= 0; i--) {
            answer[i] = result.peek();
            result.pop();
        }

        System.out.println(Arrays.toString(answer));
        System.out.println(result);

    }


    @Test
    public void arrayElements() {

        int[] arr = {2, 36, 1, 3};
        int divisor = 1;


        int[] test = Arrays.stream(arr).filter(f -> f % divisor == 0).toArray();

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % divisor == 0) {
                queue.add(arr[i]);
            }
        }

        int[] answer;

        if (queue.size() == 0) {
            answer = new int[]{-1};
        } else {
            answer = new int[queue.size()];

            for (int i = 0; i < queue.size() + i; i++) {
                answer[i] = queue.peek();
                queue.poll();
            }
        }

        System.out.println(Arrays.toString(answer));

    }

    @Test
    public void betweenNumber() {

        int a = 3;
        int b = 7;

        int result = 0;

        for (int i = Math.min(a, b); i <= Math.max(a, b); i++) {
            result = result + i;
        }

        System.out.println(result);
    }

    @Test
    public void stringSort() {

        String[] a = {"sun", "bed", "car"};
        int n = 1;

        String[] result = new String[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = String.valueOf(a[i].charAt(n));
        }

        Stream<String> check = Arrays.stream(result).sorted(Comparator.reverseOrder());

        String[] output = new String[a.length];
        for (int i = 0; i < a.length; i++) {
            int idx = i;

//            output[i] = Arrays.stream(a).filter(t -> t.charAt(n) == check.filter(s -> s.equals(t))).findFirst();
        }

    }


    @Test
    public void upperSort() {
        String answer = "";

        String s = "pPoooyY";

        s = s.toLowerCase(Locale.ROOT);

        String upper = "";

        if (charCnt(s, 'p') - charCnt(s, 'y') == 0) {
            System.out.println("True");
        }

    }


    public int charCnt(String s, char c) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.toLowerCase(s.charAt(i)) == c || Character.toUpperCase(s.charAt(i)) == c) {
                cnt++;
            }
        }

        return cnt;
    }


    @Test
    public void findKimSeoBang() {


        String[] s = {"Jane", "Kim"};

        String result = "김서방은 %s에 있다";

        for (int i = 0; i < s.length; i++) {
            if (s[i] == "Kim") {
                result = String.format(result, i);
            }
        }

        System.out.println(result);

    }


    public String solution(String[] seoul) {
        String answer = "";
        String result = "김서방은 %s에 있다";

        for (int i = 0; i < seoul.length; i++) {
            if (seoul[i] == "Kim") {
                answer = String.format(result, i);
            }
        }

        return answer;
    }



}