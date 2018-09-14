package com.my.project.controller;

import com.my.framework.utils.RedisUtils;
import com.my.project.dao.TestMapper;
import com.my.project.response.TestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-07
 **/
@RestController
public class TestController {

    @Autowired
    private TestMapper testMapper;


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
        testResponse.setPassword(testMapper.getPassword("test"));
        testResponse.setName(RedisUtils.get("hello", String.class));
        return testResponse;
    }
}
