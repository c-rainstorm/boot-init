package me.rainstorm.boot.service.lock;

import lombok.Getter;

import java.util.Objects;
import java.util.concurrent.locks.Lock;

@Getter
public class LockResult implements AutoCloseable {
    public static final int FAILURE = 1;
    public static final int SUCCESS = 1 << 1;
    public static final int EXCEPTION = 1 << 2;

    private int result;
    private String key;
    private Lock lock;

    public LockResult(int result, String key, Lock lock) {
        this.result = result;
        this.key = key;
        this.lock = lock;
    }

    public boolean success() {
        return SUCCESS == result;
    }

    public boolean failure() {
        return FAILURE == result;
    }

    public boolean exception() {
        return EXCEPTION == result;
    }

    @Override
    public void close() throws Exception {
        if (success() && Objects.nonNull(lock)) {
            lock.unlock();
        }
    }
}
