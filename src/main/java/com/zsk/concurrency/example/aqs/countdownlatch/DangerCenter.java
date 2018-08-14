package com.zsk.concurrency.example.aqs.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Create by zsk on 2018/8/14
 **/
public abstract class DangerCenter  implements Runnable{
    private CountDownLatch countDownLatch ;
    private String station;
    private Boolean ok = false;

    public DangerCenter(CountDownLatch countDownLatch, String station) {
        this.countDownLatch = countDownLatch;
        this.station = station;
    }

    @Override
    public void run() {
        try {
            check();
            ok = true;
        }catch (Exception e){
            ok=false;
        }
        finally {
            if (countDownLatch!=null){
                countDownLatch.countDown();
            }
        }
    }
    public abstract void check();

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }
}
