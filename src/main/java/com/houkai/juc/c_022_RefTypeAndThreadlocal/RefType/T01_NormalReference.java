package com.houkai.juc.c_022_RefTypeAndThreadlocal.RefType;

import java.io.IOException;

public class T01_NormalReference {
    public static void main(String[] args) throws IOException {
        M m = new M();//new的,不会被回收.因为有m指向它,
        m=null;//m值设置为null,会被回收
        System.gc();
        System.in.read();
    }
}
