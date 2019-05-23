package juc._4_synchronizer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

import juc.util.JucUtil;

public class Synchronizer_Phaser_CyclicBarrier {
    public static void main(String[] args) throws Exception {
        Phaser phaser = new Phaser(4) {
            protected boolean onAdvance(int phase, int registeredParties) {
                JucUtil.print("...");
                return super.onAdvance(phase, registeredParties);
            }
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        
        for (int i = 0; i < 4; i++) {
            executorService.execute(() -> {
                goTo("Start", phaser);
                goTo("Center", phaser);
                goTo("End", phaser);
            });
        }
        executorService.shutdown();
    }
    
    private static void goTo(String place, Phaser phaser) {
        JucUtil.print(" arrive " + place);
        phaser.arriveAndAwaitAdvance();
    }
}
