package com.giwa.blog.controller;

import com.giwa.blog.domain.Demo;
import com.giwa.blog.service.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DemoContrller {

    @Resource
    private DemoService demoService;
    

    @RequestMapping(value = "/demo/list", method = RequestMethod.GET)
    public List<Demo> list() {
        return demoService.list();
    }

}
