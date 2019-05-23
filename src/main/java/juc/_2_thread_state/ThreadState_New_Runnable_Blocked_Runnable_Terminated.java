package juc._2_thread_state;

import static juc.util.JucUtil.print;
import static juc.util.JucUtil.sleep100ms;
import static juc.util.JucUtil.sleep10ms;

public class ThreadState_New_Runnable_Blocked_Runnable_Terminated {
    // 锁
    private static final Object lock = new Object();
    
    public static void main(String[] args) {
        // 辅助线程，制造synchronized状态。
        Thread assistantThread = new Thread(() -> {
            synchronized (lock) {
                sleep100ms(); // 锁定一定时间
            }
        });
        assistantThread.start();

        sleep10ms(); // 保证assistantThread先执行

        Thread showThread = new Thread(() -> {
            synchronized (lock) {
                print(4, Thread.currentThread());
            }
        });
        print(1, showThread);
        showThread.start();
        print(2, showThread);
        // 因为无法判断显示线程何时执行，所以循环直到显示线程执行。
        while (true) {
            if (showThread.getState() == Thread.State.BLOCKED) {
                print(3, showThread);
                break;
            }
        }
        // 等待两个线程执行完毕。
        try {
            assistantThread.join();
            showThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 线程执行完毕打印状态。
        print(5, showThread);
    }
}