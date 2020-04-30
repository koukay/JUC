package com.houkai;


import java.util.Date;

public class TestInterrupt {
    public static void main(String[] args) {
        MyThread myThread= new MyThread();
        myThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread.interrupt();
    }
}
class MyThread extends Thread{
    public void run(){
        while (true){
            System.out.println("=== "+new Date()+" ===");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}