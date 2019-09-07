package com.yuanian.five.controller;

import com.yuanian.five.Translate.TransUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：mmzs
 * @date ：Created in 2019/9/5 16:43
 * @description：
 * @modified By：
 * @version: $
 */
@CrossOrigin
@RestController
public class TransController {
    @GetMapping("/trans/{word}")
    public Map<String,Object> trans(@PathVariable("word") String word){
        TransUtil transutil = new  TransUtil();
        Map shuju = new HashMap();
        String even=transutil.InputMSG(word);
        shuju =TransUtil.DealXml(even);
        return shuju;
    }
}
