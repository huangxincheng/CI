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

    @RequestMapping("a")
    public String a() {
        return "a";
    }

    @RequestMapping("b")
    public String b() {
        int f = 1 / 0;
        return "b";
    }

    @RequestMapping("v2")
    @ResponseBody
    public Map v2() {
        HashMap map = new HashMap();
        map.put("a1", "a1");
        map.put("vesion", 1.1);
        return map;
    }

    @RequestMapping("v3")
    @ResponseBody
    public Map v3() {
        HashMap map = new HashMap();
        map.put("a1", "a1");
        map.put("vesion", 1.1);
        int f = 1 / 0;
        return map;
    }


}
