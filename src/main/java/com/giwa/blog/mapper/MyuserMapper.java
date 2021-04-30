package com.giwa.blog.mapper;

import com.giwa.blog.domain.Myuser;
import com.giwa.blog.domain.MyuserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyuserMapper {
    long countByExample(MyuserExample example);

    int deleteByExample(MyuserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Myuser record);

    int insertSelective(Myuser record);

    List<Myuser> selectByExample(MyuserExample example);

    Myuser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Myuser record, @Param("example") MyuserExample example);

    int updateByExample(@Param("record") Myuser record, @Param("example") MyuserExample example);

    int updateByPrimaryKeySelective(Myuser record);

    int updateByPrimaryKey(Myuser record);
}