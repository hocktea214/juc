package juc._4_synchronizer;

import java.util.concurrent.Phaser;

import juc.util.JucUtil;

public class Synchronizer_Phaser_CountDownLatch {
    public static void main(String[] args) throws Exception {
        Phaser phaser = new Phaser(3);
        
        new Thread(() -> {
            JucUtil.sleep100ms();
            JucUtil.print();
            phaser.arrive();    // 类似于latch.countDown();
        }, "T1").start();
        
        new Thread(() -> {
            JucUtil.sleep1s();
            JucUtil.print();
            phaser.arrive();
        }, "T2").start();
        
        new Thread(() -> {
            JucUtil.sleep2s();
            JucUtil.print();
            phaser.arrive();
        }, "T3").start();
        
        JucUtil.print();
        phaser.awaitAdvance(phaser.getPhase()); // 类似于latch.await();
        
        JucUtil.print();
    }
}
