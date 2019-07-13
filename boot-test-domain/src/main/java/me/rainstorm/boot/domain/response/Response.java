package me.rainstorm.boot.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("基础响应类")
@Data
public class Response<T> implements IResponse, BizStatus {

    @ApiModelProperty(name = "resp_code", value = "响应码", dataType = "String", example = "0")
    @JsonProperty("resp_code")
    private String respCode;

    @ApiModelProperty(name = "resp_msg", value = "响应消息", dataType = "String", position = 1, example = "成功")
    @JsonProperty("resp_msg")
    private String respMsg;

    @ApiModelProperty(name = "server_ip", value = "服务器IP", dataType = "String", position = 2, example = "127.0.0.1")
    @JsonProperty("server_ip")
    private String serverIp;

    @ApiModelProperty(name = "server_time", value = "服务器响应时间", dataType = "LocalDateTime", position = 3, example = "2019-07-10 23:22:00")
    @JsonFormat(pattern = DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS)
    @JsonProperty("server_time")
    private LocalDateTime serverTime;

    @ApiModelProperty(name = "data", value = "具体的业务响应", position = 4)
    @JsonProperty("data")
    private T data;

    public Response() {
        this(ResponseCodeEnum.INTERVAL_SERVER_ERROR);
    }

    public Response(ResponseCodeEnum responseCode) {
        this(ObjectUtils.defaultIfNull(responseCode, ResponseCodeEnum.INTERVAL_SERVER_ERROR).getCode(),
                ObjectUtils.defaultIfNull(responseCode, ResponseCodeEnum.INTERVAL_SERVER_ERROR).getDesc());
    }


    public Response(ResponseCodeEnum responseCode, String respMsg) {
        this(ObjectUtils.defaultIfNull(responseCode, ResponseCodeEnum.INTERVAL_SERVER_ERROR).getCode(), respMsg);
    }

    public Response(String respCode, String respMsg) {
        init(respCode, respMsg);
        this.serverIp = LocalHost.getMachineIp();
        this.serverTime = LocalDateTime.now();
    }

    public Response(T data) {
        this(ResponseCodeEnum.SUCCESS);
        this.data = data;
    }

    @Override
    public IResponse success() {
        return init(ResponseCodeEnum.SUCCESS);
    }

    @Override
    public IResponse failure() {
        return init(ResponseCodeEnum.INTERVAL_SERVER_ERROR);
    }

    @Override
    public IResponse failure(String msg) {
        return init(ResponseCodeEnum.INTERVAL_SERVER_ERROR.getCode(), msg);
    }

    @Override
    public IResponse failure(String errCode, String respMsg) {
        return init(errCode, respMsg);
    }

    @Override
    public IResponse failure(ResponseCodeEnum responseCode) {
        return init(ObjectUtils.defaultIfNull(responseCode, ResponseCodeEnum.INTERVAL_SERVER_ERROR));
    }

    @Override
    public IResponse failure(ResponseCodeEnum responseCode, String msg) {
        return init(ObjectUtils.defaultIfNull(responseCode, ResponseCodeEnum.INTERVAL_SERVER_ERROR).getCode(), msg);
    }

    @Override
    public IResponse failure(Throwable ex) {
        if (ex == null) {
            return failure();
        }

        if (ex instanceof CommonBizException) {
            return failure(((CommonBizException) ex).getResponseCode(), ex.getMessage());
        }

        return failure(ex.getMessage());
    }

    private IResponse init(ResponseCodeEnum responseCode) {
        return init(responseCode.getCode(), responseCode.getDesc());
    }

    private IResponse init(String respCode, String respMsg) {
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
