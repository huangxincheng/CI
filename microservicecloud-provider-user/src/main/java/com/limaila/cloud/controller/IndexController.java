package com.limaila.cloud.controller;

import com.limaila.cloud.anno.GlobalResponseBodyIgnore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/index2")
public class IndexController {

    @ResponseBody
    @RequestMapping
    @GlobalResponseBodyIgnore
    public String index() {
        return "aaa";
    }

    @RequestMapping("aaa")
    public String a() {
        return "aa";
    }

    @RequestMapping("v2")
    @ResponseBody
    public Map v2() {
        HashMap map = new HashMap();
        map.put("a1", "a1");
        map.put("vesion", 1.1);
        return map;
    }
}
