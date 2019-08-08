package me.rainstorm.boot.service.lock;


/**
 * @author baochen1.zhang
 * @date 2019.08.03
 */
public interface DLock {
    LockResult tryLock(String key, long timeout);
}
