package me.rainstorm.boot.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.rainstorm.boot.domain.constant.ResponseCodeEnum;

/**
 * @author baochen1.zhang
 * @date 2019.07.05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommonBizException extends RuntimeException {
    private ResponseCodeEnum responseCode;

    public CommonBizException(String message, ResponseCodeEnum responseCode) {
        super(message);
        this.responseCode = responseCode;
    }

    public CommonBizException(String message, Throwable cause, ResponseCodeEnum responseCode) {
        super(message, cause);
        this.responseCode = responseCode;
    }

    public CommonBizException(Throwable cause, ResponseCodeEnum responseCode) {
        super(cause);
        this.responseCode = responseCode;
    }

    public CommonBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ResponseCodeEnum responseCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.responseCode = responseCode;
    }
}
