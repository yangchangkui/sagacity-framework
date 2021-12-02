package com.sagacity.framework.util;

public final class IdWorker {
    //起始时间戳，用于用当前时间戳减去这个时间戳，算出偏移量
    private final long twepoch = 1542335205801L;
    //workerId占用的位数：5
    private final long workerIdBits = 5L;
    private final long workerId;
    // workerId可以使用的最大数值：31
    private final long maxWorkerId = ~(-1L << workerIdBits);
    private final int prefixNumberMin;
    private final int prefixNumberMax;
    //上一个时间戳
    private long lastTimestamp;
    private long sequence;
    private long roundTimestamp;
    private long roundSequence;

    public IdWorker(long workerId) {
        this.prefixNumberMin = 11;
        this.prefixNumberMax = 91;
        this.lastTimestamp = -1L;
        this.sequence = 0L;
        this.roundTimestamp = -1L;
        this.roundSequence = 0L;
        if (workerId <= this.maxWorkerId && workerId >= 0L) {
            this.workerId = workerId;
        } else {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
        }
    }

    public synchronized long nextId(int prefixNumber) {
        if (prefixNumber <= prefixNumberMax && prefixNumber >= prefixNumberMin) {
            long timestamp = this.currentTimeMillis();
            if (timestamp < this.lastTimestamp) {
                throw new IllegalStateException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
            } else {
                ++this.sequence;
                this.sequence %= 1000L;
                if (timestamp == this.roundTimestamp && this.sequence == this.roundSequence) {
                    timestamp = this.tilNextMillis(timestamp);
                }

                if (timestamp != this.roundTimestamp) {
                    this.roundTimestamp = timestamp;
                    this.roundSequence = this.sequence;
                }

                this.lastTimestamp = timestamp;
                long var10000 = timestamp - twepoch;
                long id = var10000 << 5 | this.workerId;
                if (id > 9999999999999L) {
                    throw new IllegalStateException("超出ID生成范围");
                } else {
                    long addNumber = (long)prefixNumber * 10000000000000000L;
                    return addNumber + id * 1000L + this.sequence;
                }
            }
        } else {
            throw new IllegalStateException(String.format("prefixNumber must be greater than %d or less than %d", 11, 91));
        }
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp;
        timestamp = this.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = this.currentTimeMillis();
        }
        return timestamp;
    }

    /**
     * 获取当前时间毫秒
     */
    private long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public long getWorkerId() {
        return this.workerId;
    }
}
