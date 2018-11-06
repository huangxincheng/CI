package com.limaila.cloud.entitys;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class User implements Serializable {

    private Long userno;

    private String username;

    private Integer userage;

    private Date ctime;
}
