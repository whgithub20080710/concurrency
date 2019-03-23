package com.wooden.concurrency.example.atomic;

import com.wooden.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            // 此方法的fieldName参数要求所指变量必须用volatile修饰且非static
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

    @Getter
    public volatile int count = 100;

    private static AtomicExample5 example5 = new AtomicExample5();

    public static void main(String[] args) {
        if(updater.compareAndSet(example5,100,120)){
            log.info("update1 success:{}",example5.getCount());
        }
        if(updater.compareAndSet(example5,100,120)){
            log.info("update2 success:{}",example5.getCount());
        }else {
            log.info("update2 failed:{}",example5.getCount());
        }
    }

}
