package me.rainstorm.boot.service;

import me.rainstorm.boot.domain.entity.User;
import me.rainstorm.boot.domain.response.Response;

/**
 * @author chen
 */
public interface UserService {
    Response signUp(User user);

    Response exist(User user);

    Response select(User user);
}
