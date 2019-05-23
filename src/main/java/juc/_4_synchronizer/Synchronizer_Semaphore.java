package juc._4_synchronizer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import juc.util.JucUtil;

public class Synchronizer_Semaphore {
    public static void main(String[] args) throws Exception {
        Semaphore semaphore = new Semaphore(2);
        
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    JucUtil.sleep1s();
                    JucUtil.print("is running, avaiableNum", semaphore.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                    JucUtil.print("is finished, avaiableNum", semaphore.availablePermits());
                }
            });
        }
        executorService.shutdown();
    }
}
