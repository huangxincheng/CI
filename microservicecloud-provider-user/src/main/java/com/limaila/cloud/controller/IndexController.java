package com.limaila.cloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index2")
public class IndexController {

    @RequestMapping
    public String index() {
        return "aaa";
    }
}
