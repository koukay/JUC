package com.houkai.juc.c_020;

import com.houkai.util.SleepUtil;

import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * Phaser阶段
 * 分阶段的栅栏
 * int phase  目前进行到第几阶段
 * int registerdParties 目前多少人参加
 * phaser.bulkRegister(7); party的容量
 * phaser.register();   向party里面加人
 * phaser.arriveAndAwaitAdvance(); 到达等待下一步,表示所有人业务执行完毕进行下一步,如果没执行完毕,继续等待
 * phaser.arriveAndDeregister();将party里面的人移除
 */
public class T09_TestPhaser2 {
    static Random r= new Random();
    static MarriagePhaser phaser= new MarriagePhaser();

    public static void main(String[] args) {
        phaser.bulkRegister(7);
        for (int i = 0; i < 5; i++) {
            new Thread(new Person("p"+i)).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();

    }

    /**
     * 婚礼进行步骤类继承阶段类
     */
    static class MarriagePhaser extends Phaser{
        @Override
        protected boolean onAdvance(int phase,int registerdParties){
            switch (phase){
                case 0:
                    System.out.println("所有人都到齐了! "+registerdParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人都吃完了! "+registerdParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人都离开了! "+registerdParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("婚礼结束,新郎新娘抱抱! "+registerdParties);
                    System.out.println();
                    return true;
                default:
                    return true;
            }
        }
    }

    /**
     * 人员类,控制整个流程及进度
     */
    static class Person implements Runnable{
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive(){
            SleepUtil.sleepMillis(r.nextInt(1000));
            System.out.printf("%s 到达现场! \n",name);
            //phaser等所有人执行完毕才能进入下一阶段
            phaser.arriveAndAwaitAdvance();
        }
        public void eat(){
            SleepUtil.sleepMillis(r.nextInt(1000));
            System.out.printf("%s 吃完! \n",name);
            phaser.arriveAndAwaitAdvance();
        }
        public void leave(){
            SleepUtil.sleepMillis(r.nextInt(1000));
            System.out.printf("%s 离开! \n",name);
            phaser.arriveAndAwaitAdvance();
        }
        public void hug(){
            if (name.equals("新郎") || name.equals("新娘")){
                SleepUtil.sleepMillis(r.nextInt(1000));
                System.out.printf("%s 洞房! \n",name);
                phaser.arriveAndAwaitAdvance();
            }else {
                phaser.arriveAndDeregister();
//                phaser.register();
            }
        }
        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }
}
