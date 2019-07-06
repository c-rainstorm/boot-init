package me.rainstorm.boot.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.rainstorm.boot.domain.constant.ResponseCodeEnum;
import me.rainstorm.boot.domain.exception.CommonBizException;
import me.rainstorm.boot.domain.util.DateTimeFormatterUtil;
import me.rainstorm.boot.domain.util.LocalHost;
import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDateTime;

/**
 * @author baochen1.zhang
 * @date 2019.07.04
 */
@Data
public class BaseResponse implements Response, BizStatus {
    @JsonProperty("resp_code")
    private String respCode;

    @JsonProperty("resp_msg")
    private String respMsg;

    @JsonProperty("server_ip")
    private String serverIp;

    @JsonFormat(pattern = DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS)
    @JsonProperty("server_time")
    private LocalDateTime serverTime;

    public BaseResponse() {
        this(ResponseCodeEnum.INTERVAL_SERVER_ERROR);
    }

    public BaseResponse(ResponseCodeEnum responseCode) {
        this(ObjectUtils.defaultIfNull(responseCode, ResponseCodeEnum.INTERVAL_SERVER_ERROR).getCode(),
                ObjectUtils.defaultIfNull(responseCode, ResponseCodeEnum.INTERVAL_SERVER_ERROR).getDesc());
    }


    public BaseResponse(ResponseCodeEnum responseCode, String respMsg) {
        this(ObjectUtils.defaultIfNull(responseCode, ResponseCodeEnum.INTERVAL_SERVER_ERROR).getCode(), respMsg);
    }

    public BaseResponse(String respCode, String respMsg) {
        init(respCode, respMsg);
        this.serverIp = LocalHost.getMachineIp();
        this.serverTime = LocalDateTime.now();
    }

    @Override
    public Response success() {
        return init(ResponseCodeEnum.SUCCESS);
    }

    @Override
    public Response failure() {
        return init(ResponseCodeEnum.INTERVAL_SERVER_ERROR);
    }

    @Override
    public Response failure(String msg) {
        return init(ResponseCodeEnum.INTERVAL_SERVER_ERROR.getCode(), msg);
    }

    @Override
    public Response failure(String errCode, String respMsg) {
        return init(errCode, respMsg);
    }

    @Override
    public Response failure(ResponseCodeEnum responseCode) {
        return init(ObjectUtils.defaultIfNull(responseCode, ResponseCodeEnum.INTERVAL_SERVER_ERROR));
    }

    @Override
    public Response failure(ResponseCodeEnum responseCode, String msg) {
        return init(ObjectUtils.defaultIfNull(responseCode, ResponseCodeEnum.INTERVAL_SERVER_ERROR).getCode(), msg);
    }

    @Override
    public Response failure(Throwable ex) {
        if (ex == null) {
            return failure();
        }

        if (ex instanceof CommonBizException) {
            return failure(((CommonBizException) ex).getResponseCode(), ex.getMessage());
        }

        return failure(ex.getMessage());
    }

    private Response init(ResponseCodeEnum responseCode) {
        return init(responseCode.getCode(), responseCode.getDesc());
    }

    private Response init(String respCode, String respMsg) {
        configRespCode(ObjectUtils.defaultIfNull(respCode, ResponseCodeEnum.INTERVAL_SERVER_ERROR.getCode()));
        configRespMsg(respMsg);
        return this;
    }

    private void configRespMsg(String desc) {
        this.respMsg = desc;
    }

    private void configRespCode(String code) {
        this.respCode = code;
    }

    @Override
    public boolean isSuccess() {
        return ResponseCodeEnum.SUCCESS.getCode().equals(this.respCode) ||
                ResponseCodeEnum.ACCEPT.getCode().equals(this.respCode);
    }
}
