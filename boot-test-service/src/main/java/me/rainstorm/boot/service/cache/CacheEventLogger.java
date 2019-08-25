package me.rainstorm.boot.service.cache;

import me.rainstorm.boot.domain.util.log.LogBuilder;
import me.rainstorm.boot.domain.util.log.LogUtil;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

/**
 * @author baochen1.zhang
 * @date 2019.08.24
 */
public class CacheEventLogger implements CacheEventListener<Object, Object> {
    private static final String CATEGORY = CacheEventLogger.class.getSimpleName();

    @Override
    public void onEvent(CacheEvent<?, ?> event) {
        LogUtil.info(LogBuilder.init(CATEGORY, "onEvent")
                .setMessage(String.format("Cache event EXPIRED for item with key %s. Old value = %s,New value = %s",
                        event.getKey(), event.getOldValue(), event.getNewValue())).build());
    }
}