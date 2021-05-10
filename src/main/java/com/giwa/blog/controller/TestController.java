package com.giwa.blog.controller;

import com.giwa.blog.domain.Test;
import com.giwa.blog.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @Value("${test.hello:TEST}")
    private String testhello;

    @Resource
    private TestService testService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
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

    @RequestMapping("/redis/set/{key}/{value}")
    public String set(@PathVariable Long key, @PathVariable String value) {
        stringRedisTemplate.opsForValue().set(key.toString(), value, 3600, TimeUnit.SECONDS);
        LOG.info("key: {}, value: {}", key, value);
        return "success";
    }

    @RequestMapping("/redis/get/{key}")
    public Object get(@PathVariable Long key) {
        Object object = stringRedisTemplate.opsForValue().get(key.toString());
        LOG.info("key:{}, value:{}", key.toString(), object);
        return object;
    }
}
