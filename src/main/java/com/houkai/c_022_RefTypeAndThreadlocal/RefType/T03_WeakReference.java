package com.houkai.c_022_RefTypeAndThreadlocal.RefType;

import com.houkai.util.SleepUtil;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 弱引用,遇到gc就会回收
 */
public class T03_WeakReference {
    public static void main(String[] args) throws IOException {
        WeakReference<M> m = new WeakReference<>(new M());
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());


        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();//不用了就要remove,不然也会内存泄漏
    }
}
