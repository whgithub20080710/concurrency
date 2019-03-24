package com.wooden.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * synchronized(class)包含方法的全部代码时效果等同于直接使用synchronized修饰静态方法
 * synchronized修饰静态方法时，多线程调用此方法时会同步执行
 */
@Slf4j
public class SynchronizedExample2 {

    // 修饰一个类
    public void test1(int j){
        synchronized (SynchronizedExample2.class){
            for(int i = 0;i < 10;i++){
                log.info("test1-{}，j-{}",i,j);
            }
        }
    }

    // 修饰一个静态方法
    public static synchronized void test2(int j){
        for(int i = 0;i < 10;i++){
            log.info("test2-{}，j-{}",i,j);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 example1 = new SynchronizedExample2();
        SynchronizedExample2 example2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example1.test1(1);
        });
        executorService.execute(() -> {
            example2.test1(2);
        });
    }
}
