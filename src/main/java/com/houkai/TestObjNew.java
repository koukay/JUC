package com.houkai;

/**
 * 测试
 */
public class TestObjNew {
    public static void main(String[] args) {
        System.out.println("m1 "+m1());//10.24
        System.out.println("m2 "+m2());//6.11

    }
    public static long m1(){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Object o= new Object();
        }
        long end = System.currentTimeMillis();
        return end-start;
    }
    public static long m2(){
        long start = System.currentTimeMillis();
        Object o=null;
        for (int i = 0; i < 100; i++) {
            o=new Object();
        }
        long end = System.currentTimeMillis();
        return end-start;
    }
}
