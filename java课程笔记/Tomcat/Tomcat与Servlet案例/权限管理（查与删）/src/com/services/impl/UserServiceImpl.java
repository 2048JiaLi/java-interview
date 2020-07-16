package com.services.impl;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.entity.User;
import com.services.UserService;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/7/15 10:19
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User checkUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userDao.checkUser(user);
    }
}
