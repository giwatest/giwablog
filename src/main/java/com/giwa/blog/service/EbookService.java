package com.giwa.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.giwa.blog.domain.Ebook;
import com.giwa.blog.domain.EbookExample;
import com.giwa.blog.mapper.EbookMapper;
import com.giwa.blog.req.EbookQueryReq;
import com.giwa.blog.req.EbookSaveReq;
import com.giwa.blog.resp.EbookQueryResp;
import com.giwa.blog.resp.PageResp;
import com.giwa.blog.util.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;
    private static final Logger Log = LoggerFactory.getLogger(EbookService.class);
    public PageResp<EbookQueryResp> list(EbookQueryReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Ebook> ebooksList = ebookMapper.selectByExample(ebookExample);
        PageInfo<Ebook> pageInfo = new PageInfo<>(ebooksList);
        Log.info("总行数：{}",pageInfo.getTotal());
        Log.info("总页数：{}",pageInfo.getPages());
        List<EbookQueryResp> respList1 = CopyUtil.copyList(ebooksList, EbookQueryResp.class);
        PageResp<EbookQueryResp> ebookRespPageResp = new PageResp<>();
        ebookRespPageResp.setList(respList1);
        ebookRespPageResp.setTotal(pageInfo.getTotal());
        return ebookRespPageResp;
    }

    public void save(EbookSaveReq ebookSaveReq){
        Ebook ebook = CopyUtil.copy(ebookSaveReq, Ebook.class);
        if(ObjectUtils.isEmpty(ebookSaveReq.getId())){
            //insert
            ebookMapper.insert(ebook);
        }else{
            //update
            ebookMapper.updateByPrimaryKey(ebook);
        }
    }
}
