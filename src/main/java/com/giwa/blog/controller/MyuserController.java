package com.giwa.blog.controller;

import com.giwa.blog.req.MyuserQueryReq;
import com.giwa.blog.req.MyuserSaveReq;
import com.giwa.blog.resp.CommonResp;
import com.giwa.blog.resp.MyuserQueryResp;
import com.giwa.blog.resp.PageResp;
import com.giwa.blog.service.MyuserService;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/myuser")
public class MyuserController {
    @Resource
    private MyuserService myuserService;
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
}
