package com.houkai.juc.c_026_00_interview;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 交叉打印1a2b3c4d5e
 * 管道输入输出流,在多线程之间可以相互传输
 * in1.connect(out2); //第一个线程的in1和out2链接起来
 * in2.connect(out1);//第二个线程的in2和out1链接起来
 */
public class T10_00_PipedStream {
    static Thread t1,t2;
    public static void main(String[] args) throws IOException {
        char[] aC={'1','2','3','4','5','6'};
        char[] aI={'a','b','c','d','e','f'};

        PipedInputStream in1= new PipedInputStream();
        PipedInputStream in2= new PipedInputStream();
        PipedOutputStream out1= new PipedOutputStream();
        PipedOutputStream out2= new PipedOutputStream();

        in1.connect(out2); //第一个线程的in1和out2链接起来
        in2.connect(out1);//第二个线程的in2和out1链接起来
        String msg="Your Turn";
       t1=new Thread(()->{
           byte[] buffer= new byte[9];
           for (char c : aI) {
               try {
                   in1.read(buffer); //读取消息
               } catch (IOException e) {
                   e.printStackTrace();
               }
               if (new String(buffer).equals(msg))//如果读到的消息和我预测的是一样的,就打印
               System.out.println(c);
               try {
                   out1.write(msg.getBytes());//写入消息
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }


       },"t1");
       t2=new Thread(()->{
           byte[] buffer= new byte[9];
           for (char c : aC) {
               System.out.println(c);
               try {
                   out2.write(msg.getBytes());//写入消息
                   in2.read(buffer);//读取消息
               } catch (IOException e) {
                   e.printStackTrace();
               }
               if (new String(buffer).equals(msg))continue;
           }
       },"t2");
       t1.start();
       t2.start();
    }
}
