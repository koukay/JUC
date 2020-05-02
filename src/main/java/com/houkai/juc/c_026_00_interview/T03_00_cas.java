package com.houkai.juc.c_026_00_interview;

/**
 * 交叉打印1a2b3c4d5e
 * volatile
 */
public class T03_00_cas {
    enum ReadyToRun {T1,T2}
    static volatile ReadyToRun r= ReadyToRun.T1;
    static Thread t1,t2;
    public static void main(String[] args) {
        char[] aI={'1','2','3','4','5','6'};
        char[] aC={'a','b','c','d','e','f'};
       t1=new Thread(()->{
           for (char c : aI) {
               while (r!=ReadyToRun.T1){}
               System.out.println(c);
               r=ReadyToRun.T2;
           }


       },"t1");
       t2=new Thread(()->{
           for (char c : aC) {
               while (r!=ReadyToRun.T2){}
               System.out.println(c);
               r=ReadyToRun.T1;
           }
       },"t2");
       t1.start();
       t2.start();
    }
}
