package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest {


    @Test
    public void testGetShortLinkWithNull() {
        Integer a = 10;
        Integer b = 20;

        b = a + b;
        a = b - a;
        b = b - a;
        //output b =10;a=20;
    }

    /**
     * O(n) O(N*N)
     *
     * Write a function that returns an array with each index being the product of all the other numbers in the input array except the one with that index.
     *
     * So, given the array: [1,2,3,4,5]
     * Index 0 should exclude 1 and be 2*3*4*5 = 120
     * Index 1 should exclude 2 and be 1*3*4*5 = 60
     * Index 2 should exclude 3 and be 1*2*4*5 = 40
     * Index 3 should exclude 4 and be 1*2*3*5 = 30
     * Index 4 should exclude 5 and be 1*2*3*4 = 24
     * Final Answer: [120, 60, 40, 30, 24]
     */
    @Test
    public void testTest2() {
        Integer[] number = new Integer[5];
        number[0] = 0;
        number[1] = 2;
        number[2] = 3;
        number[3] = 4;
        number[4] = 5;

        int result = 1;
        int zeroCount = 0;
        for (int i = 0; i < 5; i++) {
            if(number[i] == 0 && zeroCount > 0) {
                zeroCount++;
            } else {
                result = result * number[i];
            }
        }

        Integer[] resultArray = new Integer[5];
        for (int i = 0; i < 5; i++) {
            if(number[i] == 0) {
                resultArray[i] = result;
            } else {
                resultArray[i]= result / number[i];
            }
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(resultArray[i]);
        }
    }



}