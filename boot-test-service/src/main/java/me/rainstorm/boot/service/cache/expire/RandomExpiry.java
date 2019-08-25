package me.rainstorm.boot.service.cache.expire;

import lombok.Data;
import me.rainstorm.boot.domain.util.log.LogBuilder;
import me.rainstorm.boot.domain.util.log.LogUtil;
import org.ehcache.expiry.ExpiryPolicy;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * @author baochen1.zhang
 * @date 2019.08.24
 */
@Data
public class RandomExpiry<Key, Value> implements ExpiryPolicy<Key, Value> {
    private static final String CATEGORY = RandomExpiry.class.getSimpleName();
    /**
     * 本地缓存超时时间，单位秒，超时时间不是绝对值，具体超时时间与 timeoutDelta 一起决定
     */
    private long localCacheTimeout = 300;

    /**
     * 用于打散超时时间，本地缓存 timeout 由 localCacheTimeout 和 timeoutDelta 两个变量来决定，
     * 超时区间是 [localCacheTimeout - timeoutDelta, localCacheTimeout + timeoutDelta]
     */
    private long localCacheTimeoutDelta = 60;

    private boolean localCacheExpire = true;

    private TemporalUnit timeUnit = ChronoUnit.SECONDS;

    private Duration getNewDuration() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        long timeout = localCacheTimeout + random.nextLong(-localCacheTimeoutDelta, localCacheTimeoutDelta);

        return Duration.of(timeout, timeUnit);
    }

    @Override
    public Duration getExpiryForCreation(Key key, Value value) {
        final String logMethodName = "getExpiryForCreation";
        if (localCacheExpire) {
            Duration duration = getNewDuration();
            LogUtil.info(LogBuilder.init(CATEGORY, logMethodName)
                    .setMessage(String.format("new expiry:%s", duration)).build());
            return duration;
        }
        return null;
    }

    @Override
    public java.time.Duration getExpiryForAccess(Key key, Supplier<? extends Value> value) {
        return null;
    }

    @Override
    public java.time.Duration getExpiryForUpdate(Key key, Supplier<? extends Value> oldValue, Value newValue) {
        final String logMethodName = "getExpiryForUpdate";
        if (localCacheExpire) {
            Duration duration = getNewDuration();
            LogUtil.info(LogBuilder.init(CATEGORY, logMethodName)
                    .setMessage(String.format("new expiry:%s", duration)).build());
            return duration;
        }
        return null;
    }
}
