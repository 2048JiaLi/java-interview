package com.dao;

import com.entity.Dept;

import java.util.List;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/7/16 9:17
 */
public interface DeptDao {

    List<Dept> getAllDept();

    int delete(int no);
}
