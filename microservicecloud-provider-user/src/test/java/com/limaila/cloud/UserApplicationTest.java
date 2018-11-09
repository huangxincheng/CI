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

        User user = userService.get(1L);

        int delete = userService.delete(1L);

        User user2 = userService.get(1L);

        User user3 = userService.get(1L);
    }


}
