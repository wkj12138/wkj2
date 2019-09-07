package com.yuanian.five.controller;

import com.yuanian.five.Weather.WeatherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * @author ：mmzs
 * @date ：Created in 2019/9/4 15:03
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@CrossOrigin
public class WeaController {

    @GetMapping("/weather")
    public String Weather(){
        WeatherUtil weatherUtil = new WeatherUtil();
        String url = "http://v.juhe.cn/weather/index";
        String key = "26cc3362cf5af8df9a9d8af5e2478514";
        String parms = "cityname=武汉&key=" + key;
        String str = weatherUtil.SendGET(url, parms);
        String q=weatherUtil.JsonSpilt(str);
        return q;
    }
    @GetMapping("/SendMail/{title}/{sendU}/{sendP}/{requireU}/{result}")
    public void sendmail(
            @PathVariable("title") String title,
            @PathVariable("sendU") String sendU,
            @PathVariable("sendP") String sendP,
            @PathVariable("requireU") String requireU,
            @PathVariable("result") String result
    )throws AddressException,MessagingException {
        WeatherUtil.send(title,sendU,sendP,requireU,result);
    }


}
