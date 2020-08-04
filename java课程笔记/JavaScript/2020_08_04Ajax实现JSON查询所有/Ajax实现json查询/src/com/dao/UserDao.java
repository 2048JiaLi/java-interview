package com.dao;

import com.entity.User;

import java.util.List;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/8/4 15:19
 */
public interface UserDao {
    List<User> getAllUser();
}
