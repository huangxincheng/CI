package com.limaila.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myLoad")
public class LoadController {

    @Value("${profile}")
    private String profile;

    @RequestMapping
    public String index() {
        System.out.println("profile = " + profile);
        return profile;
    }
}
