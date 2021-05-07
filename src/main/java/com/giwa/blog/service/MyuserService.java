package com.giwa.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.giwa.blog.domain.Myuser;
import com.giwa.blog.domain.MyuserExample;
import com.giwa.blog.exception.BusinessException;
import com.giwa.blog.exception.BusinessExceptionCode;
import com.giwa.blog.mapper.MyuserMapper;
import com.giwa.blog.req.MyuserQueryReq;
import com.giwa.blog.req.MyuserResetPasswordReq;
import com.giwa.blog.req.MyuserSaveReq;
import com.giwa.blog.resp.MyuserQueryResp;
import com.giwa.blog.resp.PageResp;
import com.giwa.blog.util.CopyUtil;
import com.giwa.blog.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyuserService {

    @Resource
    private MyuserMapper myuserMapper;

    @Resource
    private SnowFlake snowFlake;
    private static final Logger Log = LoggerFactory.getLogger(MyuserService.class);
    public PageResp<MyuserQueryResp> list(MyuserQueryReq req){
        MyuserExample myuserExample = new MyuserExample();
        MyuserExample.Criteria criteria = myuserExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getLoginName())) {
            criteria.andNameLike("%" + req.getLoginName() + "%");
        }

        PageHelper.startPage(req.getPage(),req.getSize());
        List<Myuser> myusersList = myuserMapper.selectByExample(myuserExample);
        PageInfo<Myuser> pageInfo = new PageInfo<>(myusersList);
        Log.info("总行数：{}",pageInfo.getTotal());
        Log.info("总页数：{}",pageInfo.getPages());
        List<MyuserQueryResp> respList1 = CopyUtil.copyList(myusersList, MyuserQueryResp.class);
        PageResp<MyuserQueryResp> myuserRespPageResp = new PageResp<>();
        myuserRespPageResp.setList(respList1);
        myuserRespPageResp.setTotal(pageInfo.getTotal());
        return myuserRespPageResp;
    }

    public void save(MyuserSaveReq myuserSaveReq){
        Myuser myuser = CopyUtil.copy(myuserSaveReq, Myuser.class);
        if(ObjectUtils.isEmpty(myuserSaveReq.getId())){
            Myuser myuser1 = selectByLoginame(myuserSaveReq.getLoginName());
            if(ObjectUtils.isEmpty(myuser1)){
                //insert
                myuser.setId(snowFlake.nextId());
                myuserMapper.insert(myuser);
            }else{
                //提示用户名重复
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }

        }else{
            //update
            myuser.setLoginName(null);
            myuserMapper.updateByPrimaryKeySelective(myuser);
        }
    }

    public void delete(Long id){
        myuserMapper.deleteByPrimaryKey(id);
    }
    public Myuser selectByLoginame(String loginName){
        MyuserExample myuserExample = new MyuserExample();
        MyuserExample.Criteria criteria = myuserExample.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
        List<Myuser> myusers = myuserMapper.selectByExample(myuserExample);
        if(CollectionUtils.isEmpty(myusers)){
            return null;
        }else {
            return myusers.get(0);
        }

    }


    public void resetPassword(MyuserResetPasswordReq myuserResetPasswordReq){
        Myuser myuser = CopyUtil.copy(myuserResetPasswordReq, Myuser.class);
        myuserMapper.updateByPrimaryKeySelective(myuser);
    }
}
