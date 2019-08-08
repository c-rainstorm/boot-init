package me.rainstorm.boot.service.impl;

import me.rainstorm.boot.dao.boot.UserDao;
import me.rainstorm.boot.domain.constant.ResponseCodeEnum;
import me.rainstorm.boot.domain.entity.User;
import me.rainstorm.boot.domain.exception.CommonBizException;
import me.rainstorm.boot.domain.request.PageRequest;
import me.rainstorm.boot.domain.util.JsonUtil;
import me.rainstorm.boot.domain.util.LocalHost;
import me.rainstorm.boot.domain.util.log.LogBuilder;
import me.rainstorm.boot.domain.util.log.LogUtil;
import me.rainstorm.boot.service.UserService;
import me.rainstorm.boot.service.lock.LockResult;
import me.rainstorm.boot.service.lock.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author baochen1.zhang
 * @date 2019.07.05
 */
@Service
public class UserServiceImpl implements UserService {
    private static final String CATEGORY = UserServiceImpl.class.getSimpleName();
    @Resource
    private RedisService redisService;
    @Resource
    private UserDao userDao;

    @Override
    public Boolean signUp(User user) throws Exception {
        final String logMethodName = "signUp";

        String key = "signup_" + user.getUsername();
        try (LockResult lockResult = redisService.tryLock(key, 3000L)) {
            if (lockResult.failure()) {
                throw new CommonBizException("当前用户名正在注册中", ResponseCodeEnum.CONCURRENT_OPERATE_ERROR);
            }

            User query = new User();
            query.setUsername(user.getUsername());

            if (userExist(query)) {
                throw new CommonBizException("用户已存在", ResponseCodeEnum.USER_EXISTS);
            }

            user.setCreatedBy(LocalHost.getMachineName());
            user.setCreatedOn(LocalDateTime.now());
            user.setUpdatedBy(LocalHost.getMachineName());
            user.setUpdatedOn(LocalDateTime.now());

            LogUtil.info(LogBuilder.init(CATEGORY, logMethodName)
                    .setFilter(user.getUsername())
                    .setMessage("准备入库" + JsonUtil.toJsonString(user)).build());

            return userDao.insertSelective(user) > 0;
        }
    }

    @Override
    public Boolean exist(User user) {
        assert StringUtils.isNotBlank(user.getPassword()) : "登录密码为空";

        return userExist(user);
    }

    @Override
    public User select(User query) {
        return userDao.selectOne(query);
    }

    @Override
    public List<User> select(PageRequest pageRequest) {
        List<User> users = new ArrayList<>(pageRequest.getPageSize());
        for (int i = 0; i < pageRequest.getPageSize(); i++) {
            User user = new User();
            user.setUsername(UUID.randomUUID().toString());
            user.setPassword(UUID.randomUUID().toString());
            user.setBirthday(LocalDateTime.now());
            user.setGender(i % 2);
            user.setCreatedBy(LocalHost.getMachineName());
            user.setCreatedOn(LocalDateTime.now());
            user.setUpdatedBy(LocalHost.getMachineName());
            user.setUpdatedOn(LocalDateTime.now());
            user.setRemark("");
            users.add(user);
        }

        return users;
    }

    private boolean userExist(User user) {
        assert StringUtils.isNotBlank(user.getUsername()) : "登录用户名为空";

        return userDao.find(user) > 0;
    }
}
