package me.rainstorm.boot.service.impl;

import me.rainstorm.boot.dao.boot.UserDao;
import me.rainstorm.boot.domain.constant.ResponseCodeEnum;
import me.rainstorm.boot.domain.entity.User;
import me.rainstorm.boot.domain.exception.CommonBizException;
import me.rainstorm.boot.domain.response.Response;
import me.rainstorm.boot.domain.response.biz.DataResponse;
import me.rainstorm.boot.domain.response.biz.LoginResponse;
import me.rainstorm.boot.domain.response.biz.SignUpResponse;
import me.rainstorm.boot.domain.util.JsonUtil;
import me.rainstorm.boot.domain.util.LocalHost;
import me.rainstorm.boot.domain.util.log.LogBuilder;
import me.rainstorm.boot.domain.util.log.LogUtil;
import me.rainstorm.boot.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author baochen1.zhang
 * @date 2019.07.05
 */
@Service
public class UserServiceImpl implements UserService {
    private static final String CATEGORY = UserServiceImpl.class.getSimpleName();
    @Resource
    private UserDao userDao;

    @Override
    public Response signUp(User user) {
        final String logMethodName = "signUp";

        User query = new User();
        query.setUsername(user.getUsername());

        if (userExist(query)) {
            throw new CommonBizException("用户已存在", ResponseCodeEnum.BIZ_500_0001);
        }

        user.setCreatedBy(LocalHost.getMachineName());
        user.setCreatedOn(LocalDateTime.now());
        user.setUpdatedBy(LocalHost.getMachineName());
        user.setUpdatedOn(LocalDateTime.now());

        LogUtil.info(LogBuilder.init(CATEGORY, logMethodName)
                .setFilter(user.getUsername())
                .setMessage("准备入库" + JsonUtil.toJsonString(user)).build());

        SignUpResponse response = new SignUpResponse();
        boolean saved = userDao.insertSelective(user) > 0;
        response.setDone(saved);
        return response.success();
    }

    @Override
    public Response exist(User user) {
        assert StringUtils.isNotBlank(user.getPassword()) : "登录密码为空";

        LoginResponse response = new LoginResponse();
        response.setExist(userExist(user));
        return response.success();
    }

    @Override
    public Response select(User query) {
        DataResponse<User> response = new DataResponse<>();

        User user = userDao.selectOne(query);
        response.setData(user);

        return response.success();
    }

    private boolean userExist(User user) {
        assert StringUtils.isNotBlank(user.getUsername()) : "登录用户名为空";

        return userDao.find(user) > 0;
    }
}
