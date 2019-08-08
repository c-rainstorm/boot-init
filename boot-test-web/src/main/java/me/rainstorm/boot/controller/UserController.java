package me.rainstorm.boot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.rainstorm.boot.domain.constant.ResponseCodeEnum;
import me.rainstorm.boot.domain.entity.User;
import me.rainstorm.boot.domain.request.PageRequest;
import me.rainstorm.boot.domain.request.biz.LoginRequest;
import me.rainstorm.boot.domain.request.biz.SignUpRequest;
import me.rainstorm.boot.domain.response.PageResponse;
import me.rainstorm.boot.domain.response.Response;
import me.rainstorm.boot.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @author baochen1.zhang
 * @date 2019.06.23
 */
@Api("用户相关操作")
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public Response<Boolean> login(@RequestBody @Valid LoginRequest loginRequest, BindingResult bindingResult) {
        User userModel = convertToModel(loginRequest);
        Boolean success = userService.exist(userModel);
        return new Response<>(Boolean.TRUE.equals(success));
    }

    @ApiOperation(value = "注册")
    @PostMapping("signup")
    public Response<Boolean> signup(@RequestBody @Valid SignUpRequest signUpRequest, BindingResult bindingResult) throws Exception {
        User userModel = convertToModel(signUpRequest);

        userModel.setBirthday(signUpRequest.getBirthday());
        if (Objects.nonNull(signUpRequest.getGender())) {
            userModel.setGender(signUpRequest.getGender().getCode());
        }

        Boolean success = userService.signUp(userModel);

        return new Response<>(Boolean.TRUE.equals(success));
    }

    private User convertToModel(LoginRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        return user;
    }

    @ApiOperation(value = "根据用户名查询")
    @ApiImplicitParam(name = "username", required = true, example = "rainstorm.me", dataType = "String", dataTypeClass = String.class)
    @GetMapping("/{username}")
    public Response<User> get(@PathVariable("username") String username) {
        if (StringUtils.isBlank(username)) {
            return new Response<>(ResponseCodeEnum.BAD_REQUEST, "用户名不能为空");
        }

        User user = new User();
        user.setUsername(username);
        User query = userService.select(user);

        return new Response<>(query);
    }

    @ApiOperation(value = "分页查询User")
    @PostMapping("/page")
    public PageResponse<List<User>> queryPage(@RequestBody @Valid PageRequest pageRequest, BindingResult bindingResult) {
        List<User> users = userService.select(pageRequest);

        PageResponse<List<User>> response = new PageResponse<>(users);
        response.setPageIndex(pageRequest.getPageIndex());
        response.setPageSize(pageRequest.getPageSize());
        response.setTotal(pageRequest.getPageSize() * pageRequest.getPageIndex());
        return response;
    }
}
