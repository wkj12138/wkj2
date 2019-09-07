package com.yuanian.five.Weather;

import net.sf.json.JSONObject;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @author ：mmzs
 * @date ：Created in 2019/9/4 14:44
 * @description：
 * @modified By：
 * @version: $
 */
public class WeatherUtil {
    public static void main(String[] args)   throws AddressException,MessagingException {
        String url = "http://v.juhe.cn/weather/index";
        String key = "26cc3362cf5af8df9a9d8af5e2478514";
        String parms = "cityname=武汉&key=" + key;
        String str = SendGET(url, parms);
//        System.out.println(str);
        String q=JsonSpilt(str);
        System.out.println(q);
        WeatherUtil.send("niubi","zhaowei@yuanian.com","zw19970613zw"
        ,"794253266@qq.com",q);




    }

    public static String SendGET(String url, String param) {
        String result = "";//访问返回结果
        BufferedReader read = null;//读取访问结果
        try {
            //创建url
            URL realurl = new URL(url + "?" + param);
            //打开连接
            URLConnection connection = realurl.openConnection();
            //建立连接
            connection.connect();
            read = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;
            //循环读取
            while ((line = read.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (read != null) {//关闭流
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public  static String  JsonSpilt(String str) {
        JSONObject js = JSONObject.fromObject(str);
        String o = js.getString("result");
        js = JSONObject.fromObject(o);
        o = js.getString("future");
        js = JSONObject.fromObject(o);
//        System.out.println(js);
        StringBuilder stbu = new StringBuilder();
        Iterator it = js.keys();
        for (int i = 0; i < 4; ++i) {
            String key = (String) it.next();
            JSONObject date = js.getJSONObject(key);
            if (i > 0) {
//                System.out.println(date);
                String temperature = date.getString("temperature");
                String weather = date.getString("weather");
                String wind = date.getString("wind");
                String week = date.getString("week");
                String day = date.getString("date");
                String text = "时间:" + day +","+week+ ",天气:" + weather + ",温度:" + temperature +",风向:"+wind+ "\n";
                stbu.append(text);
            }
        }
        return stbu.toString();
    }
public static void send(String title,String sendU,String sendP,String requireU,String result )throws AddressException,MessagingException{
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.yuanian.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
        // 得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress(sendU));
        // 设置收件人邮箱地址
//message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("qiugch@yuanian.com"),new InternetAddress("wangmh@yuanian.com"),new InternetAddress("wangyq@yuanian.com")});
         message.setRecipient(Message.RecipientType.TO, new InternetAddress(requireU));//一个收件人
        // 设置邮件标题
        message.setSubject(title+"  "+new Date());
        // 设置邮件内容
        message.setText(result);
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        transport.connect(sendU, sendP);// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
}

}
