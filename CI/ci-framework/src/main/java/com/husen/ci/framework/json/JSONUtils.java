package com.husen.ci.framework.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/***
 @Author:MrHuang
 @Date: 2019/6/9 16:38
 @DESC: TODO 基于Fastjson的Utils
 @VERSION: 1.0
 ***/
public class JSONUtils {

    public static <T> T json2Bean(String jsonData, Class<T> clazz) {
        return JSON.parseObject(jsonData, clazz);
    }

    public static<T> List<T> json2List(String jsonData, Class<T> clazz) {
        return JSON.parseArray(jsonData, clazz);
    }

    public static Map<String, Object> json2Map(String jsonData) {
        return JSON.parseObject(jsonData, TreeMap.class);
    }

    public static JSONObject json2JSONObject(String jsonData) {
        return JSON.parseObject(jsonData);
    }

    public static JSONArray json2JSONArray(String jsondata) {
        return JSON.parseArray(jsondata);
    }

    public static String object2Json(Object object) {
        return JSON.toJSONString(object);
    }
}
