package com.giwa.blog.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestContrller {

    /**
     *
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }
}
