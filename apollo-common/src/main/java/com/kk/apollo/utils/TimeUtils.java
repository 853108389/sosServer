package com.kk.apollo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/15.
 */
public class TimeUtils {
    /**
     * date =>String
     * @param format 转化的是当前时间
     * @return
     */
    public static String timeFormat(String format) {
        SimpleDateFormat sdf = null;
        try {
            sdf = new SimpleDateFormat(format);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * string=>date
     *
     * @param format
     * @param dateStr
     * @return
     */
    public static Date timeParse2date(String format, String dateStr) {
        SimpleDateFormat sdf = null;
        try {
            sdf = new SimpleDateFormat(format);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * string=>string
     *
     * @param format
     * @param dateStr
     * @return
     */
    public static String timeParse2str(String format, String dateStr) {
        SimpleDateFormat sdf = null;
        try {
            sdf = new SimpleDateFormat(format);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        try {
            return sdf.format(sdf.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * string=>string
     * 几天前 几分钟前 之类的
     * 如评论 消息
     *
     * @return
     */
    public static String timeParse2str2(String commentTime, String formater) {
        String message = commentTime;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat(formater);
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        Date cmTime = null;
        Date currentTime = new Date();
        try {
            cmTime = formatter.parse(commentTime);
            calendar1.setTime(cmTime); //评论时间
            calendar2.setTime(currentTime); //当前时间
            int year1 = calendar1.get(Calendar.YEAR);//评论时间的年份
            int year2 = calendar2.get(Calendar.YEAR);//评论时间的年份
            if(year1!=year2){ //没在一年的评论
                 return new SimpleDateFormat("yyyy年MM月dd日HH时").format(cmTime);
            }
            Long diff = calendar2.getTimeInMillis() - calendar1.getTimeInMillis(); //相差时间
            Long days = diff / (1000 * 60 * 60 * 24); //相差天数
            if (days > 1) {
                //相差一天以上
//                    System.out.println(commentTime.substring(0,commentTime.lastIndexOf(":")));
                message = formatter2.format(cmTime);
            } else {
                Long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60); //相差小时
                int i = hours.intValue();
                if (0 < i && i < 24) {
                    //相差1小时以上
                    message = i + "小时前";
                } else {
                    Long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);//相差分钟
                    if (0 < minutes && minutes < 60) {
                        //相差1分钟以上
                        message = minutes.intValue() + "分钟前";
                    } else {
                        //相差一秒钟以上
                        Long Time = ((currentTime.getTime() - cmTime.getTime()) / 1000);
                        message = Time.intValue() + "秒前";
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return message;
    }
}
