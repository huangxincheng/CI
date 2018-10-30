package com.limaila.cloud.service.impl;

import com.limaila.cloud.dao.DeptMapper;
import com.limaila.cloud.entitys.Dept;
import com.limaila.cloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public int add(Dept dept) {
        return deptMapper.addDept(dept);
    }

    @Override
    public Dept get(Long id) {
        return deptMapper.findById(id);
    }

    @Override
    public List<Dept> list() {
        return deptMapper.findAll();
    }
}
