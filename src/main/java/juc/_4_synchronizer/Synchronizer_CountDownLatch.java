package juc._4_synchronizer;

import java.util.concurrent.CountDownLatch;

import juc.util.JucUtil;

public class Synchronizer_CountDownLatch {
    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(3);
        
        new Thread(() -> {
            JucUtil.sleep100ms();
            JucUtil.print();
            latch.countDown();
        }, "T1").start();
        
        new Thread(() -> {
            JucUtil.sleep1s();
            JucUtil.print();
            latch.countDown();
        }, "T2").start();
        
        new Thread(() -> {
            JucUtil.sleep2s();
            JucUtil.print();
            latch.countDown();
        }, "T3").start();
        
        JucUtil.print();
        latch.await();
        
        JucUtil.print();
    }
}
