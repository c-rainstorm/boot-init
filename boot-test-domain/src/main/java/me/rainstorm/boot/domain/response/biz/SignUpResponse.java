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
public class SignUpResponse extends BaseResponse {
    @JsonProperty("done")
    private boolean done;

    @Override
    public boolean isSuccess() {
        return super.isSuccess() && done;
    }
}
