package com.giwa.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.giwa.blog.domain.Content;
import com.giwa.blog.domain.Doc;
import com.giwa.blog.domain.DocExample;
import com.giwa.blog.mapper.ContentMapper;
import com.giwa.blog.mapper.DocMapper;
import com.giwa.blog.req.DocQueryReq;
import com.giwa.blog.req.DocSaveReq;
import com.giwa.blog.resp.DocQueryResp;
import com.giwa.blog.resp.PageResp;
import com.giwa.blog.util.CopyUtil;
import com.giwa.blog.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DocService {

    @Resource
    private DocMapper docMapper;
    @Resource
    private ContentMapper contentMapper;

    @Resource
    private SnowFlake snowFlake;
    private static final Logger Log = LoggerFactory.getLogger(DocService.class);
    public List<DocQueryResp> all(DocQueryReq req){
        DocExample docExample = new DocExample();
        docExample.setOrderByClause("sort asc");
        DocExample.Criteria criteria = docExample.createCriteria();
        List<Doc> docsList = docMapper.selectByExample(docExample);
        List<DocQueryResp> respList1 = CopyUtil.copyList(docsList, DocQueryResp.class);
        return respList1;
    }

    public PageResp<DocQueryResp> list(DocQueryReq req){
        DocExample docExample = new DocExample();
        docExample.setOrderByClause("sort asc");
        DocExample.Criteria criteria = docExample.createCriteria();
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Doc> docsList = docMapper.selectByExample(docExample);
        PageInfo<Doc> pageInfo = new PageInfo<>(docsList);
        Log.info("总行数：{}",pageInfo.getTotal());
        Log.info("总页数：{}",pageInfo.getPages());
        List<DocQueryResp> respList1 = CopyUtil.copyList(docsList, DocQueryResp.class);
        PageResp<DocQueryResp> docRespPageResp = new PageResp<>();
        docRespPageResp.setList(respList1);
        docRespPageResp.setTotal(pageInfo.getTotal());
        return docRespPageResp;
    }

    public void save(DocSaveReq docSaveReq){
        Doc doc = CopyUtil.copy(docSaveReq, Doc.class);
        Content content = CopyUtil.copy(docSaveReq, Content.class);
        if(ObjectUtils.isEmpty(docSaveReq.getId())){
            //insert
            doc.setId(snowFlake.nextId());
            docMapper.insert(doc);
            content.setId(doc.getId());
            contentMapper.insert(content);

        }else{
            //update
            docMapper.updateByPrimaryKey(doc);
            contentMapper.updateByExampleWithBLOBs(content);
        }
    }

    public void delete(Long id){
        docMapper.deleteByPrimaryKey(id);
    }
    public String findContent(Long id){
        Content content = contentMapper.selectByPrimaryKey(id);
        return content.getContent();
    }

    public void delete(List<String> ids){
        DocExample docExample = new DocExample();
        docExample.setOrderByClause("sort asc");
        DocExample.Criteria criteria = docExample.createCriteria();
        criteria.andIdIn(ids);
        docMapper.deleteByExample(docExample);
    }
}
