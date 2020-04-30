package com.houkai.c_012_Volatile;

/**
 * 恶汉式,初始化时创建实例
 */
public class Mgr02 {
    private static final Mgr02 m;
    static {
        m=new Mgr02();
    }
    //构造方法私有,不允许new,保证单例
    private Mgr02(){

    }
    public static Mgr02 getInstance(){
        return m;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        Mgr02 m1=Mgr02.getInstance();
        Mgr02 m2=Mgr02.getInstance();
        System.out.println(m1==m2);
    }
}
