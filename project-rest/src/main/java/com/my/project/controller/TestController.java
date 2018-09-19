package com.my.project.controller;

import com.my.framework.common.utils.RedisUtils;
import com.my.project.response.TestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-07
 **/
@RestController
public class TestController {

    /**
     * @description:
     * @return: java.lang.String
     * @author: Mr.WangJie
     * @date: 2018/9/3
     */
    @GetMapping("/test")
    public TestResponse test() {
        TestResponse testResponse = new TestResponse();
        testResponse.setName("Bob");
        RedisUtils.set("hello", "Bob");
        testResponse.setName(RedisUtils.get("hello", String.class));
        return testResponse;
    }
}
