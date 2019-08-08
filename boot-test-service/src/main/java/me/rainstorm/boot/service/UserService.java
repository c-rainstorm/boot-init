package me.rainstorm.boot.service;

import me.rainstorm.boot.domain.entity.User;
import me.rainstorm.boot.domain.request.PageRequest;

import java.util.List;

/**
 * @author chen
 */
public interface UserService {
    Boolean signUp(User user) throws Exception;

    Boolean exist(User user);

    User select(User user);

    List<User> select(PageRequest pageRequest);
}
