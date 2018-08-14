package com.zsk.concurrency.example.aqs.countdownlatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * Create by zsk on 2018/8/14
 **/
@Slf4j
public class HangzhouDangerCenter extends DangerCenter {
    public HangzhouDangerCenter(CountDownLatch countDownLatch) {
        super(countDownLatch,"杭州站");
    }

    @Override
    public void check() {
        log.info("开始检查 {}",this.getStation());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("检查 【" +this.getStation()+"】 完毕，开始发车");

    }
}
