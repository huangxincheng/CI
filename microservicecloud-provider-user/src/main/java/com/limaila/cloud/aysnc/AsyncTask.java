package com.limaila.cloud.aysnc;

import com.alibaba.fastjson.JSON;
import com.limaila.cloud.entitys.User;
import com.limaila.cloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.Future;

@Component
@Slf4j
public class AsyncTask {

    @Autowired
    private UserService userService;

    @Async("myAsync")
    public Future<String> testAsync() {
        User user = new User();
        user.setUserage(199);
        user.setUsername("199");
        user.setCtime(new Date());
        userService.add(user);
        log.info("testAsync -------------- " + JSON.toJSONString(user));
        return new AsyncResult<>("testAsync");
    }

}
