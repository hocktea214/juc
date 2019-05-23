package juc._2_thread_state;

import static juc.util.JucUtil.print;

public class ThreadState_New_Runnable_Terminated {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            print(2, Thread.currentThread());
        });
        print(1, thread);
        thread.start();
        // 等待线程执行完毕
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print(3, thread);
    }
}
