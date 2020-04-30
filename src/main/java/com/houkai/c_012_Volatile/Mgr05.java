package com.houkai.c_012_Volatile;

import java.util.concurrent.TimeUnit;

/**
 * 细粒度锁
 * 发现不可行,
 */
public class Mgr05 {
    private static Mgr05 m;
    //构造方法私有,不允许new,保证单例
    private Mgr05(){

    }
    public static Mgr05 getInstance(){
        if (m==null){
            synchronized (Mgr05.class){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                m=new Mgr05();
            }
        }
        return m;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()-> System.out.println(Mgr05.getInstance().hashCode())).start();;
        }
    }
}
