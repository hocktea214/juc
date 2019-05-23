package juc._3_lock;

import juc.util.JucUtil;

public class Lock_Synchronized {
    private final Object lock = new Object();
    private int index = 0;
    private int[] data = new int[3];

    public void put(int value) {
        synchronized(lock) {
            while (index == data.length) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                }
            }
            data[index] = value;
            index++;
            lock.notify();
        }
    }
    
    public int get() {
        synchronized(lock) {
            while (index <= 0) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                }
            }
            index--;
            int val = data[index];
            lock.notify();
            return val;
        }
    }
    
    public static void main(String[] args) {
        Lock_Synchronized lock = new Lock_Synchronized();
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