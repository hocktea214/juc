package juc._4_synchronizer;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import juc.util.JucUtil;

public class Synchronizer_CyclicBarrier {
    public static void main(String[] args) throws Exception {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> {
            JucUtil.print("...");
        });

        ExecutorService executorService = Executors.newCachedThreadPool();
        
        for (int i = 0; i < 4; i++) {
            executorService.execute(() -> {
                goTo("Start", cyclicBarrier);
                goTo("Center", cyclicBarrier);
                goTo("End", cyclicBarrier);
            });
        }
        executorService.shutdown();
    }
    
    private static void goTo(String place, CyclicBarrier cyclicBarrier) {
        JucUtil.print(" arrive " + place);
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
