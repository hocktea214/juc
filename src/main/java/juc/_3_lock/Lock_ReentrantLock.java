package juc._3_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import juc.util.JucUtil;

public class Lock_ReentrantLock {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    
    private int index = 0;
    private int[] data = new int[3];

    public void put(int value) {
        lock.lock();
        try {
            while (index == data.length) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                }
            }
            data[index] = value;
            index++;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
    
    public int get() {
        lock.lock();
        try {
            while (index <= 0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                }
            }
            index--;
            int val = data[index];
            condition.signal();
            return val;
        } finally {
            lock.unlock();
        }
    }
    
    public static void main(String[] args) {
        Lock_ReentrantLock lock = new Lock_ReentrantLock();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                int value = 0;
                while (true) {
                    lock.put(value);
                    JucUtil.print(Thread.currentThread(), " produced ", value++);
                    JucUtil.sleep1s();
                }
            }, "Producer-" + i).start();
        }
        
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true) {
                    JucUtil.print(Thread.currentThread(), " consumed ", lock.get());
                    JucUtil.sleep100ms();
                }
            }, "Consumer-" + i).start();
        }
    }
}