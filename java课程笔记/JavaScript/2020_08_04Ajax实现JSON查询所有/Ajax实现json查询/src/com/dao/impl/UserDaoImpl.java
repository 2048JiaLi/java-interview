package com.dao.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.dao.UserDao;
import com.entity.User;
import com.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/8/4 15:19
 */
public class UserDaoImpl implements UserDao {
    private QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
    @Override
    public List<User> getAllUser() {
        try {
            return queryRunner.query("select * from users;",new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
