package me.rainstorm.boot.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.rainstorm.boot.domain.util.DateTimeFormatterUtil;
import me.rainstorm.boot.domain.util.LocalHost;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author baochen1.zhang
 * @date 2019.07.04
 */
@ApiModel("基础请求类")
@Data
public class BaseRequest implements Request {
    @ApiModelProperty(name = "request_ip", value = "请求服务器IP", required = true, dataType = "String", example = "127.0.0.1")
    @NotBlank(message = "请求服务器IP不能为空")
    @JsonProperty("request_ip")
    private String requestIp;

    @ApiModelProperty(name = "request_time", value = "服务器请求时间", required = true, dataType = "LocalDateTime", position = 1, example = "2019-07-10 23:22:00")
    @NotNull(message = "服务器请求时间")
    @JsonProperty("request_time")
    @JsonFormat(pattern = DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime requestTime;

    public BaseRequest() {
        this.requestIp = LocalHost.getMachineIp();
        this.requestTime = LocalDateTime.now();
    }
}
