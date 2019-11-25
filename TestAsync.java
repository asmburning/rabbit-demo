package com.hellobike.rent.order.sync.web.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author liuxinyi
 * @date 2019/11/15
 */
public class TestAsync {

    private static ThreadPoolExecutor myPool = new ThreadPoolExecutor(
            50, 70, 15, TimeUnit.MINUTES,
            new ArrayBlockingQueue<Runnable>(2000),
            new ThreadFactoryBuilder().setDaemon(true).setNameFormat("async-pool-%d").build());


    public static void main(String[] args) throws Exception {
        useCustomPool();
    }

    private static void useCustomPool() throws InterruptedException, ExecutionException, TimeoutException {
        int max = 120;
        List<CompletableFuture<Integer>> list = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            CompletableFuture<Integer> completableFuture = CompletableFuture
                    .supplyAsync(TestAsync::getInteger, myPool)
                    .handle((s, t) -> null == t ? s : -1);
            list.add(completableFuture);
        }
        CompletableFuture<Integer>[] array = new CompletableFuture[max];
        list.toArray(array);
        CompletableFuture<Void> allOf = CompletableFuture.allOf(array);
        allOf.get(5, TimeUnit.SECONDS);
        for (CompletableFuture<Integer> completableFuture : list) {
            try {
                System.out.println(completableFuture.get());
            } catch (Exception e) {
                System.out.println(completableFuture.isDone());
            }
        }
        System.out.println(myPool.getActiveCount());
        System.out.println(myPool.getPoolSize());
        System.out.println(myPool.getQueue().size());
        System.out.println(myPool.getQueue().remainingCapacity());
        System.out.println(myPool.getCompletedTaskCount());
    }

    private static Integer getInteger() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        System.out.println(Thread.currentThread().getName());
        int i = random.nextInt(800);
        i = i + 100;
        try {
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (Exception e) {
            return 0;
        }
        if (i > 400) {
            throw new RuntimeException("greater than 400");
        }
        return i;
    }
}
