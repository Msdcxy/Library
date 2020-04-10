package com.common.lib.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dengdayi
 * Date: 2019/01/03 16：49
 * Desc:日期转化工具
 */
public class CommUtils {
    public static final String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_YMDH = "yyyy-MM-dd HH";
    public static final String FORMAT_YMD = "yyyy-MM-dd";
    public static final String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_MS = "mm:ss";

    /**
     * 将日期格式转换成yyyy-MM-dd的字符串格式
     * 返回值如：2010-10-06
     *
     * @param time 要转换的日期
     * @return
     */
    public static String dateToString(Date time) {

        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_YMD); //定义将日期格式要换成的格式
        String stringTime = formatter.format(time);
        return stringTime;

    }

    public static String dateToString(Date time, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern); //定义将日期格式要换成的格式
        String stringTime = formatter.format(time);
        return stringTime;
    }

    /**
     * 将日期格式转换成yyyyMMdd的字符串格式
     * 返回值如：2010-10-06
     *
     * @param time 要转换的日期
     * @return
     */
    public static String dateTimeToString(Date time) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd"); //定义将日期格式要换成的格式
        String stringTime = formatter.format(time);

        return stringTime;

    }


    /**
     * 将日期格式转换成yyyy-MM-dd的字符串格式
     * 返回值如：2010-10-06
     *
     * @param time 要转换的日期
     * @return
     */
    public static Date dateToDate(Date time) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //定义将日期格式要换成的格式
        String stringTime = formatter.format(time);
        Date date = null;
        try {
            date = formatter.parse(stringTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    /**
     * 得到当前时间，以字符串表示
     *
     * @return
     */
    public static String getDate() {
        Date date = new Date();
        return CommUtils.dateToString(date);
    }

    /**
     * 获取相差天数
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static long diffDays(Date beginDate, Date endDate) {
        long beginTime = beginDate.getTime();
        long endTime = endDate.getTime();
        //先算出两时间的毫秒数之差大于一天的天数
        int betweenDays = (int) ((endTime - beginTime) / (1000 * 60 * 60 * 24));
        return betweenDays;
    }

    /**
     * 算天数后的小时
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static long diffHours(Date startTime, Date endTime) {
        long nd = 1000 * 24 * 60 * 60;//每天毫秒数
        long nh = 1000 * 60 * 60;//每小时毫秒数
        long diff = endTime.getTime() - startTime.getTime();
        return diff % nd / nh;
    }

    /**
     * 算天数,小时，在计算分钟
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static long diffMins(Date startTime, Date endTime) {
        long nd = 1000 * 24 * 60 * 60;//每天毫秒数
        long nh = 1000 * 60 * 60;//每小时毫秒数
        long nm = 1000 * 60;
        long diff = endTime.getTime() - startTime.getTime();
        return diff % nd % nh / nm;
    }

    /**
     * 字符串转date
     *
     * @param str
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String str, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(str);
    }

    public static int getDateApm(Date date) {
        long time = date.getTime();
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        return mCalendar.get(Calendar.AM_PM);
    }

    /**
     * 比较时间大小
     *
     * @param startDate:开始时间
     * @param endDate：结束时间
     * @return
     */
    public static boolean isDateOneBigger(Date startDate, Date endDate) {
        boolean isBigger = false;
        if (startDate.getTime() > endDate.getTime()) {
            isBigger = true;
        } else if (startDate.getTime() < endDate.getTime()) {
            isBigger = false;
        }
        return isBigger;
    }

    /**
     * 获取当前时间秒数
     *
     * @return
     */
    public static int getCurrentHour() {
        long time = System.currentTimeMillis();
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        return mCalendar.get(Calendar.HOUR);
    }

    /**
     * 获取当前时间分钟
     *
     * @return
     */
    public static int getCurrentMinut() {
        long time = System.currentTimeMillis();
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        return mCalendar.get(Calendar.MINUTE);
    }

    /**
     * 获取当前时间秒数
     *
     * @return
     */
    public static int getCurrentSecond() {
        long time = System.currentTimeMillis();
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        return mCalendar.get(Calendar.SECOND);
    }

    /**
     * 获取当前时间秒数
     *
     * @return
     */
    public static String getCurrentSecondStr() {
        long time = System.currentTimeMillis();
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        return mCalendar.get(Calendar.SECOND) > 10 ? String.valueOf(mCalendar.get(Calendar.SECOND)) : "0" + mCalendar.get(Calendar.SECOND);
    }

    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            SimpleDateFormat sf = new SimpleDateFormat(FORMAT_YMD);
            String date1Str = sf.format(date1);
            String date2Str = sf.format(date2);
            return date1Str.equals(date2Str);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    /**
     * 获取两个日期之间的间隔天数
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static int getGapCount(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }
}
