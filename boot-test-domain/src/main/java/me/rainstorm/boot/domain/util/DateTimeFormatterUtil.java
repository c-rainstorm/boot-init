package me.rainstorm.boot.domain.util;

import lombok.NonNull;
import org.apache.commons.lang3.ObjectUtils;

import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author baochen1.zhang
 * @date 2019.07.06
 */
public class DateTimeFormatterUtil {
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYYMMDD = "yyyyMMdd";

    private static volatile Map<String, DateTimeFormatter> formatterCache = new ConcurrentHashMap<>();

    static {
        for (DateTimePatternEnum patternEnum : DateTimePatternEnum.values()) {
            formatterCache.put(patternEnum.getPattern(), DateTimeFormatter.ofPattern(patternEnum.getPattern()));
        }
    }

    public static DateTimeFormatter get(@NonNull String pattern) {
        DateTimeFormatter formatter = formatterCache.get(pattern);
        if (formatter == null) {
            formatter = formatterCache.putIfAbsent(pattern, DateTimeFormatter.ofPattern(pattern));
        }
        return ObjectUtils.defaultIfNull(formatter, formatterCache.get(pattern));
    }

    public static DateTimeFormatter get(@NonNull DateTimePatternEnum patternEnum) {
        String pattern = patternEnum.getPattern();
        return get(pattern);
    }

    enum DateTimePatternEnum {
        /**
         * 秒级
         */
        YYYY_MM_DD_HH_MM_SS(DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS, null),
        /**
         * 分钟级
         */
        YYYY_MM_DD_HH_MM(DateTimeFormatterUtil.YYYY_MM_DD_HH_MM, null),
        /**
         * 天级
         */
        YYYY_MM_DD(DateTimeFormatterUtil.YYYY_MM_DD, DateTimeFormatter.ISO_LOCAL_DATE),

        /**
         * 秒级，无连接符
         */
        YYYYMMDDHHMMSS(DateTimeFormatterUtil.YYYYMMDDHHMMSS, null),

        /**
         * 天级，无连接符
         */
        YYYYMMDD(DateTimeFormatterUtil.YYYYMMDD, null);

        DateTimePatternEnum(String pattern, DateTimeFormatter formatter) {
            this.pattern = pattern;
            DateTimeFormatter f = formatter;
            if (f == null) {
                f = DateTimeFormatter.ofPattern(pattern);
            }
            this.formatter = f;
        }

        private String pattern;
        private DateTimeFormatter formatter;

        public String getPattern() {
            return pattern;
        }

        public DateTimeFormatter getFormatter() {
            return formatter;
        }
    }
}
