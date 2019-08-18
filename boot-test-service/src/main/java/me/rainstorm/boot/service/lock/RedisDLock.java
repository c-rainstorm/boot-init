package me.rainstorm.boot.service.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author baochen1.zhang
 * @date 2019.08.03
 */
@Service
public class RedisDLock implements DLock {
    private static final int LOCK_WAIT_TIME = 3000;

    @Resource
    private RedissonClient client;

    @PreDestroy
    public void destroy() {
        client.shutdown();
    }

    @Override
    public LockResult tryLock(String key, long timeout) {

        LockResult lockResult;
        try {
            RLock lock = client.getLock(key);
            if (lock.tryLock(LOCK_WAIT_TIME, timeout, TimeUnit.MILLISECONDS)) {
                lockResult = new LockResult(LockResult.SUCCESS, key, lock);
            } else {
                lockResult = new LockResult(LockResult.FAILURE, key, lock);
            }
        } catch (Exception e) {
            lockResult = new LockResult(LockResult.EXCEPTION, key, null);
        }
        return lockResult;
    }
}
