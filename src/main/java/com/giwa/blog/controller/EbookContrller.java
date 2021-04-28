package com.giwa.blog.controller;

import com.giwa.blog.req.EbookReq;
import com.giwa.blog.resp.CommonResp;
import com.giwa.blog.resp.EbookResp;
import com.giwa.blog.service.EbookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookContrller {
    @Resource
    private EbookService ebookService;
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResp list(EbookReq req) {
        CommonResp<List<EbookResp>> resp = new CommonResp<>();
        List<EbookResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }
}
