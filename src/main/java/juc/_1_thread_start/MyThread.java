package juc._1_thread_start;

import juc.util.JucUtil;

public class MyThread extends Thread {
    public static void main(String[] args) {
        new MyThread().start();
        JucUtil.echoPid();
    }
    
    public MyThread() {
        super("MyThread");
//        setDaemon(true);      // 取消注释，则main函数直接结束，不等待run()结束
    }

    public void run() {
        System.out.println(getName() + " is Running.");
        JucUtil.sleepUtilDeath();
    }
}