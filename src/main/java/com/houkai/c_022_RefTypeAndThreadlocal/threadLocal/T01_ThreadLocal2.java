package com.houkai.c_022_RefTypeAndThreadlocal.threadLocal;

import com.houkai.util.SleepUtil;

/**
 *null
 * threadlocal里面放的对象是相互独立的
 */
public class T01_ThreadLocal2 {
   /* 源码
   public T get() {
   //获取当前线程
        Thread t = Thread.currentThread();
        //获取当前线程的map
        ThreadLocal.ThreadLocalMap map = getMap(t);
        if (map != null) {
            ThreadLocal.ThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null) {
                @SuppressWarnings("unchecked")
                T result = (T)e.value;
                return result;
            }
        }
        return setInitialValue();
    }
    private T setInitialValue() {
        T value = initialValue();
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null) {
            map.set(this, value);
        } else {
            createMap(t, value);
        }
        if (this instanceof TerminatingThreadLocal) {
            TerminatingThreadLocal.register((TerminatingThreadLocal<?>) this);
        }
        return value;
    }*/
    static ThreadLocal<Person1>  t1= new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(()->{
                SleepUtil.sleepSecond(2);
                System.out.println(t1.get());
        }).start();
        new Thread(()->{
                SleepUtil.sleepSecond(1);
            t1.set(new Person1());
        }).start();
    }
}

class Person1{
    String name="houkai";
}