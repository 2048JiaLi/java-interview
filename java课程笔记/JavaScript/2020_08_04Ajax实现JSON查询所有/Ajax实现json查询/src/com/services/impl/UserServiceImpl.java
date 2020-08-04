package com.services.impl;

import com.alibaba.fastjson.JSON;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.entity.User;
import com.services.UserService;

import java.util.List;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/8/4 15:23
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public String getAll() {
        List<User> allUser = userDao.getAllUser();
        return JSON.toJSONString(allUser);
    }
}
