package com.services;

import com.entity.Dept;

import java.util.List;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/7/16 9:22
 */
public interface DeptService {
    List<Dept> getAllDept();
    int delete(int no);
}
