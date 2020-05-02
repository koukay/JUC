package com.houkai.juc.c_022_RefTypeAndThreadlocal.RefType;

/**
 * 为了看到是否被回收,如果回收就会打印finalize
 */
public class M {
    @Override
   protected void finalize(){
        System.out.println("finalize");
   }
}
