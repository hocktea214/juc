package juc._2_thread_state;

import static juc.util.JucUtil.print;

/**
 * Object.wait(long)触发线程进入TIMED_WAITING
 */
public class ThreadState_New_Runnable_TimedWaiting_Runnable_Terminated {
    // 锁
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread showThread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            print(4, Thread.currentThread());
        });

        print(1, showThread);
        showThread.start();
        print(2, showThread);
        // 循环读取展示线程状态，直到读到展示线程状态为WAITING，才让辅助线程唤醒等待线程。
        while (true) {
            if (showThread.getState() == Thread.State.TIMED_WAITING) {
                print(3, showThread);
                break;
            }
        }
        
//        synchronized (lock) {
//            lock.notifyAll();
//        }

        try {
            showThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 线程执行完毕打印状态。
        print(5, showThread);
    }
}
