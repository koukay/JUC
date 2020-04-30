package com.houkai.c_022_RefTypeAndThreadlocal.RefType;

import com.houkai.util.SleepUtil;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

/**
 * 虚引用,一般用不到,用来回收对外内存的
 */
public class T04_PhantomReference {
    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue QUEUE= new ReferenceQueue();

    public static void main(String[] args) throws IOException {
        PhantomReference<M> phantomReference= new PhantomReference<>(new M(),QUEUE);
        new Thread(()->{
            while (true){
                LIST.add(new byte[1024*1024]);
                SleepUtil.sleepSecond(1);
                System.out.println(phantomReference);
            }
        }).start();
        new Thread(()->{
            while (true){
                Reference<? extends M> poll = QUEUE.poll();
                if (poll!=null){
                    System.out.println("----  虚引用对象调用JVM回收了  -----"+poll);
                }
            }
        }).start();
        SleepUtil.sleepMillis(500);
    }
}
