package com.giwa.blog.controller;

import com.giwa.blog.req.EbookQueryReq;
import com.giwa.blog.req.EbookSaveReq;
import com.giwa.blog.resp.CommonResp;
import com.giwa.blog.resp.EbookQueryResp;
import com.giwa.blog.resp.PageResp;
import com.giwa.blog.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookService ebookService;
    @GetMapping(value = "/list")
    public CommonResp list(EbookQueryReq req) {
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }
    @PostMapping(value = "/save")
    public CommonResp save(@RequestBody EbookSaveReq req) {
        CommonResp resp = new CommonResp<>();
        ebookService.save(req);
        return resp;
    }
}
