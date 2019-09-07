package com.yuanian.five.controller;

import com.yuanian.five.News.NewsUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * @author ：mmzs
 * @date ：Created in 2019/9/5 9:54
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@CrossOrigin
public class NewsController {

    @GetMapping("/News/{index}/{type}")
    public String NewsText(
            @PathVariable ("index") int index,
            @PathVariable("type") int type
    ){
        NewsUtil newsUtil = new NewsUtil();
        String url = "http://zhouxunwang.cn/data/";
        String str = newsUtil.UrlGet(url, index);
        String content =  newsUtil.JsonSpilt(str,type);
        System.out.println(content);
        return content;
    }

    @GetMapping("/SendNMail/{title}/{sendU}/{sendP}/{requireU}/{nid}/{gid}/{sid}")
    public void sendmail(
            @PathVariable("title") String title,
            @PathVariable("sendU") String sendU,
            @PathVariable("sendP") String sendP,
            @PathVariable("requireU") String requireU,
            @PathVariable("nid") int nid,
            @PathVariable("gid") int gid,
            @PathVariable("sid") int sid
    )throws AddressException,MessagingException {
       NewsUtil.send(title,sendU,sendP,requireU,nid,gid,sid);
    }
}
