package com.yuanian.five.jsondate;


import com.yuanian.five.model.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：mmzs
 * @date ：Created in 2019/8/29 23:33
 * @description：
 * @modified By：
 * @version: $
 */
public class JsonReturn {

    public static JSONObject JsonGet() throws Exception{
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("reason","成功的返回");
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateformat.parse("2007-1-18");
        Date date1 = dateformat.parse("2019-02-05");
        Date date2 = dateformat.parse("2019-06-16");

        User user= new User();
        user.setUsercode("10010");
        user.setUsername("王五");
        user.setDepartment("武汉技术开发部");
        user.setHiredate(date);
        JSONObject js = new JSONObject();
        js.put("usercode",user.getUsercode());
        js.put("username",user.getUsername());
        js.put("department",user.getDepartment());
        js.put("hiredate",getStringDateShort(user.getHiredate()));

        User user1= new User();
        user1.setUsercode("10011");
        user1.setUsername("赵六");
        user1.setDepartment("华北预算事业部");
        user1.setHiredate(date1);
        JSONObject js1 = new JSONObject();
        js1.put("usercode",user1.getUsercode());
        js1.put("username",user1.getUsername());
        js1.put("department",user1.getDepartment());
        js1.put("hiredate",getStringDateShort(user1.getHiredate()));
        User user2= new User();
        user2.setUsercode("10013");
        user2.setUsername("刘八");
        user2.setDepartment("北京事业部");
        user2.setHiredate(date2);
        JSONObject js2 = new JSONObject();
        js2.put("usercode",user2.getUsercode());
        js2.put("username",user2.getUsername());
        js2.put("department",user2.getDepartment());
        js2.put("hiredate",getStringDateShort(user2.getHiredate()));
        JSONArray ja = new JSONArray();
        ja.add(js);
        ja.add(js1);
        ja.add(js2);
        JSONObject jb3 = new JSONObject();
        jb3.put("stat","1");
        jb3.put("data",ja);

        result.put("result",jb3);
        JSONObject jsonObject = JSONObject.fromObject(result);
        return jsonObject;
    }
        public static String getStringDateShort(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
        }

    public static void main(String[] args)  throws Exception{
        JSONObject o = JsonGet();
        System.out.println(o.toString());
    }
}
