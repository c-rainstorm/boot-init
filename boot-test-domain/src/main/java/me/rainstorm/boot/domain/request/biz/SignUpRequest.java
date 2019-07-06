package me.rainstorm.boot.domain.request.biz;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.rainstorm.boot.domain.constant.GenderEnum;
import me.rainstorm.boot.domain.util.DateTimeFormatterUtil;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author baochen1.zhang
 * @date 2019.07.05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SignUpRequest extends LoginRequest {

    @JsonProperty("birthday")
    @JsonFormat(pattern = DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime birthday;

    @JsonProperty("gender")
    private GenderEnum gender;

    public SignUpRequest() {
    }

    public SignUpRequest(@NotBlank(message = "用户名不能为空") String username,
                         @NotBlank(message = "密码不能为空") String password) {
        super(username, password);
    }
}
