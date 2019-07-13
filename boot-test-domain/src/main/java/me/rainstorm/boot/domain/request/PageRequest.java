package me.rainstorm.boot.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author baochen1.zhang
 * @date 2019.07.11
 */
@ApiModel(value = "分页请求", parent = BaseRequest.class)
@EqualsAndHashCode(callSuper = true)
@Data
public class PageRequest extends BaseRequest {
    @ApiModelProperty(value = "页码", example = "1")
    @Min(value = 1, message = "页码不能小于1")
    @JsonProperty("page_index")
    private Integer pageIndex;

    @ApiModelProperty(value = "每页记录数", example = "50")
    @Min(value = 1, message = "每页不能小于1条")
    @Max(value = 1000, message = "每页不能超过 1000 条")
    @JsonProperty("page_size")
    private Integer pageSize;
}
