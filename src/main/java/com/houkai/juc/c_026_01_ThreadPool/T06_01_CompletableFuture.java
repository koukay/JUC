package com.houkai.juc.c_026_01_ThreadPool;

import com.houkai.util.SleepUtil;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture异步处理业务
 */
public class T06_01_CompletableFuture {

    public static void main(String[] args) {
        long start,end;
        /*start=System.currentTimeMillis();
        priceOfTM();
        priceOfTB();
        priceOfJD();

        end=System.currentTimeMillis();
        System.out.println("use serial method call! " + (end - start));*/


        start=System.currentTimeMillis();
        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(() -> priceOfTM());
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(() -> priceOfTB());
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(() -> priceOfJD());

        CompletableFuture.allOf(futureJD,futureTB,futureTM).join();
        CompletableFuture.supplyAsync(()->priceOfTM()).
                thenApply(String::valueOf).
                thenApply(str->"price"+str).
                thenAccept(System.out::println);
        end=System.currentTimeMillis();
        System.out.println("use completable future! " + (end - start));
    }
    private static  double priceOfTM(){
        delay();
        return 1.00;
    }
    private static  double priceOfTB(){
        delay();
        return 2.00;
    }
    private static  double priceOfJD(){
        delay();
        return 3.00;
    }

    private static double priceOfAmazon() {
        delay();
        throw new RuntimeException("product not exist!");
    }

    private static void  delay(){
        int time = new Random().nextInt(500);
        SleepUtil.sleepMillis(time);
        System.out.printf("After %s sleep!\n", time);
    }

}
