package com.yuanian.five.Translate;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author ：mmzs
 * @date ：Created in 2019/9/5 16:37
 * @description：
 * @modified By：
 * @version: $
 */
public class TransUtil {
    public static void main(String[] args) {

        String data = InputMSG("animal");
        System.out.println(data);
        Map o =TransUtil.DealXml(data);
        for (Object key : o.keySet()) {
            String value = (String)o.get(key);
            System.out.println(key + " : " + value);
        }
    }


    public static String InputMSG(String word) {
        //请求地址
        String Url = "http://www.webxml.com.cn/WebServices/TranslatorWebService.asmx/getEnCnTwoWayTranslator";
        //请求参数
        Map Field = new HashMap();
        Field.put("Word", word);
        //获取数据
        String result = "";
        BufferedReader read = null;
        try {
            URL realurl = new URL(Url + "?" + urlencode(Field));
            URLConnection connection = realurl.openConnection();
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

    public static Map DealXml(String xml) {
        Map map = new HashMap();
        Document doc = null;
        try {
            // 将字符串转为XML
            doc = DocumentHelper.parseText(xml);
            // 获取根节点
            Element rootElt = doc.getRootElement();
            // 获取根节点下的子节点string
            Iterator iter = rootElt.elementIterator("string");
            // 遍历string节点
            int i = 0;
            while (iter.hasNext()) {
                Element node = (Element) iter.next();
                if (i == 0) {
                    String word = node.getStringValue().split(":")[0];
                    map.put("Input_word", word);
                } else {
                    map.put("Translation_word", node.getStringValue());
                }
                i++;
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;

    }
}
