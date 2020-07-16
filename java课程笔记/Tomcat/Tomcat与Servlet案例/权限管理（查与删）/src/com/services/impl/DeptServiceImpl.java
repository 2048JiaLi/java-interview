package com.services.impl;

import com.dao.DeptDao;
import com.dao.impl.DeptDaoImpl;
import com.entity.Dept;
import com.services.DeptService;

import java.util.List;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/7/16 9:22
 */
public class DeptServiceImpl implements DeptService {
    private DeptDao deptDao = new DeptDaoImpl();

    @Override
    public List<Dept> getAllDept() {
        return deptDao.getAllDept();
    }

    @Override
    public int delete(int no) {
        return deptDao.delete(no);
    }
}
