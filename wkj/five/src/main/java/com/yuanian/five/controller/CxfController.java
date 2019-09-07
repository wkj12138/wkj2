package com.yuanian.five.controller;

import com.yuanian.five.model.User;
import com.yuanian.five.service.UserService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：mmzs
 * @date ：Created in 2019/9/6 9:27
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@CrossOrigin
public class CxfController {
    @GetMapping("/search/{usercode}")
    public User selectByPrimaryKey(@PathVariable("usercode") String usercode){
        // 接口地址
        String address = "http://127.0.0.1:8099/services/user?wsdl";
        // 代理工厂
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        // 设置代理地址
        jaxWsProxyFactoryBean.setAddress(address);
        // 设置接口类型
        jaxWsProxyFactoryBean.setServiceClass(UserService.class);
        // 创建一个代理接口实现
        UserService userService = (UserService) jaxWsProxyFactoryBean.create();

        User o = userService.selectByPrimaryKey(usercode);
        System.out.println(o.toString());
        if(o ==null){
            System.out.println("查询失败，无此人消息");
        }else{
            System.out.println("查询成功");
        }
        return o;
    }
}
