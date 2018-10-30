package com.limaila.cloud.dao;

import com.limaila.cloud.entitys.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Mapper
public interface DeptMapper {

    int addDept(Dept dept);

    Dept findById(Long id);

    List<Dept> findAll();
}
