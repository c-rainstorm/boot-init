package me.rainstorm.boot.domain.util.log;

import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

/**
 * @author baochen1.zhang
 * @date 2019.07.05
 */
@Slf4j(topic = "com.rainstorm.boot.skyLog")
public class LogUtil {
    public static void log(@NotNull LogEntity logEntity) {
        LogLevel level = logEntity.getLevel();
        switch (level) {
            case TRACE:
                trace(logEntity);
                break;
            case DEBUG:
                debug(logEntity);
                break;
            case WARN:
                warn(logEntity);
                break;
            case ERROR:
                error(logEntity);
                break;
            default:
                info(logEntity);
                break;
        }
    }

    public static void debug(@NotNull LogEntity logEntity) {
        if (log.isDebugEnabled()) {
            log.debug(logEntity.getMessage());
        }
    }

    public static void error(@NotNull LogEntity logEntity) {
        if (log.isErrorEnabled()) {
            log.error(logEntity.getMessage(), logEntity.getThrowable());
        }
    }

    public static void trace(@NotNull LogEntity logEntity) {
        if (log.isTraceEnabled()) {
            log.trace(logEntity.getMessage());
        }
    }

    public static void warn(@NotNull LogEntity logEntity) {
        if (log.isWarnEnabled()) {
            log.warn(logEntity.getMessage());
        }
    }

    public static void info(@NotNull LogEntity logEntity) {
        if (log.isInfoEnabled()) {
            log.info(logEntity.getMessage());
        }
    }
}
