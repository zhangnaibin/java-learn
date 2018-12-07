package com.znb.java.learn.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class CompletableFutureTest {
    static List<Shop> shops = Arrays.asList(
            new Shop("苏宁"),
            new Shop("拼多多"),
//                new Shop("京东商城"),
            new Shop("天猫商城2"),
            new Shop("天猫商城4"),
            new Shop("天猫商城5")
    );

    public static void doSomethingElse(){
        System.out.println("做其他的事情。。。");
    }

    static class Shop {
        private String name;
        private Random random = new Random();

        public Shop(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        //直接获取价格
        public double getPrice(String product){
            return calculatePrice(product);
        }
        //模拟延迟
        public  void delay(){
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
        }
        //模拟获取价格的服务
        private double calculatePrice(String product){
            delay();
            return random.nextDouble() * product.charAt(0) + product.charAt(1);
        }

        //异步获取价格
        public Future<Double> getPriceAsync(String product){
            CompletableFuture<Double> future = new CompletableFuture<>();
            new Thread(() -> {
                double price = calculatePrice(product);
                future.complete(price);
            }).start();
            return  future;
        }
    }

    public static void testAsync() {
        Shop shop = new Shop("BestShop");
        long start = System.currentTimeMillis();
        Future<Double> future = shop.getPriceAsync("My Favorite");
        long invocationTime = System.currentTimeMillis() - start;
        System.out.println("调用接口时间：" + invocationTime + "毫秒");

        doSomethingElse();

        try {
            double price = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        long retrievalTime = System.currentTimeMillis() - start;
        System.out.println("返回价格消耗时间：" + retrievalTime + "毫秒");

    }

    /**
     * 多余4个时，无时间优势
     * @param product
     * @return
     */
    public static List<String> findPriceByParallelStream(String product){
        return shops.parallelStream()
                .map(shop -> String.format("%s 的价格是 %.2f", shop.getName(), shop.getPrice(product)))
                .collect(toList());

    }

    public static List<String> findPriceAsyncBatch(String product){
        List<CompletableFuture<String>> priceFuture = shops.stream()
                .map(shop ->
                        CompletableFuture.supplyAsync(() -> shop.getName() + " 的价格是 " + shop.getPrice(product))
                )
                .collect(toList());
        return priceFuture.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }


    public static void testParallelAsync2() {
        long start = System.currentTimeMillis();
        System.out.println(findPriceByParallelStream("java8实战"));
        long duration = System.currentTimeMillis() - start;
        System.out.println("总消耗时间：" + duration + "毫秒");
    }

    public static void testAsyncBatch() {
        long start = System.currentTimeMillis();
        System.out.println(findPriceAsyncBatch("java8实战"));
        long duration = System.currentTimeMillis() - start;
        System.out.println("总消耗时间：" + duration + "毫秒");
    }

    public static void main(String[] args) throws Exception {
//        testRunnable();
//        testCallable();
//        testAsync();
//        testParallelAsync2();
        testAsyncBatch();
    }

    public static void testRunnable() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> System.out.println("testRunnable"));
        future.get();
    }

    public static void testCallable() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "testCallable");
        System.out.println(future.getNumberOfDependents());
        System.out.println(future.get());
    }
}
