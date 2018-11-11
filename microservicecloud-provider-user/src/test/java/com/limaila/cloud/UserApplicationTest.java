package com.limaila.cloud;

import com.limaila.cloud.aysnc.AsyncTask;
import com.limaila.cloud.entitys.User;
import com.limaila.cloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
    private AsyncTask asyncTask;

    @Test
    public void contextLoads() throws ExecutionException, InterruptedException {
        System.out.println("----------------OK");
        System.out.println("111");
        Future<String> task1 = asyncTask.testAsync();
        while(true) {
            if (task1.isDone()) {
                log.info("task1 result:{}", task1.get());
                break;
            }
        }
        System.out.println("----------------BB");
    }
}
