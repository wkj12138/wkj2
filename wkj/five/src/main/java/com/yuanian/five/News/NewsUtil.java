package com.yuanian.five.News;

import com.yuanian.five.Weather.WeatherUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Properties;

/**
 * @author ：mmzs
 * @date ：Created in 2019/9/5 9:24
 * @description：
 * @modified By：
 * @version: $
 */
public class NewsUtil {
    public static void main(String[] args) throws AddressException, MessagingException {
        String url = "http://zhouxunwang.cn/data/";
//        StringBuilder all=new StringBuilder();
        String str = UrlGet(url, 0);
        System.out.println(str);
        String content = JsonSpilt(str,1);
        String content2 = JsonSpilt(str,2);
//        all.append(content);
        System.out.println(content);
        System.out.println(content2);
        NewsUtil.send("niubi","zhaowei@yuanian.com","zw19970613zw"
                ,"794253266@qq.com",9,2,2);
    }

    public static String UrlGet(String url, int number) {
        String key = "AOrErNM3Sdv+ipOK9IM1Q2jBPwTgsJeZ/px16A";
        String type[] = {"top", "shehui", "guonei", "guoji", "yule"
                , "tiyu", "junshi", "keji", "caijing", "shishang"};
        String parms = "id=75&key=" + key + "&type=" + type[number];
        String Content = "";
        BufferedReader read = null;
        try {
            URL realurl = new URL(url + "?" + parms);
            URLConnection connection = realurl.openConnection();
            connection.connect();
            read = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = read.readLine()) != null) {
                Content += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Content;
    }
    public static String JsonSpilt(String str,int type) {

        StringBuilder stbu = new StringBuilder();
        if(str!=null){
            JSONObject obj = JSONObject.fromObject(str);
            str = obj.getString("result");
            obj = JSONObject.fromObject(str);
            JSONArray AD=obj.getJSONArray("data");
            for (int i = 0; i < AD.size(); i++) {
                JSONObject JD = AD.getJSONObject(i);
                String title = JD.getString("title");
                String time = JD.getString("date");
                String category = JD.getString("category");
                String author_name = JD.getString("author_name");
                String url= JD.getString("url");
                String text = "";
                if(type == 1){
                    text = category+"新闻[时间:"+time+",标题:"+title+",作者:"+author_name+",访问链接:\""+url+"\"]\n";
                }else if(type == 2){

                    text = "<tr>"+"<td>"+category+"新闻"+"</td>"
                        +"<td>"+time+"</td>"
                        +"<td>"+title+"</td>"
                        +"<td>"+author_name+"</td>"
                        +"<td>"+url+"</td>"
                        +"</tr>";
                }
                stbu.append(text);
            }
        }
        return stbu.toString();
    }
    public static void send(String title,String sendU,String sendP,String requireU,int first,int number,int index )throws AddressException,MessagingException{
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
        String result=NewsUtil.JsonSpilt(UrlGet("http://zhouxunwang.cn/data/", first),number);
        if(index == 1){
            message.setText(result);
        }else if(index == 2){
            message.setContent("<table border=\\\"5\\\" style=\\\"border:solid 1px #E8F2F9;font-size=14px;;font-size:18px;>" +
                    "<tr style=\\\"background-color: #428BCA; color:#ffffff\\\">" +
                    "<th>新闻类别</th><th>时间</th><th>标题</th><th>作者</th><th>访问链接</th></tr>" +
                    result +
                    "</table>","text/html; charset=utf-8");
        }
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        transport.connect(sendU, sendP);// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
    public static void sendhtml(String title,String sendU,String sendP,String requireU,String result )throws AddressException,MessagingException{
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
        message.setContent("<table border=\\\"5\\\" style=\\\"border:solid 1px #E8F2F9;font-size=14px;;font-size:18px;>" +
                "<tr style=\\\"background-color: #428BCA; color:#ffffff\\\">" +
                "<th>新闻类别</th><th>时间</th><th>标题</th><th>作者</th><th>访问链接</th></tr>" +
                result +
                "</table>","text/html; charset=utf-8");
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        transport.connect(sendU, sendP);// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
