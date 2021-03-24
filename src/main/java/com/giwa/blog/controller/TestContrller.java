package com.giwa.blog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestContrller {

    @Value("${test.hello:TEST}")
    private String testhello;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello " +testhello;
    }

    @RequestMapping(value = "/hellopost", method = RequestMethod.POST)
    public String hellopost(String name) {
        return "hello post" + name;
    }
}
