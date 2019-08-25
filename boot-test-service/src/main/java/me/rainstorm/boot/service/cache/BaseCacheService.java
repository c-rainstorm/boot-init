package me.rainstorm.boot.service.cache;

import me.rainstorm.boot.domain.util.JsonUtil;
import me.rainstorm.boot.domain.util.log.LogBuilder;
import me.rainstorm.boot.domain.util.log.LogUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.cache.Cache;
import javax.cache.CacheManager;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @author baochen1.zhang
 * @date 2019.08.24
 */
@Component
public abstract class BaseCacheService<Key, Value> implements CacheService<Key, Value> {
    private static final String CATEGORY = BaseCacheService.class.getSimpleName();

    @Resource(name = "ehcacheCacheManager")
    private CacheManager cacheManager;

    protected Cache<Key, Value> cache;

    protected abstract String getCacheName();

    protected void init(Function<Value, Key> getKey) {
        final String logMethodName = "init";

        cache = cacheManager.getCache(getCacheName());
        List<Value> users = initList();
        users.forEach(x -> cache.put(getKey.apply(x), x));

        LogUtil.info(LogBuilder.init(CATEGORY, logMethodName)
                .setMessage(JsonUtil.toJsonString(users)).build());
    }

    protected List<Value> initList() {
        return Collections.emptyList();
    }
}
