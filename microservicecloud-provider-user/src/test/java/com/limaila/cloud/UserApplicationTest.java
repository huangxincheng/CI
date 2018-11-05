package com.limaila.cloud;

import com.limaila.cloud.entitys.User;
import com.limaila.cloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserApplicationTest {

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setUserage(100);
        user.setUsername("hxc100");
        Date date = new Date();
        user.setCtime(date);
        userService.add(user);
        log.info("{}", user.toString());
    }


}
