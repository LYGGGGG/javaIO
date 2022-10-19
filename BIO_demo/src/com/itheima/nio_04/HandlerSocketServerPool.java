package com.itheima.nio_04;

/*
@author YG
@create 2022/10/18   20:54
*/

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HandlerSocketServerPool {
    private ExecutorService executorService;

    public HandlerSocketServerPool(int coreSize, int maxSize, int queueSize) {
        this.executorService = new ThreadPoolExecutor(coreSize,maxSize,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public void excute(Runnable task){
        //主线程提交任务后，任务与主线程无关，交给了线程池处理
        //执行task的run方法，即触发的是：实现Runnable接口，重写的run方法
       this.executorService.execute(task);
    }
}
