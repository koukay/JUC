package com.houkai.juc.c_022_RefTypeAndThreadlocal.RefType;

import com.houkai.util.SleepUtil;

import java.io.IOException;
import java.lang.ref.SoftReference;

/**
 * 设置jvm内存
 * -Xms20M -Xmx20M
 * 软引用,当内存不够了,gc调用就会回收
 * 应用场景,做缓存,别人可以从缓存中读取,也可以利用这块空间
 */
public class T02_SoftReference {
    public static void main(String[] args) throws IOException {
        SoftReference<byte[]> s= new SoftReference<>(new byte[1024*1024*10]);
        //如果被回收了,就为空,没回收就不为空
        System.out.println(s.get());
        System.gc();
        SleepUtil.sleepMillis(500);
        //如果被回收了,就为空,没回收就不为空
        System.out.println(s.get());
        byte[] b = new byte[1024 * 1024 * 10];
        //如果被回收了,就为空,没回收就不为空 此处为空
        System.out.println(s.get());
    }
}
