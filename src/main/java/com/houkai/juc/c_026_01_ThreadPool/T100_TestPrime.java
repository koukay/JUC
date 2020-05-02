package com.houkai.juc.c_026_01_ThreadPool;

import java.util.ArrayList;
import java.util.List;

/**
 * 质数大于1的自然数,并且只能被1和它本身整除
 */
public class T100_TestPrime {
    public static void main(String[] args) {

        List<Integer> prime = getPrime(0, 10);
        prime.forEach(a-> System.out.println(a));
        for (int i = 0; i < 10; i++) {
            System.out.println(i+" "+isPrime(i));
        }
    }
    /**
     * 判断该数是不是质数
     * @param num
     * @return
     */
    static boolean isPrime(int num){
        if (num<2) return false;
        for (int i = 2; i <= num/2 ; i++) {
            if (num % i==0)return false;
        }
        return true;
    }

    /**
     * 拿到一段区间的质数
     * @param start
     * @param end
     * @return
     */
    static List<Integer> getPrime(int start, int end){
        if (start<2)start=2;
        List<Integer> list= new ArrayList<>();
        for (int i = start; i <=end ; i++) {
            if (isPrime(i)) list.add(i);
        }
        return list;
    }
}
