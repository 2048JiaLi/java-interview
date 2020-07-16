package com.dao.impl;

import com.dao.DeptDao;
import com.entity.Dept;
import com.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/7/16 9:17
 */
public class DeptDaoImpl implements DeptDao {
    private QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public List<Dept> getAllDept() {
        try {
            return queryRunner.query("select * from dept;", new BeanListHandler<Dept>(Dept.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int delete(int no) {
        try {
            return queryRunner.update("delete from dept where deptno=?;",no);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
