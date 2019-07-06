package me.rainstorm.boot;

import com.fasterxml.jackson.core.type.TypeReference;
import me.rainstorm.boot.domain.constant.GenderEnum;
import me.rainstorm.boot.domain.constant.ResponseCodeEnum;
import me.rainstorm.boot.domain.entity.User;
import me.rainstorm.boot.domain.request.biz.LoginRequest;
import me.rainstorm.boot.domain.request.biz.SignUpRequest;
import me.rainstorm.boot.domain.response.biz.DataResponse;
import me.rainstorm.boot.domain.response.biz.LoginResponse;
import me.rainstorm.boot.domain.response.biz.SignUpResponse;
import me.rainstorm.boot.domain.util.JsonUtil;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @author baochen1.zhang
 * @date 2019.07.04
 */

public class UserControllerTest extends BaseController {
    @Test
    public void loginTest() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUsername("rainstorm.me");
        request.setPassword("rainstorm.me");

        LoginResponse response = post("/user/login", request, LoginResponse.class);

        assert ResponseCodeEnum.SUCCESS.getCode().equals(response.getRespCode());
        assert !response.isExist();
    }

    @Test
    public void signUpTest() throws Exception {
        SignUpRequest request = new SignUpRequest("rainstorm.me", "rainstorm.me");
        request.setBirthday(LocalDateTime.now());
        request.setGender(GenderEnum.MALE);
        System.out.println(JsonUtil.toJsonString(request));
        SignUpResponse response = post("/user/signup", request, SignUpResponse.class);

        assert ResponseCodeEnum.SUCCESS.getCode().equals(response.getRespCode());
        assert response.isSuccess();
    }

    @Test
    public void get() throws Exception {
        String username = "rainstorm.me";
        DataResponse<User> response = get("/user/" + username, new TypeReference<DataResponse<User>>() {
        });

        assert ResponseCodeEnum.SUCCESS.getCode().equals(response.getRespCode());
        assert response.isSuccess();
    }
}
