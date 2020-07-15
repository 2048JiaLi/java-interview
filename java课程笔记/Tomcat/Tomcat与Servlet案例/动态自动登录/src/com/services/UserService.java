package com.services;

import com.entity.User;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/7/15 10:18
 */
public interface UserService {
    User checkUser(String username, String password);
}
