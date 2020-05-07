package com.houkai.juc;

import com.houkai.util.SleepUtil;

/**
 * 先解释在while循环里面将println注释后产生的结果原因：
 * 一开始主线程中的initFlag的变量值为false，
 * 创建了一个线程Thread-0,将flag复制到运行内存中，因为Thread-0在运行的时候flag一直都是false，
 * 因为while循环会一直运行，后面的线程Thread-1虽然改变了主内存里面initFlag为true了，
 * 但是影响不了Thread-0运行内存中的initFlag的值。因此Thread-0会一直在while中无限循环；
 *
 * 现在加了println后，因为println操作是Synchronized加锁的，它会做以下的操作：
 * 1.获得同步锁；
 * 2.清空工作流出来；
 * 3.从主内存拷贝对象副本到线程工作内存中；
 * 4.开始继续执行代码；
 * 5.刷新主内存数据；
 * 6.释放同步锁。
 *
 * 在清空内存刷新内存的过程中Thread-0线程的initFlag就变成了true，所以就跳出了循环。
 *
 */
public class Test {
    public static void main(String[] args) {
        RunDemo runDemo = new RunDemo();
        new Thread(runDemo).start();
    while (true){
        if (runDemo.isFlag()){
            System.out.println("主线程读取flag: "+runDemo.isFlag());
            break;
        }
        /**
         *          synchronized (this) {
         *                 ensureOpen();
         *                 textOut.newLine();
         *                 textOut.flushBuffer();
         *                 charOut.flushBuffer();
         *                 if (autoFlush)
         *                     out.flush();
         *             }
         */
        System.out.println();
    }
    }
}
class RunDemo implements  Runnable{
    private boolean flag= false;
    @Override
    public void run() {
        SleepUtil.sleepSecond(3);
        flag=true;
        System.out.println("flag 设置为true");
    }
    public  boolean isFlag(){
        return flag;
    }
}