package com.zsk.concurrency.example.aqs.countdownlatch;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create by zsk on 2018/8/14
 **/
public class CheckStartUp {
    private static CountDownLatch countDownLatch;
    private static  ArrayList<DangerCenter> statiionList;
    public CheckStartUp() {
    }

    public static boolean checkAllStations() throws Exception{
        countDownLatch = new CountDownLatch(3);
        statiionList = new ArrayList<DangerCenter>();
        statiionList.add(new BeijingDangerCenter(countDownLatch));
        statiionList.add(new ShanghaiDangerCenter(countDownLatch));
        statiionList.add(new HangzhouDangerCenter(countDownLatch));

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (DangerCenter dangerCenter : statiionList){
            executorService.execute(dangerCenter);
        }
        countDownLatch.await();

        for (DangerCenter center : statiionList) {
            if (!center.getOk()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception{
        boolean b = checkAllStations();
        if (b){
            System.out.println("所有站点准备完毕，开始发车");
        }
    }
}
