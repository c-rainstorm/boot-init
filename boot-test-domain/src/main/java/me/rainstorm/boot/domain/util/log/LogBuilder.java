package me.rainstorm.boot.domain.util.log;

import me.rainstorm.boot.domain.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author baochen1.zhang
 * @date 2019.07.05
 */
public class LogBuilder {
    private LogEntity logEntity;

    private LogBuilder() {
        logEntity = new LogEntity("Default", "Default");
    }

    private LogBuilder(String category, String method) {
        logEntity = new LogEntity(category, method);
    }

    public static LogBuilder init(String category, String method) {
        return new LogBuilder(category, method);
    }

    public LogBuilder setLevel(LogLevel level) {
        if (level != null) {
            logEntity.setLevel(level);
        }
        return this;
    }

    public LogBuilder setFilter(String filter) {
        return setFilter(filter, filter);
    }

    public LogBuilder setFilter(String filter1, String filter2) {
        if (StringUtils.isNotBlank(filter1)) {
            logEntity.setFilter1(filter1);
        }
        if (StringUtils.isNotBlank(filter2)) {
            logEntity.setFilter2(filter2);
        }

        return this;
    }

    public LogBuilder setStartTime(Long startTime) {
        if (Objects.nonNull(startTime)) {
            logEntity.setStartTime(startTime);
        }
        return this;
    }

    public LogBuilder setMessage(String msg) {
        if (StringUtils.isNotBlank(msg)) {
            logEntity.setMessage(msg);
        }
        return this;
    }

    public LogBuilder setMessage(String msg, Throwable e) {
        if (StringUtils.isNotBlank(msg)) {
            logEntity.setMessage(msg);
        }

        if (e != null) {
            logEntity.setThrowable(e);
        }
        return this;
    }

    public LogBuilder setTraceEntity(Object request, Object response) {
        if (request != null) {
            logEntity.setRequest(request);
        }
        if (response != null) {
            logEntity.setResponse(response);
        }
        return this;
    }

    public LogEntity build() {
        StringBuilder builder = new StringBuilder(100);

        if (Objects.nonNull(logEntity.getStartTime())) {
            builder.append("执行耗时: ")
                    .append(System.currentTimeMillis() - logEntity.getStartTime())
                    .append("ms");
        }

        if (Objects.nonNull(logEntity.getMessage())) {
            builder.append("; message: ")
                    .append(logEntity.getMessage());
        }

        if (Objects.nonNull(logEntity.getRequest())) {
            builder.append("; request: ")
                    .append(JsonUtil.toJsonString(logEntity.getRequest()));
        }

        if (Objects.nonNull(logEntity.getResponse())) {
            builder.append("; response: ")
                    .append(JsonUtil.toJsonString(logEntity.getResponse()));
        }

        if (Objects.nonNull(logEntity.getThrowable())) {
            logEntity.setLevel(LogLevel.ERROR);
            builder.append("; Exception: ")
                    .append(logEntity.getThrowable().getMessage());
        }

        String message = builder.toString();

        String msg = String.format("[%s][%s][%s][%s] - %s",
                StringUtils.defaultString(logEntity.getCategory()),
                StringUtils.defaultString(logEntity.getMethod()),
                StringUtils.defaultString(logEntity.getFilter1()),
                StringUtils.defaultString(logEntity.getFilter2()),
                StringUtils.defaultString(message));

        logEntity.setMessage(msg);
        return logEntity;
    }
}
