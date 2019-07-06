package me.rainstorm.boot.domain.request.biz;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.rainstorm.boot.domain.request.BaseRequest;

import javax.validation.constraints.NotBlank;

/**
 * @author baochen1.zhang
 * @date 2019.07.05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginRequest extends BaseRequest {
    @NotBlank(message = "用户名不能为空")
    @JsonProperty("username")
    private String username;

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
