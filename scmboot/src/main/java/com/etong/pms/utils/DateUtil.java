package com.etong.pms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static  SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
    public static  SimpleDateFormat simpleDateFormatDayM = new SimpleDateFormat("yyyy-MM-dd HH");
    public static String getHMS(Date date){
        return simpleDateFormat.format(date);
    }
    public static String getDay(Date date){
        return simpleDateFormatDay.format(date);
    }
    public static String getDayM(Date date){
        return simpleDateFormatDayM.format(date);
    }
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }
    public static Date stringFormatDate(String date) throws ParseException {
        return simpleDateFormat.parse(date);
    }
    public static Long DaysBetween(Date before,Date after) {
        return  after.getTime()-before.getTime();
    }





//    public static void main(String[] args) throws ParseException {
////        System.out.println("int:"+((1-1)>-1));
//        System.out.println(getDay(new Date()));
////        System.out.println("int:"+((1-3)<5));
////        System.out.println("000--"+getDayM(new Date()));
////        System.out.println("分钟:"+getMinute(new Date()));
////        System.out.println("2018-11-01 17:21".substring("2018-11-01 17:21".indexOf(":")+1));
////        System.out.println("true: "+"2018-11-01 17:21".contains(getDayM(new Date())));
////        System.out.println("date:"+stringFormatDate("2018-11-02 14:50"));
////
////        System.out.println(DaysBetween(stringFormatDate("2018-11-04 12:50"),new Date() ));
//    }
}
