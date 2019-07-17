package com.api.common;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wanghuiwen on 17-2-23.
 */
public class UtilFun {
    static Logger logger = LoggerFactory.getLogger("util");

    public  static  final  String YYYYMMDDHHMMSS="yyyy-MM-dd HH:mm:ss";
    public  static  final  String YYYYMMDDHHMMSS3="yyyyMMddHHmmss";
    public  static  final  String YYYYMMDD2="yyyy/MM/dd";
    public  static  final  String YMD="yyyy-MM-dd";
    /**
     * 判断list不为
     *
     * @param list list
     * @return 为空返回true
     */
    public static boolean isEmptyList(List list) {
        return list == null || list.size() <= 0;
    }

    /**
     * 判断str不为
     *
     * @param str str
     * @return 为空返回true
     */
    public static boolean isEmptyString(String str) {
        return str == null || str.equals("");
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (!checkIP(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!checkIP(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!checkIP(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    private static boolean checkIP(String ip) {
        return ip != null && ip.length() != 0 && !"unkown".equalsIgnoreCase(ip)
                && ip.split(".").length == 4;
    }
    public static void prinrObject(Object o){
        System.out.println(JSON.toJSONString(o));
    }


    public  static  String DateToString(Date date,String fromat){
        SimpleDateFormat sdf = new SimpleDateFormat(fromat);
        return sdf.format(date);
    }

    public  static Date StringToDate(String datestr,String formatstr){
        DateFormat format= new SimpleDateFormat(formatstr);
        try {
            Date date =format.parse(datestr);
            return date;
        } catch (ParseException e) {
           logger.error("日期转换错误",e);
            return null;
        }

    }

    public static  Date addDay(String date,int day){
        return addDay(StringToDate(date,YYYYMMDDHHMMSS),day);
    }

    public static  Date addDay(String date,int day,String fromat){
        return addDay(StringToDate(date,fromat),day);
    }


    public static Date addDay(Date date,int day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,day);
        return calendar.getTime();
    }

    /**
     * 首字母大
     * @param str start
     * @return str
     */
     public static String upperFristCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 返回时间戳
     */
    public static Integer getIntTime(Date date){

        return Math.toIntExact(date.getTime() / 1000);
    }

}
