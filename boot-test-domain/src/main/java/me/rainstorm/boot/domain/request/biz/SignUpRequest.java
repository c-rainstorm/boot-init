package me.rainstorm.boot.domain.request.biz;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.rainstorm.boot.domain.constant.GenderEnum;
import me.rainstorm.boot.domain.util.DateTimeFormatterUtil;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;

/**
 * @author baochen1.zhang
 * @date 2019.07.05
 */
@ApiModel("注册请求")
@EqualsAndHashCode(callSuper = true)
@Data
public class SignUpRequest extends LoginRequest {

    @ApiModelProperty(name = "birthday", value = "生日", dataType = "LocalDateTime", position = 5, example = "2019-07-10 23:22:00")
    @Past(message = "生日只能在过去")
    @JsonFormat(pattern = DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS)
    @JsonProperty("birthday")
    private LocalDateTime birthday;

    @ApiModelProperty(name = "gender", value = "性别", position = 6, example = "MALE")
    @JsonProperty("gender")
    private GenderEnum gender;

    public SignUpRequest() {
    }

    public SignUpRequest(@NotBlank(message = "用户名不能为空") String username,
                         @NotBlank(message = "密码不能为空") String password) {
        super(username, password);
    }
}
