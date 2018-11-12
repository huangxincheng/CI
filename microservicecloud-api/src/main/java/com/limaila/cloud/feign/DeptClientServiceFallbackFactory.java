package com.limaila.cloud.feign;

import com.limaila.cloud.entitys.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public Dept get(long id) {
                Dept dept = new Dept();
                dept.setDname("hystrixListErr");
                return dept;
            }

            @Override
            public List<Dept> list() {
                List<Dept> depts = new ArrayList<>();
                Dept dept = new Dept();
                dept.setDname("hystrixListErr");
                depts.add(dept);
                return depts;
            }

            @Override
            public int add(Dept dept) {
                return 0;
            }
        };
    }
}
