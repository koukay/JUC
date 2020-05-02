package com.houkai.juc.c_012_Volatile;

import java.util.concurrent.TimeUnit;

/**
 * 恶汉式,初始化时创建实例
 * d多线程出问题,发现创建的实例是多个,不是单例的,解决办法,加锁
 */
public class Mgr03 {
    private static Mgr03 m;
    //构造方法私有,不允许new,保证单例
    private Mgr03(){

    }
    public static Mgr03 getInstance(){
        if (m==null){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            m=new Mgr03();
        }
        return m;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()-> System.out.println(Mgr03.getInstance().hashCode())).start();;
        }
    }
}
