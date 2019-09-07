package com.yuanian.five.service;

import com.yuanian.five.model.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;
@WebService(targetNamespace = "http://service.com.yuanian.five/")
public interface UserService {
    @WebMethod
    int insert(User user);
    @WebMethod
    User selectByPrimaryKey(String usercode);


}
