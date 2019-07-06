package me.rainstorm.boot.domain.util.log;

import java.util.EnumSet;

/**
 * @author baochen1.zhang
 * @date 2019.07.05
 */
public enum LogLevel {

    /**
     * An error in the application, possibly recoverable.
     */
    ERROR(200),

    /**
     * An event that might possible lead to an error.
     */
    WARN(300),

    /**
     * An event for informational purposes.
     */
    INFO(400),

    /**
     * A general debugging event.
     */
    DEBUG(500),

    /**
     * A fine-grained debug message, typically capturing the flow through the application.
     */
    TRACE(600);

    private static final EnumSet<LogLevel> LEVELSET = EnumSet.allOf(LogLevel.class);

    private Integer intLevel;

    LogLevel(Integer intLevel) {
        this.intLevel = intLevel;
    }

    public Integer getIntLevel() {
        return intLevel;
    }

    public static LogLevel getLogLevel(final int intLevel) {
        LogLevel level = LogLevel.INFO;
        for (final LogLevel lvl : LEVELSET) {
            if (lvl.getIntLevel() > intLevel) {
                break;
            }
            level = lvl;
        }
        return level;
    }
}
