package com.yuanian.five.jsondate;

import com.yuanian.five.model.User;
import com.yuanian.five.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author ：mmzs
 * @date ：Created in 2019/8/30 15:35
 * @description：
 * @modified By：
 * @version: $
 */
public class JsonDeal {
    @Autowired
    private UserService userService;

    public static void main(String[] args) throws ParseException {
        String o = StrData();
        List<User> list = new ArrayList<>();
        SimpleDateFormat datedeal = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(o);
        JSONObject obj = JSONObject.fromObject(o);
        String b = obj.getString("result");
        obj = JSONObject.fromObject(b);
        b = obj.getString("data");
//        System.out.println(b);
        JSONArray msg = JSONArray.fromObject(b);
        for (int i = 0; i < msg.size(); i++) {
            JSONObject json = msg.getJSONObject(i);
            System.out.println(json);
            User user = new User();
            Date date = datedeal.parse(json.getString("hiredate"));
            user.setUsercode(json.getString("usercode"));
            user.setUsername(json.getString("username"));
            user.setDepartment(json.getString("department"));
            user.setHiredate(new java.sql.Date(date.getTime()));
            list.add(user);
//            System.out.println(user.getUsercode());
        }
//        System.out.println(list);
//        for(int i = 0;i < list.size(); i ++){
//            System.out.println(list.get(i).getHiredate());
//        }


    }
    public static String StrData(){

        String url = "http://192.168.51.84:8099/get";
        System.out.println("原始：" + url);
        System.out.println("MD5后：" + stringMD5(url));
        System.out.println("加密的：" + convertMD5(url));
        System.out.println("解密的：" + convertMD5(convertMD5(url)));
        Map Field = new HashMap();


        String result = HttpDeal(convertMD5(url),Field);

        return result;
    }

    public static String HttpDeal(String url,Map Field){
        String result = "";
        BufferedReader read = null;
        try {
            URL Url = new URL(convertMD5(url) + "?" + urlencode(Field));
            URLConnection connection = Url.openConnection();
            connection.connect();
            read = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = read.readLine()) != null) {
                result += line;
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
        return result;
    }
    public static String urlencode(Map<String, Object> data) {
        //将map里的参数变成像 showapi_appid=###&showapi_sign=###&的样子
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    public static String stringMD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16){
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr) {

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }

}
