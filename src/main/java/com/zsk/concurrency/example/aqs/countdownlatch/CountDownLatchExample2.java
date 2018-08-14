package com.zsk.concurrency.example.aqs.countdownlatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Create by zsk on 2018/8/14
 **/
@Slf4j
public class CountDownLatchExample2 {
    private final static int threadNum=200;

    public static void main(String[] args) throws InterruptedException{

        ExecutorService executorService = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        for (int i=0;i<threadNum;i++){
            executorService.execute(()->{
                try {
                    test(Thread.currentThread().getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("exception", e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await(1000, TimeUnit.MILLISECONDS);
        log.info("finish....");
        executorService.shutdown();
    }
    public static void test(long threadNum) throws Exception{
        Thread.sleep(1000);
        log.info("current thread num is {}",threadNum);
        Thread.sleep(1000);
    }
}
