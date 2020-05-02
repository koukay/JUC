package com.houkai.juc.c_026_01_ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程池的概念
 * nasa
 * 计算一个范围内质数数量及用时
 */
public class T09_FixedThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<Integer> prime = getPrime(1, 200000);
        long end = System.currentTimeMillis();
        System.out.println(end - start+" prime size "+prime.size());

        final int cpuCoreNum = 4;
        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);
        MyTask t1 = new MyTask(1, 80000); //1-5 5-10 10-15 15-20
        MyTask t2 = new MyTask(80001, 130000);
        MyTask t3 = new MyTask(130001, 170000);
        MyTask t4 = new MyTask(170001, 200000);
        Future<List<Integer>> f1 = service.submit(t1);
        Future<List<Integer>> f2 = service.submit(t2);
        Future<List<Integer>> f3 = service.submit(t3);
        Future<List<Integer>> f4 = service.submit(t4);

        start = System.currentTimeMillis();
        List<Integer> list = f1.get();
        List<Integer> list1 = f2.get();
        List<Integer> list2 = f3.get();
        List<Integer> list3 = f4.get();
        List<Integer> listAll = new ArrayList<>();
        listAll.addAll(list);
        listAll.addAll(list1);
        listAll.addAll(list2);
        listAll.addAll(list3);
        end = System.currentTimeMillis();
        System.out.println(end - start+" list size "+listAll.size());
    }
    static class MyTask implements Callable<List<Integer>>{
        int startPos, endPos;

        public MyTask(int startPos, int endPos) {
            this.startPos = startPos;
            this.endPos = endPos;
        }

        @Override
        public List<Integer> call() throws Exception {
            List<Integer> prime = getPrime(startPos, endPos);
            return prime;
        }
    }

    /**
     * 判断该数是不是质数
     * @param num
     * @return
     */
    static boolean isPrime(int num){
        for (int i = 2; i <= num/2 ; i++) {
            if (num % i==0)return false;
        }
        return true;
    }

    /**
     * 拿到一段区间的质数
     * @param start
     * @param end
     * @return
     */
    static List<Integer> getPrime(int start,int end){
        List<Integer> list= new ArrayList<>();
        for (int i = start; i <=end ; i++) {
            if (isPrime(i)) list.add(i);
        }
        return list;
    }
}
