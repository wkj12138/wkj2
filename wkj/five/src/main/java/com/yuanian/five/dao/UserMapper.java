package com.yuanian.five.dao;

import com.yuanian.five.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String usercode);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String usercode);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


}