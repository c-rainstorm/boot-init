package me.rainstorm.boot.domain.util.log;

import lombok.Data;

/**
 * @author baochen1.zhang
 * @date 2019.07.05
 */
@Data
class LogEntity {
    private LogLevel level;

    private String category;
    private String method;

    private String filter1;
    private String filter2;

    private Long startTime;
    private String message;
    private Object request;
    private Object response;
    private Throwable throwable;

    LogEntity() {
        this.level = LogLevel.INFO;
    }

    LogEntity(String category, String method) {
        this.category = category;
        this.method = method;
        this.level = LogLevel.INFO;
    }
}
