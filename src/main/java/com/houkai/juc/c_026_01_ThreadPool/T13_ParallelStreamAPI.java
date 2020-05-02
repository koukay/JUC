package com.houkai.juc.c_026_01_ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * nums.parallelStream().forEach(T13_ParallelStreamAPI::isPrime);
 */
public class T13_ParallelStreamAPI {
    public static void main(String[] args) {
        List<Integer> nums= new ArrayList<>();

        Random r = new Random();
        for (int i = 0; i < 10000; i++) {
            nums.add(1000000+r.nextInt(1000000));
        }
        //线性计算
        long start = System.currentTimeMillis();
        nums.forEach(v->isPrime(v));
        long end = System.currentTimeMillis();
        System.out.println(end-start);

        //流式计算
        start = System.currentTimeMillis();
        nums.parallelStream().forEach(T13_ParallelStreamAPI::isPrime);
        end = System.currentTimeMillis();
        System.out.println(end-start);
    }
    static boolean isPrime(int num) {
        for(int i=2; i<=num/2; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}
