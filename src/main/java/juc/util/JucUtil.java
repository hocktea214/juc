package juc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JucUtil {
    public static void sleep1ms() {
        try {
            Thread.sleep(1L);
        } catch (InterruptedException e) {
        }
    }
    
    public static void sleep10ms() {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
        }
    }
    
    public static void sleep100ms() {
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
        }
    }
    
    public static void sleep1s() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
        }
    }
    
    public static void sleep2s() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
        }
    }
    
    private static final String stringFormat = "%d %s:%s";
    private static final String stringFormat2 = "%s:%s_%d";
    private static final String stringFormat3 = "%s %s %s:%d";
    private static final String stringFormat4 = "%s %s %s";

    private static final ThreadLocal<SimpleDateFormat> formatLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy:mm:dd HH:mm:ss.SSS"));

    public static void print() {
        print("is Running");
    }
    
    public static void print(String message) {
        System.out.println(String.format(stringFormat4, formatLocal.get().format(new Date()), Thread.currentThread().getName(), message));
    }
    
    public static void print(int step, Thread thread) {
        System.out.println(String.format(stringFormat, step, thread.getName(), thread.getState()));
    }
    
    public static void print(Thread thread, Thread.State state, int times) {
        System.out.println(String.format(stringFormat2, thread.getName(), state, times));
    }
    
    public static void print(Thread thread, String message, int value) {
        System.out.println(String.format(stringFormat2, thread.getName(), message, value));
    }
    
    public static void print(String message, int value) {
        System.out.println(String.format(stringFormat3, formatLocal.get().format(new Date()), Thread.currentThread().getName(), message, value));
    }
}
