package com.limaila.cloud.dao;

import com.limaila.cloud.entitys.User;
import org.apache.ibatis.annotations.*;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Mapper
public interface UserMapper {

    @Insert("insert into user(username,userage,ctime) values(#{username},#{userage},now())")
    int add(User user);

    @Update("update user set username = #{username}, userage = #{userage}")
    int update(User user);

    @Delete("delete from user where userno = #{userno}")
    int delete(Long userno);

    @Select("select * from user where userno = #{userno}")
    User get(Long userno);
}
