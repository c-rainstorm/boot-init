package me.rainstorm.boot.service.cache;

/**
 * @author baochen1.zhang
 * @date 2019.08.24
 */
public interface CacheService<Key, Value> {

    Value get(Key key);
}
