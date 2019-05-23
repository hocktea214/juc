package juc._2_thread_state;

import java.util.concurrent.TimeUnit;

public class WaitNotifyCase {
    public static void main(String[] args) {
        final Object lock = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread A is waiting to get lock");
                synchronized (lock) {
                    try {
                        System.out.println("thread A get lock");
                        System.out.println("thread A do wait method");
                        lock.wait();
                        System.out.println("wait end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new MyRunnable(lock), "B").start();
        new Thread(new MyRunnable(lock), "C").start();
    }
}

class MyRunnable implements Runnable {
    private final Object lock;
    
    public MyRunnable(Object lock) {
        this.lock = lock;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " is waiting to get lock");
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " get lock");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.notify();
            System.out.println(Thread.currentThread().getName() + " do notify method");
        }
    }
}
