package com.houkai.juc.c_021_02_AQS;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * AQS直接操作二进制文件,效率非常高
 * 8
 * 9
 * 10
 * 20
 */
public class T01_HelloVarHandle {
    int x=8;
    private static VarHandle handle;
    static {
        try {
            //拿到类的引用
            handle=  MethodHandles.lookup().findVarHandle(T01_HelloVarHandle.class,"x",int.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        T01_HelloVarHandle t= new T01_HelloVarHandle();
        //获取对象属性值
        System.out.println(handle.get(t));
        //给对象属性设值
        handle.set(t,9);
        System.out.println(t.x);
        //cas操作
        handle.compareAndSet(t,9,10);
        System.out.println(t.x);
        //加法操作
        handle.getAndAdd(t,10);
        System.out.println(t.x);
    }
}
