package com.example.sentinel;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoneExample {

    //Semaphore 小列子  nihao wo shi yj
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore sm = new Semaphore(5);
        for (int i = 0; i < 20; i++) {
            int no = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        sm.acquire();//拿到令牌
                        System.out.println(Thread.currentThread().getName() + ":" + (no+1) + "在执行业务逻辑");
                        Thread.sleep(new Random().nextInt(10000));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }finally {
                        System.out.println(Thread.currentThread().getName() + ":" + (no+1) + "释放令牌");
                        sm.release();//释放令牌
                    }

                }
            };
            executorService.execute(runnable);
        }

        executorService.shutdown();
    }


}
