package juc._1_thread_start;

import juc.util.JucUtil;

public class MyRunnable implements Runnable {
    public static void main(String[] args) {
        new Thread(new MyRunnable(), "MyRunnable").start();
    }
    
    private int idx;
    
    public MyRunnable() {}
    
    public MyRunnable(int idx) {
        this.idx = idx;
    }
    
    public void run() {
        JucUtil.sleep100ms();
        JucUtil.print("is Running." + (idx == 0 ? "" : "Index: " + idx) );        
    }
}
