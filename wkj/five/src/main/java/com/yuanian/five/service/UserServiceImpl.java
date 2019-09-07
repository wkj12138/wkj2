package com.yuanian.five.service;

import com.yuanian.five.dao.UserMapper;
import com.yuanian.five.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：mmzs
 * @date ：Created in 2019/8/30 10:04
 * @description：
 * @modified By：
 * @version: $
 */
//@Service
@Component
@WebService(
        serviceName = "UserService",//对外发布服务名
        targetNamespace = "http://service.com.yuanian.five/",
        endpointInterface = "com.yuanian.five.service.UserService"
)
public class UserServiceImpl implements  UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User user){
        return userMapper.insert(user);
    }

    @Override
    public User selectByPrimaryKey(String usercode){
        return userMapper.selectByPrimaryKey(usercode);
    }


}
