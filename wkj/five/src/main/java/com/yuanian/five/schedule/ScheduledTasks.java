package com.yuanian.five.schedule;

import com.yuanian.five.jsondate.JsonDeal;
import com.yuanian.five.model.User;
import com.yuanian.five.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.yuanian.five.jsondate.JsonDeal.StrData;

/**
 * @author ：mmzs
 * @date ：Created in 2019/8/30 11:49
 * @description：
 * @modified By：
 * @version: $
 */
@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {
    @Autowired
    private UserService userService;

//    //每1分钟执行一次
//     @Scheduled(cron = "0 */1 *  * * * ")
    //每天凌晨1点执行一次
    @Scheduled(cron = "0 0 1 * * ?")
    public void reportCurrentByCron() throws ParseException {
        JsonDeal jsonDeal = new JsonDeal();
        List<User> list = new ArrayList<>();
        SimpleDateFormat datedeal = new SimpleDateFormat("yyyy-MM-dd");
        String o = jsonDeal.StrData();
        System.out.println(o);
        JSONObject obj = JSONObject.fromObject(o);
        String b = obj.getString("result");
        obj = JSONObject.fromObject(b);
        b = obj.getString("data");
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
            if(userService.selectByPrimaryKey(user.getUsercode())==null){
                userService.insert(user);
                System.out.println("此数据插入成功");
            }else{System.out.println("此数据已存在，插入失败");}
        }

    }
}
