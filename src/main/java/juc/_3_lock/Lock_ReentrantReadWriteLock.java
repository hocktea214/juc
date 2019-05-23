package juc._3_lock;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import juc.util.JucUtil;

public class Lock_ReentrantReadWriteLock {
    private final Map<Integer, String> m = new TreeMap<Integer, String>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock r = lock.readLock(); // 读锁
    private final Lock w = lock.writeLock(); // 写锁

    public static void main(String[] args) {
        Lock_ReentrantReadWriteLock rwl = new Lock_ReentrantReadWriteLock();
        
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    JucUtil.sleep1ms();
                    rwl.put((j % 10), "v" + j);
                }
            }, "W-" + i).start();
        }
        
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                while (true) {
                    int key = new Random().nextInt(10);
                    JucUtil.sleep1s();
                    JucUtil.print(" key= " + key + " value " + rwl.get(key));
                }
            }, "R-" + i).start();
        }
    }
    
    public String get(Integer key) {
        r.lock();
        try {
            return m.get(key);
        } finally {
            r.unlock();
        }
    }
    public String put(Integer key, String value) {
        w.lock();
        try {
            return m.put(key, value);
        } finally {
            w.unlock();
        }
    }
    public void clear() {
        w.lock();
        try {
            m.clear();
        } finally {
            w.unlock();
        }
    }
}