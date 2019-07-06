package me.rainstorm.boot.controller;

import me.rainstorm.boot.domain.constant.ResponseCodeEnum;
import me.rainstorm.boot.domain.entity.User;
import me.rainstorm.boot.domain.request.biz.LoginRequest;
import me.rainstorm.boot.domain.request.biz.SignUpRequest;
import me.rainstorm.boot.domain.response.BaseResponse;
import me.rainstorm.boot.domain.response.Response;
import me.rainstorm.boot.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;

/**
 * @author baochen1.zhang
 * @date 2019.06.23
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("login")
    public Response login(@RequestBody @Valid LoginRequest loginRequest, BindingResult bindingResult) {
        User userModel = convertToModel(loginRequest);

        return userService.exist(userModel);
    }

    @PostMapping("signup")
    public Response signup(@RequestBody @Valid SignUpRequest signUpRequest, BindingResult bindingResult) {
        User userModel = convertToModel(signUpRequest);

        userModel.setBirthday(signUpRequest.getBirthday());
        if (Objects.nonNull(signUpRequest.getGender())) {
            userModel.setGender(signUpRequest.getGender().getCode());
        }

        return userService.signUp(userModel);
    }

    private User convertToModel(LoginRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        return user;
    }

    @GetMapping("/{username}")
    public Response get(@PathVariable("username") String username) {
        if (StringUtils.isBlank(username)) {
            return new BaseResponse(ResponseCodeEnum.BAD_REQUEST, "用户名不能为空");
        }

        User user = new User();
        user.setUsername(username);

        return userService.select(user);
    }
}
