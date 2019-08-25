package me.rainstorm.boot.dao.boot;

import me.rainstorm.boot.domain.entity.User;

import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int find(User user);

    User selectOne(User query);

    User selectByUsername(String username);

    List<User> selectAll();

}