package com.hxc.demo;

import com.hxc.demo.service.BigoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private BigoService bigoService;

    @Test
    public void contextLoads() {
        bigoService.h1();
        bigoService.h2();
    }

}
