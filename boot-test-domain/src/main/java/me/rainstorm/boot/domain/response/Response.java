package me.rainstorm.boot.domain.response;

import me.rainstorm.boot.domain.constant.ResponseCodeEnum;

/**
 * 该接口定义了响应成功或失败
 *
 * @author baochen.zhang
 */
public interface Response {

    /**
     * 处理成功
     *
     * @return 成功响应
     */
    Response success();

    /**
     * 业务处理失败响应
     *
     * @return 业务失败响应
     */
    Response failure();

    /**
     * 业务失败响应
     *
     * @param msg 业务失败消息
     * @return 业务失败响应
     */
    Response failure(String msg);

    /**
     * 系统异常响应
     *
     * @param errCode 系统异常码
     * @param msg     系统异常消息
     * @return 系统异常响应
     */
    Response failure(String errCode, String msg);

    /**
     * 系统异常响应
     *
     * @param responseCode 系统异常响应码
     * @return 系统异常响应
     */
    Response failure(ResponseCodeEnum responseCode);

    /**
     * 系统异常响应
     *
     * @param responseCode 系统异常响应码
     * @param msg          系统异常消息
     * @return 系统异常响应
     */
    Response failure(ResponseCodeEnum responseCode, String msg);

    /**
     * 可能为业务异常，可能为系统异常
     *
     * @param t 如果是 {@link me.rainstorm.boot.domain.exception.CommonBizException CommonBizException} 则为业务异常；
     *          其他异常为系统异常
     * @return 异常响应
     * @see me.rainstorm.boot.domain.exception.CommonBizException
     */
    Response failure(Throwable t);
}
