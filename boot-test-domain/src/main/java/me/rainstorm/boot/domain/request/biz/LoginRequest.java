package me.rainstorm.boot.domain.request.biz;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.rainstorm.boot.domain.request.BaseRequest;

import javax.validation.constraints.NotBlank;

/**
 * @author baochen1.zhang
 * @date 2019.07.05
 */
@ApiModel("登录请求")
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginRequest extends BaseRequest {
    @ApiModelProperty(name = "username", value = "用户名", required = true, position = 3, example = "rainstorm.me")
    @NotBlank(message = "用户名不能为空")
    @JsonProperty("username")
    private String username;

    @ApiModelProperty(name = "password", value = "密码", required = true, position = 4, example = "rainstorm.me")
    @NotBlank(message = "密码不能为空")
    @JsonProperty("password")
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(@NotBlank(message = "用户名不能为空") String username,
                        @NotBlank(message = "密码不能为空") String password) {
        this.username = username;
        this.password = password;
    }
}
