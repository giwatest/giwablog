package com.giwa.blog.controller;

import com.giwa.blog.domain.Test;
import com.giwa.blog.service.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestContrller {

    @Value("${test.hello:TEST}")
    private String testhello;

    @Resource
    private TestService testService;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello1 " +testhello;
    }

    @RequestMapping(value = "/hellopost", method = RequestMethod.POST)
    public String hellopost(String name) {
        return "hello post" + name;
    }

    @RequestMapping(value = "/test/list", method = RequestMethod.GET)
    public List<Test> list() {
        return testService.list();
    }

}
