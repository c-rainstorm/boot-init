package me.rainstorm.boot;

import com.fasterxml.jackson.core.type.TypeReference;
import me.rainstorm.boot.domain.constant.GenderEnum;
import me.rainstorm.boot.domain.constant.ResponseCodeEnum;
import me.rainstorm.boot.domain.entity.User;
import me.rainstorm.boot.domain.request.PageRequest;
import me.rainstorm.boot.domain.request.biz.LoginRequest;
import me.rainstorm.boot.domain.request.biz.SignUpRequest;
import me.rainstorm.boot.domain.response.PageResponse;
import me.rainstorm.boot.domain.response.Response;
import me.rainstorm.boot.domain.util.JsonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

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

        Response<Boolean> response = post("/user/login", request, new TypeReference<Response<Boolean>>() {
        });

        assert response.isSuccess();
        assert response.getData();
    }

    @Test
    public void signUpTest() throws Exception {
        SignUpRequest request = new SignUpRequest("rainstorm.me", "rainstorm.me");
        request.setBirthday(LocalDateTime.now());
        request.setGender(GenderEnum.MALE);
        System.out.println(JsonUtil.toJsonString(request));
        Response<Boolean> response = post("/user/signup", request, new TypeReference<Response<Boolean>>() {
        });

        assert ResponseCodeEnum.BIZ_500_0001.getCode().equals(response.getRespCode());
    }

    @Test
    public void get() throws Exception {
        String username = "rainstorm.me";
        Response<User> response = get("/user/" + username, new TypeReference<Response<User>>() {
        });

        assert response.isSuccess();
        assert response.getData() != null;
        assert username.equals(response.getData().getUsername());
    }

    @Test
    public void page() throws Exception {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageIndex(1);
        pageRequest.setPageSize(10);

        PageResponse<List<User>> response = post("/user/page", pageRequest, new TypeReference<PageResponse<List<User>>>() {
        });

        assert response.isSuccess();
        assert CollectionUtils.isNotEmpty(response.getData());
    }
}
