package me.rainstorm.boot.domain.response.biz;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.rainstorm.boot.domain.response.BaseResponse;

/**
 * @author baochen1.zhang
 * @date 2019.07.05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponse extends BaseResponse {
    @JsonProperty("exist")
    private boolean exist;

    public LoginResponse() {
    }

    public LoginResponse(boolean exist) {
        this.exist = exist;
    }

    @Override
    public boolean isSuccess() {
        return super.isSuccess() && exist;
    }
}
