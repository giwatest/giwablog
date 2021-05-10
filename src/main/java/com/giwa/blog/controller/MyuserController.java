package com.giwa.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.giwa.blog.req.MyuserLoginReq;
import com.giwa.blog.req.MyuserQueryReq;
import com.giwa.blog.req.MyuserResetPasswordReq;
import com.giwa.blog.req.MyuserSaveReq;
import com.giwa.blog.resp.CommonResp;
import com.giwa.blog.resp.MyuserLoginResp;
import com.giwa.blog.resp.MyuserQueryResp;
import com.giwa.blog.resp.PageResp;
import com.giwa.blog.service.MyuserService;
import com.giwa.blog.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/myuser")
public class MyuserController {

    private static final Logger LOG = LoggerFactory.getLogger(MyuserController.class);

    @Resource
    private MyuserService myuserService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //@Resource
    //private RedisTemplate redisTemplate;

    @Resource
    private SnowFlake snowFlake;

    @GetMapping(value = "/list")
    public CommonResp list(@Valid MyuserQueryReq req) {
        CommonResp<PageResp<MyuserQueryResp>> resp = new CommonResp<>();
        PageResp<MyuserQueryResp> list = myuserService.list(req);
        resp.setContent(list);
        return resp;
    }
    @PostMapping(value = "/save")
    public CommonResp save(@Valid @RequestBody MyuserSaveReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        myuserService.save(req);
        return resp;
    }

    @DeleteMapping(value = "/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        myuserService.delete(id);
        return resp;
    }
    @PostMapping(value = "/reset-password")
    public CommonResp resetPassword(@Valid @RequestBody MyuserResetPasswordReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        myuserService.resetPassword(req);
        return resp;
    }
    @PostMapping(value = "/login")
    public CommonResp login(@Valid @RequestBody MyuserLoginReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        MyuserLoginResp userLoginResp = myuserService.login(req);

        Long token = snowFlake.nextId();
        LOG.info("生成单点登录token {}，并放入redis中", token);
        userLoginResp.setToken(token.toString());
        stringRedisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(userLoginResp), 3600*24, TimeUnit.SECONDS);
        Object object = stringRedisTemplate.opsForValue().get(token.toString());
        LOG.info("key:{}, value:{}", token.toString(), object);
        resp.setContent(userLoginResp);
        return resp;
    }

    @GetMapping(value = "/logout/{token}")
    public CommonResp logout(@PathVariable String token) {
        CommonResp resp = new CommonResp<>();
        stringRedisTemplate.delete(token);
        LOG.info("从redis中删除token：{}", token);
        return resp;
    }
}
