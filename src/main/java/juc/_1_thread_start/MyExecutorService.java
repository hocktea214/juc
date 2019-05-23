package juc._1_thread_start;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyExecutorService {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(5, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024),
            new MyThreadFactory("My task", false),
            new ThreadPoolExecutor.AbortPolicy());

        for (int i = 1; i <= 100; i++) {
            executorService.execute(new MyRunnable(i));
        }
        
        executorService.shutdown();
    }
}
