package juc._2_thread_state;

import static juc.util.JucUtil.print;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadState_New_Runnable_Waiting_Runnable_Terminated_Lock {
     // 锁
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    
    public static void main(String[] args) {
        Thread showThread = new Thread(() -> {
            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            print(4, Thread.currentThread());
        });

        print(1, showThread);
        showThread.start();
        print(2, showThread);
        // 循环读取展示线程状态，直到读到展示线程状态为WAITING，才让辅助线程唤醒等待线程。
        while (true) {
            if (showThread.getState() == Thread.State.WAITING) {
                print(3, showThread);
                break;
            }
        }
        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }

        try {
            showThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 线程执行完毕打印状态。
        print(5, showThread);
    }
}
