package com.my.project.limit;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.Lifecycle;

import java.io.*;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 令牌桶限流
 *
 * @author: Mr.WangJie
 * @date: 2018-09-12
 **/
public class TokenBucket implements Lifecycle {

    /**
     * 默认桶大小个数 即最大瞬时流量是64M
     */
    private static final int DEFAULT_BUCKET_SIZE = 1024 * 1024 * 64;

    /**
     * 一个桶的单位是1字节
     */
    private int everyTokenSize = 1;

    /**
     * 瞬时最大流量
     */
    private int maxFlowRate;

    /**
     * 平均流量
     */
    private int avgFlowRate;

    /**
     * 队列来缓存桶数量：最大的流量峰值 = everyTokenSize * DEFAULT_BUCKET_SIZE
     */
    private ArrayBlockingQueue<Byte> tokenQueue = new ArrayBlockingQueue<>(DEFAULT_BUCKET_SIZE);

    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private volatile boolean isStart = false;

    private ReentrantLock lock = new ReentrantLock(true);

    private static final byte A_CHAR = 'a';

    public TokenBucket() {
    }

    public TokenBucket(int maxFlowRate, int avgFlowRate) {
        this.maxFlowRate = maxFlowRate;
        this.avgFlowRate = avgFlowRate;
    }

    public TokenBucket(int everyTokenSize, int maxFlowRate, int avgFlowRate) {
        this.everyTokenSize = everyTokenSize;
        this.maxFlowRate = maxFlowRate;
        this.avgFlowRate = avgFlowRate;
    }

    public void addTokens(Integer tokenNum) {
        // 若是桶已经满了，就不再添加新的令牌，此处用的是BlockingQueue队列自己的性质。
        for (int i = 0; i < tokenNum; i++) {
            tokenQueue.offer(A_CHAR);
        }
    }

    public TokenBucket build() {
        start();
        return this;
    }

    public boolean getTokens(byte[] dataSize) {
        // TODO: 2018/9/12 需要检查dataSize是否为空
        // TODO: 2018/9/12 需要检查isStart是否为true，false则抛出异常
        // 传输内容大小对应的桶个数
        int needTokenNum = dataSize.length / everyTokenSize + 1;

        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            // 是否存在足够的桶数量
            if (needTokenNum > tokenQueue.size()) {
                return false;
            }

            int tokenCount = 0;
            for (int i = 0; i < needTokenNum; i++) {
                Byte poll = tokenQueue.poll();
                if (poll != null) {
                    tokenCount++;
                }
            }
            return tokenCount == needTokenNum;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void start() {
        // 初始化桶队列大小
        if (maxFlowRate != 0) {
            tokenQueue = new ArrayBlockingQueue<>(maxFlowRate);
        }

        // 初始化令牌生产者
        TokenProducer tokenProducer = new TokenProducer(avgFlowRate, this);
        scheduledExecutorService.scheduleAtFixedRate(tokenProducer, 0, 1, TimeUnit.SECONDS);
        isStart = true;
    }

    @Override
    public void stop() {
        isStart = false;
        scheduledExecutorService.shutdown();
    }

    public boolean isStarted() {
        return isStart;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    public static TokenBucket newBuilder() {
        return new TokenBucket();
    }

    public TokenBucket everyTokenSize(int everyTokenSize) {
        this.everyTokenSize = everyTokenSize;
        return this;
    }

    public TokenBucket maxFlowRate(int maxFlowRate) {
        this.maxFlowRate = maxFlowRate;
        return this;
    }

    public TokenBucket avgFlowRate(int avgFlowRate) {
        this.avgFlowRate = avgFlowRate;
        return this;
    }

    private String stringCopy(String data, int copyNum) {

        StringBuilder sBuilder = new StringBuilder(data.length() * copyNum);

        for (int i = 0; i < copyNum; i++) {
            sBuilder.append(data);
        }

        return sBuilder.toString();
    }

    class TokenProducer implements Runnable {

        private int avgFlowRate;
        private TokenBucket tokenBucket;

        public TokenProducer(int avgFlowRate, TokenBucket tokenBucket) {
            this.avgFlowRate = avgFlowRate;
            this.tokenBucket = tokenBucket;
        }

        @Override
        public void run() {
            tokenBucket.addTokens(avgFlowRate);
        }
    }

    private static void arrayTest() {
        ArrayBlockingQueue<Integer> tokenQueue = new ArrayBlockingQueue<>(10);
        tokenQueue.offer(1);
        tokenQueue.offer(1);
        tokenQueue.offer(1);
        System.out.println(tokenQueue.size());
        System.out.println(tokenQueue.remainingCapacity());
    }

    private static void tokenTest() throws IOException, InterruptedException {
        TokenBucket tokenBucket = TokenBucket.newBuilder().avgFlowRate(512).maxFlowRate(1024).build();

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/tmp/ds_test")));
        // 四个字节
        String data = "xxxx";
        for (int i = 1; i <= 1000; i++) {
            Random random = new Random();
            int i1 = random.nextInt(100);
            boolean tokens = tokenBucket.getTokens(tokenBucket.stringCopy(data, i1).getBytes());
            TimeUnit.MILLISECONDS.sleep(100);
            if (tokens) {
                bufferedWriter.write("token pass --- index:" + i1);
                System.out.println("token pass --- index:" + i1);
            } else {
                bufferedWriter.write("token reject --- index" + i1);
                System.out.println("token reject --- index" + i1);
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        bufferedWriter.close();
    }
}
