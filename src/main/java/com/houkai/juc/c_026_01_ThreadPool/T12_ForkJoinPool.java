package com.houkai.juc.c_026_01_ThreadPool;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class T12_ForkJoinPool {
    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();

    static {
        for(int i=0; i<nums.length; i++) {
            nums[i] = r.nextInt(100);
        }

        System.out.println("---" + Arrays.stream(nums).sum()); //stream api
    }

    static class AddTask extends RecursiveAction {
        int start, end;

        public AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end-start<=MAX_NUM){
                long sum=0L;
                for (int i = start ; i <end ; i++) {
                    sum+=nums[i];
                }
                System.out.println("from "+start+" to: "+end+" = "+sum);
            }else {
                int middle = start+(end-start)/2;

                AddTask addTask1= new AddTask(start, middle);
                AddTask addTask2= new AddTask(middle, end);
                addTask1.fork();
                addTask2.fork();
            }
        }
    }

    static class AddTaskRet extends RecursiveTask<Long> {
        private static final long serialVersionUID = 1L;
        int start, end;

        public AddTaskRet(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end-start<=MAX_NUM){
                long sum=0L;
                for (int i = start ; i <end ; i++) {
                    sum+=nums[i];
                }
                return sum;
            }
                int middle = start+(end-start)/2;

                AddTaskRet addTask1= new AddTaskRet(start, middle);
                AddTaskRet addTask2= new AddTaskRet(middle, end);
                addTask1.fork();
                addTask2.fork();
            return addTask1.join()+addTask2.join();
        }
    }

    public static void main(String[] args) {
        T12_ForkJoinPool temp = new T12_ForkJoinPool();
        ForkJoinPool fjp = new ForkJoinPool();
        AddTaskRet task= new AddTaskRet(0, nums.length);
        fjp.execute(task);
        Long result = task.join();
        System.out.println(result);

    }
}
