package com.dao.impl;

import com.dao.UserDao;
import com.entity.User;
import com.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/7/15 9:59
 */
public class UserDaoImpl implements UserDao {
    QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public User checkUser(User user) {
        try {
            return queryRunner.query("select * from users where username=? and password=?;",
                    new BeanHandler<User>(User.class),
                    user.getUsername(),user.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
