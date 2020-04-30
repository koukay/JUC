package com.houkai.util;

import java.util.concurrent.TimeUnit;

public class SleepUtil {
    public static void sleepSecond(int i){
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void sleepMillis(int i){
        try {
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
