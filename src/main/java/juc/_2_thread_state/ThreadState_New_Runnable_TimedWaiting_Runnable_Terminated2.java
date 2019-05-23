package juc._2_thread_state;

import static juc.util.JucUtil.print;

import java.lang.Thread.State;

import juc.util.JucUtil;

/**
 * Thread.sleep(long)触发线程进入TIMED_WAITING
 */
public class ThreadState_New_Runnable_TimedWaiting_Runnable_Terminated2 {
    public static void main(String[] args) {
        Thread showThread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                JucUtil.sleep1s();
                print(4, Thread.currentThread());
            }
        });

        print(1, showThread);
        showThread.start();
        print(2, showThread);
        // 循环读取展示线程状态，直到读到展示线程状态为WAITING，才让辅助线程唤醒等待线程。
        
        int timedWaiting = 0, running = 0;
        while (true) {
            JucUtil.sleep10ms();
            switch (showThread.getState()) {
                case TIMED_WAITING :
                    timedWaiting++;
                    break;
                case RUNNABLE:
                    running++;
                    break;
                default :
                    JucUtil.print(Thread.currentThread(), State.TIMED_WAITING, timedWaiting);
                    JucUtil.print(Thread.currentThread(), State.RUNNABLE, running);
                    
                    // 线程执行完毕打印状态。
                    print(5, showThread);
                    return;
            }
        }
    }
}
