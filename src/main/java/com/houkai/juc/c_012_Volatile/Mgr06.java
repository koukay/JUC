package com.houkai.juc.c_012_Volatile;

import java.util.concurrent.TimeUnit;

/**
 * 细粒度锁
 * 发现不可行,解决办法,双重检查锁
 */
public class Mgr06 {
    private static Mgr06 m;
    //构造方法私有,不允许new,保证单例
    private Mgr06(){

    }
    public static Mgr06 getInstance(){
        if (m==null){
            synchronized (Mgr06.class){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (m==null){
                    /**
                     * 该语句做了三件事情
                     * 1.为Mgr06分配内存
                     * 2.为Mgr06赋初始值
                     * 3.赋值给m
                     * 指令重排序的理解,本来先分配内存,默认初始化,然后再初始化,最后赋值给m,重排序后可能将第二第三步交换顺序
                     */
                    m=new Mgr06();
                }
            }
        }
        return m;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()-> System.out.println(Mgr06.getInstance().hashCode())).start();;
        }
    }
}
