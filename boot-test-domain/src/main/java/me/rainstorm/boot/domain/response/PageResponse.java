package me.rainstorm.boot.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.rainstorm.boot.domain.constant.ResponseCodeEnum;

/**
 * @author baochen1.zhang
 * @date 2019.07.11
 */
@ApiModel("分页响应")
@EqualsAndHashCode(callSuper = true)
@Data
public class PageResponse<T> extends Response<T> {
    @ApiModelProperty(name = "pageIndex", value = "响应页码", position = 5)
    @JsonProperty("page_index")
    private Integer pageIndex;

    @ApiModelProperty(name = "pageSize", value = "每页记录数", position = 6)
    @JsonProperty("page_size")
    private Integer pageSize;

    @ApiModelProperty(name = "total", value = "总记录数", position = 7)
    @JsonProperty("total")
    private Integer total;

    public PageResponse() {
    }

    public PageResponse(ResponseCodeEnum responseCode) {
        super(responseCode);
    }

    public PageResponse(ResponseCodeEnum responseCode, String respMsg) {
        super(responseCode, respMsg);
    }

    public PageResponse(String respCode, String respMsg) {
        super(respCode, respMsg);
    }

    public PageResponse(T data) {
        super(data);
    }
}
