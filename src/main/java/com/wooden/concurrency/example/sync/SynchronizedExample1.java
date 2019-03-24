package com.wooden.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * synchronized包含方法的全部代码时效果等同于直接使用synchronized修饰方法
 * synchronized修饰方法时，同一个对象在多线程调用此方法时会同步执行
 * 子类不会继承父类方法的synchronized特性，因为synchronized不属于方法声明
 */
@Slf4j
public class SynchronizedExample1 {

    // 修饰一个代码块
    public void test1(int j){
        synchronized (this){
            for(int i = 0;i < 10;i++){
                log.info("test1-{}，j-{}",i,j);
            }
        }
    }

    // 修饰一个方法
    public synchronized void test2(int j){
        for(int i = 0;i < 10;i++){
            log.info("test2-{}，j-{}",i,j);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example1.test2(1);
        });
        executorService.execute(() -> {
            example2.test2(2);
        });
    }
}
