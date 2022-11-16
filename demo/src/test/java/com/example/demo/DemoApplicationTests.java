package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;

import java.util.*;
import java.util.stream.IntStream;

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

        for(int i = 0; i < ingredient.length; i++){

            bugger.push(ingredient[i]);

            if(ingredient[i] == 1 && bugger.size() > 3){
                if(bugger.get(bugger.size()-2)==3){
                    if(bugger.get(bugger.size()-3)==2){
                        if (bugger.get(bugger.size()-4)==1){
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


}
