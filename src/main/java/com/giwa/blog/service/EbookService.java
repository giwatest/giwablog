package com.giwa.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.giwa.blog.domain.Ebook;
import com.giwa.blog.domain.EbookExample;
import com.giwa.blog.mapper.EbookMapper;
import com.giwa.blog.req.EbookReq;
import com.giwa.blog.resp.EbookResp;
import com.giwa.blog.util.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;
    private static final Logger Log = LoggerFactory.getLogger(EbookService.class);
    public List<EbookResp> list(EbookReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%"+req.getName()+"%");
        PageHelper.startPage(1,3);
        List<Ebook> ebooksList = ebookMapper.selectByExample(ebookExample);
        PageInfo<Ebook> pageInfo = new PageInfo<>(ebooksList);
        Log.info("总行数：{}",pageInfo.getTotal());
        Log.info("总页数：{}",pageInfo.getPages());
        List<EbookResp> respList1 = CopyUtil.copyList(ebooksList, EbookResp.class);
        return respList1;
    }
}
