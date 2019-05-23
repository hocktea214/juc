package juc._1_thread_start;

import java.io.IOException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadFactory implements ThreadFactory {
    private final String name;
    private final boolean daemon;
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    public MyThreadFactory(String name, boolean daemon) {
        this.name = name;
        this.daemon = daemon;
    }

    public Thread newThread(Runnable runnable) {
        int num = threadNumber.getAndIncrement();
        String threadName = name;
        if (num != 1) {
            threadName += "-" + num;
        }
        Thread thread = new Thread(runnable, threadName);
        if (daemon) {
            thread.setDaemon(true);
        }
        return thread;
    }
    
    public static void main(String[] args) throws IOException {
        new MyThreadFactory("MyThreadFactory", false)
            .newThread(new MyRunnable())
            .start();
    }
}