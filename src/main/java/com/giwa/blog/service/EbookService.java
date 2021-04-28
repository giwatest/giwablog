package com.giwa.blog.service;

import com.giwa.blog.domain.Ebook;
import com.giwa.blog.domain.EbookExample;
import com.giwa.blog.mapper.EbookMapper;
import com.giwa.blog.req.EbookReq;
import com.giwa.blog.resp.EbookResp;
import com.giwa.blog.util.CopyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%"+req.getName()+"%");
        List<Ebook> ebooksList = ebookMapper.selectByExample(ebookExample);
        List<EbookResp> respList1 = CopyUtil.copyList(ebooksList, EbookResp.class);
        return respList1;
    }
}
