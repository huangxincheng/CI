package com.limaila.cloud.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.limaila.cloud.entitys.Dept;
import com.limaila.cloud.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@RestController
@Slf4j
public class DeptController {

    @Autowired
    private DeptService service;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public int add(@RequestBody Dept dept)
    {
        return service.add(dept);
    }

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id)
    {
        return service.get(id);
    }

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list() throws InterruptedException {
        Thread.sleep(3000);
        return service.list();
    }

    @RequestMapping("/dept/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        log.info("-----------------------------" + JSONUtils.toJSONString(services));
        List<ServiceInstance> srvList = discoveryClient.getInstances("MICROSERVICECLOUD-DEPT");
        for (ServiceInstance ele : srvList) {
            log.info(ele.getServiceId() + "\t" + ele.getHost() + "\t" + ele.getPort() + "\t" + ele.getUri());
        }
        return this.discoveryClient;
    }


}
