package com.houkai.juc.c_012_Volatile;

/**
 * 恶汉式,是单例
 */
public class Mgr01 {
    private static final Mgr01 m= new Mgr01();
    //构造方法私有,不允许new,保证单例
    private Mgr01(){

    }
    public static Mgr01 getInstance(){
        return m;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        Mgr01 m1=Mgr01.getInstance();
        Mgr01 m2=Mgr01.getInstance();
        System.out.println(m1==m2);
    }
}
