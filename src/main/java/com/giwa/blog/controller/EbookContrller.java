package com.giwa.blog.controller;

import com.giwa.blog.domain.Ebook;
import com.giwa.blog.resp.CommonResp;
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
    public CommonResp list() {
        CommonResp<List<Ebook>> resp = new CommonResp<>();
        List<Ebook> list = ebookService.list();
        resp.setContent(list);
        return resp;
    }
}
