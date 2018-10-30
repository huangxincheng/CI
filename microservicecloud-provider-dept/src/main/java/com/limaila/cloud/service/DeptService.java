package com.limaila.cloud.service;

import com.limaila.cloud.entitys.Dept;

import java.util.List;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
public interface DeptService {

    int add(Dept dept);

    Dept get(Long id);

    List<Dept> list();
}
