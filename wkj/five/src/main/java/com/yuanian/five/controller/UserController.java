package com.yuanian.five.controller;

import com.yuanian.five.jsondate.JsonReturn;
import com.yuanian.five.model.User;
import com.yuanian.five.service.UserService;
import com.yuanian.five.service.UserServiceImpl;
import jdk.nashorn.internal.runtime.logging.Logger;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * @author ：mmzs
 * @date ：Created in 2019/8/30 9:32
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/test")
    public String test() {
        return "test success";
    }
    //实现有Json数据的接口
    @GetMapping("/get")
    public JSONObject ShowJson() throws Exception{
        JsonReturn jsonReturn = new JsonReturn();
        JSONObject o = jsonReturn.JsonGet();
        return o;
    }
    //通过usercode查询
    @PostMapping("/select/{usercode}")
    public User selectByPrimaryKey(@PathVariable("usercode") String usercode){
        User o = userService.selectByPrimaryKey(usercode);
        if(o ==null){
            System.out.println("查询失败，无此人消息");
        }else{
            System.out.println("查询成功");
        }
        return o;
    }
    //插入
    @GetMapping("/insert")
    public  int insert(User user) throws ParseException {
        return userService.insert(user);
    }


}
