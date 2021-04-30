package com.giwa.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.giwa.blog.domain.Category;
import com.giwa.blog.domain.CategoryExample;
import com.giwa.blog.mapper.CategoryMapper;
import com.giwa.blog.req.CategoryQueryReq;
import com.giwa.blog.req.CategorySaveReq;
import com.giwa.blog.resp.CategoryQueryResp;
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
public class CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private SnowFlake snowFlake;
    private static final Logger Log = LoggerFactory.getLogger(CategoryService.class);
    public List<CategoryQueryResp> all(CategoryQueryReq req){
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        List<Category> categorysList = categoryMapper.selectByExample(categoryExample);
        List<CategoryQueryResp> respList1 = CopyUtil.copyList(categorysList, CategoryQueryResp.class);
        return respList1;
    }

    public PageResp<CategoryQueryResp> list(CategoryQueryReq req){
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Category> categorysList = categoryMapper.selectByExample(categoryExample);
        PageInfo<Category> pageInfo = new PageInfo<>(categorysList);
        Log.info("总行数：{}",pageInfo.getTotal());
        Log.info("总页数：{}",pageInfo.getPages());
        List<CategoryQueryResp> respList1 = CopyUtil.copyList(categorysList, CategoryQueryResp.class);
        PageResp<CategoryQueryResp> categoryRespPageResp = new PageResp<>();
        categoryRespPageResp.setList(respList1);
        categoryRespPageResp.setTotal(pageInfo.getTotal());
        return categoryRespPageResp;
    }

    public void save(CategorySaveReq categorySaveReq){
        Category category = CopyUtil.copy(categorySaveReq, Category.class);
        if(ObjectUtils.isEmpty(categorySaveReq.getId())){
            //insert
            category.setId(snowFlake.nextId());
            categoryMapper.insert(category);
        }else{
            //update
            categoryMapper.updateByPrimaryKey(category);
        }
    }

    public void delete(Long id){
        categoryMapper.deleteByPrimaryKey(id);
    }
}
