package com.mashibing;

public class T_02 {
    public static void main(String[] args) {
        T_02 t_02=new T_02();
        t_02.m(3);
    }
    public  int m(int n){
        if (n==1) return 2;
         return m(n-1);
    }
}
