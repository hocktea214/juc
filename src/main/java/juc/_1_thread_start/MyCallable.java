package juc._1_thread_start;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import juc.util.JucUtil;

public class MyCallable implements Callable<String> {
    public static void main(String[] args) throws Exception {
        FutureTask<String> future = new FutureTask<String>(new MyCallable());
        new Thread(future, "MyCallable").start();

        System.out.println(future.get());
    }
    
    public String call() throws Exception {
        JucUtil.sleep1s();
        
        return Thread.currentThread().getName() + " is Running";
    }
}
