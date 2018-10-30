package com.limaila.cloud.entitys;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Dept implements Serializable {

    // 主键
    private Long 	deptno;
    // 部门名称
    private String 	dname;

    public Dept(String dname) {
        this.dname = dname;
    }
}
