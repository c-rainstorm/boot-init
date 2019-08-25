package me.rainstorm.boot.service.cache.expire;


/**
 * @author baochen1.zhang
 * @date 2019.08.24
 */
public class UserCacheExpiry extends RandomExpiry {
    public UserCacheExpiry() {
        setLocalCacheTimeout(30);
        setLocalCacheTimeoutDelta(10);
    }
}
