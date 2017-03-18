//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.inxedu.os.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtils {
    public DateUtils() {
    }

    public static String getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(Calendar.getInstance().getTime());
    }

    public static String getTimeBeforeORAfter(int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(5, days);
        return sdf.format(c.getTime());
    }

    public static Date toDate(String date, String pattern) {
        if(("" + date).equals("")) {
            return null;
        } else {
            if(pattern == null) {
                pattern = "yyyy-MM-dd";
            }

            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date newDate = new Date();

            try {
                newDate = sdf.parse(date);
            } catch (Exception var5) {
                var5.printStackTrace();
            }

            return newDate;
        }
    }

    public static String toString(Date date, String pattern) {
        if(date == null) {
            return "";
        } else {
            if(pattern == null) {
                pattern = "yyyy-MM-dd";
            }

            String dateString = "";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);

            try {
                dateString = sdf.format(date);
            } catch (Exception var5) {
                var5.printStackTrace();
            }

            return dateString;
        }
    }

    public static Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    public static Date getNowDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    public static String dateToStrLong(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    public static String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    public static String dateToStr(Date dateDate, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(dateDate);
    }

    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    public static Date strToDate(String strDate, String patten) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if(patten != null && patten.length() > 0) {
            formatter = new SimpleDateFormat(patten);
        }

        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    public static Date getNow() {
        Date currentTime = new Date();
        return currentTime;
    }

    public static String getCurrentDateStr() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static Date getLastDate(long day) {
        Date date = new Date();
        long date_3_hm = date.getTime() - 122400000L * day;
        Date date_3_hm_date = new Date(date_3_hm);
        return date_3_hm_date;
    }

    public static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getStringTodayto() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getHour() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String hour = dateString.substring(11, 13);
        return hour;
    }

    public static String getTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String min = dateString.substring(14, 16);
        return min;
    }

    public static String getUserDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getTwoHour(String st1, String st2) {
        String[] kk = null;
        String[] jj = null;
        kk = st1.split(":");
        jj = st2.split(":");
        if(Integer.parseInt(kk[0]) < Integer.parseInt(jj[0])) {
            return "0";
        } else {
            double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60.0D;
            double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60.0D;
            return y - u > 0.0D?y - u + "":"0";
        }
    }

    public static String getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0L;

        try {
            Date var7 = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            day = (var7.getTime() - mydate.getTime()) / 86400000L;
        } catch (Exception var71) {
            var71.printStackTrace();
            return "";
        }

        return day + "";
    }

    public static String getPreTime(String sj1, String jj) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mydate1 = "";

        try {
            Date var7 = format.parse(sj1);
            long Time = var7.getTime() / 1000L + (long)(Integer.parseInt(jj) * 60);
            var7.setTime(Time * 1000L);
            mydate1 = format.format(var7);
        } catch (Exception var71) {
            var71.printStackTrace();
        }

        return mydate1;
    }

    public static String getNextDay(String nowdate, String delay) {
        try {
            SimpleDateFormat var7 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String mdate = "";
            Date d = strToDate(nowdate);
            long myTime = d.getTime() / 1000L + Long.parseLong(delay) * 24L * 60L * 60L;
            d.setTime(myTime * 1000L);
            mdate = var7.format(d);
            return mdate;
        } catch (Exception var71) {
            var71.printStackTrace();
            return "";
        }
    }

    public static String getNextDaytoSen(String statrdate, String delay) {
        try {
            SimpleDateFormat var7 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String mdate = "";
            Date d = strToDate(statrdate, "yyyy-MM-dd HH:mm:ss");
            long myTime = d.getTime() / 1000L + Long.parseLong(delay) * 24L * 60L * 60L;
            d.setTime(myTime * 1000L);
            mdate = var7.format(d);
            return mdate;
        } catch (Exception var71) {
            var71.printStackTrace();
            return "";
        }
    }

    public static boolean isLeapYear(String ddate) {
        Date d = strToDate(ddate);
        GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
        gc.setTime(d);
        int year = gc.get(1);
        return year % 400 == 0?true:(year % 4 == 0?year % 100 != 0:false);
    }

    public static String getEDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(str, pos);
        String j = strtodate.toString();
        String[] k = j.split(" ");
        return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
    }

    public static String getEndDateOfMonth(String dat) {
        String str = dat.substring(0, 8);
        String month = dat.substring(5, 7);
        int mon = Integer.parseInt(month);
        if(mon != 1 && mon != 3 && mon != 5 && mon != 7 && mon != 8 && mon != 10 && mon != 12) {
            if(mon != 4 && mon != 6 && mon != 9 && mon != 11) {
                if(isLeapYear(dat)) {
                    str = str + "29";
                } else {
                    str = str + "28";
                }
            } else {
                str = str + "30";
            }
        } else {
            str = str + "31";
        }

        return str;
    }

    public static boolean isSameWeekDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(1) - cal2.get(1);
        if(0 == subYear) {
            if(cal1.get(3) == cal2.get(3)) {
                return true;
            }
        } else if(1 == subYear && 11 == cal2.get(2)) {
            if(cal1.get(3) == cal2.get(3)) {
                return true;
            }
        } else if(-1 == subYear && 11 == cal1.get(2) && cal1.get(3) == cal2.get(3)) {
            return true;
        }

        return false;
    }

    public static String getSeqWeek() {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        String week = Integer.toString(c.get(3));
        if(week.length() == 1) {
            week = "0" + week;
        }

        String year = Integer.toString(c.get(1));
        return year + week;
    }

    public static String getWeek(String sdate, String num) {
        Date dd = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(dd);
        if(num.equals("1")) {
            c.set(7, 2);
        } else if(num.equals("2")) {
            c.set(7, 3);
        } else if(num.equals("3")) {
            c.set(7, 4);
        } else if(num.equals("4")) {
            c.set(7, 5);
        } else if(num.equals("5")) {
            c.set(7, 6);
        } else if(num.equals("6")) {
            c.set(7, 7);
        } else if(num.equals("0")) {
            c.set(7, 1);
        }

        return (new SimpleDateFormat("yyyy-MM-dd")).format(c.getTime());
    }

    public static String getWeek(String sdate) {
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return (new SimpleDateFormat("EEEE")).format(c.getTime());
    }

    public static String getWeekStr(String sdate) {
        String str = "";
        str = getWeek(sdate);
        if("1".equals(str)) {
            str = "星期日";
        } else if("2".equals(str)) {
            str = "星期一";
        } else if("3".equals(str)) {
            str = "星期二";
        } else if("4".equals(str)) {
            str = "星期三";
        } else if("5".equals(str)) {
            str = "星期四";
        } else if("6".equals(str)) {
            str = "星期五";
        } else if("7".equals(str)) {
            str = "星期六";
        }

        return str;
    }

    public static long getDays(String date1, String date2) {
        if(date1 != null && !date1.equals("")) {
            if(date2 != null && !date2.equals("")) {
                SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                Date mydate = null;

                try {
                    date = myFormatter.parse(date1);
                    mydate = myFormatter.parse(date2);
                } catch (Exception var7) {
                    var7.printStackTrace();
                }

                long day = (date.getTime() - mydate.getTime()) / 86400000L;
                return day;
            } else {
                return 0L;
            }
        } else {
            return 0L;
        }
    }

    public static String getNowMonth(String sdate) {
        sdate = sdate.substring(0, 8) + "01";
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int u = c.get(7);
        String newday = getNextDay(sdate, 1 - u + "");
        return newday;
    }

    public static long getDistinceMonth(String beforedate, String afterdate) throws ParseException {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        long monthCount = 0L;

        try {
            Date var7 = d.parse(beforedate);
            Date d2 = d.parse(afterdate);
            monthCount = (long)((getYear(d2) - getYear(var7)) * 12 + getMonth(d2) - getMonth(var7));
        } catch (ParseException var71) {
            var71.printStackTrace();
        }

        return monthCount;
    }

    public static long getDistinceDay(Date beforedate, Date afterdate) {
        long dayCount = 0L;
        dayCount = (beforedate.getTime() - afterdate.getTime()) / 86400000L;
        return dayCount;
    }

    public static long getDistinceDay(String beforedate, String afterdate) throws ParseException {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        long dayCount = 0L;

        try {
            Date var7 = d.parse(beforedate);
            Date d2 = d.parse(afterdate);
            dayCount = (d2.getTime() - var7.getTime()) / 86400000L;
        } catch (ParseException var71) {
            var71.printStackTrace();
        }

        return dayCount;
    }

    public static String disTime(Date date1, Date date2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return disTime(df.format(date1), df.format(date2));
    }

    public static int disDay(String date1, String date2) {
        int day = 0;

        try {
            SimpleDateFormat var8 = new SimpleDateFormat("yyyy-MM-dd");
            Date now = var8.parse(date1);
            Date date = var8.parse(date2);
            long l = now.getTime() - date.getTime();
            day = (int)(l / 86400000L);
        } catch (Exception var81) {
            var81.printStackTrace();
        }

        return day;
    }

    public static double disDateTime(String date1, String date2) {
        double la = 0.0D;

        try {
            SimpleDateFormat var17 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = var17.parse(date1);
            Date date = var17.parse(date2);
            long l = now.getTime() - date.getTime();
            long day = l / 86400000L;
            long hour = l / 3600000L - day * 24L;
            double min = (double)(l / 60000L - day * 24L * 60L - hour * 60L);
            double s = (double)(l / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L) - min * 60.0D;
            la = (double)(day * 24L + hour) + min / 60.0D + s / 3600.0D;
        } catch (Exception var171) {
            var171.printStackTrace();
        }

        return la;
    }

    public static String disTime(String date1, String date2) {
        StringBuffer sb = new StringBuffer();

        try {
            SimpleDateFormat var14 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = var14.parse(date1);
            Date date = var14.parse(date2);
            long l = now.getTime() - date.getTime();
            long day = l / 86400000L;
            long hour = l / 3600000L - day * 24L;
            long min = l / 60000L - day * 24L * 60L - hour * 60L;
            if(day <= 0L && hour <= 0L && min <= 0L) {
                return "1";
            }

            if(day > 0L) {
                sb.append(day + "天");
            }

            if(hour > 0L) {
                sb.append(hour + "小时");
            }

            if(min > 0L) {
                sb.append(min + "");
            }
        } catch (Exception var141) {
            var141.printStackTrace();
        }

        return sb.toString();
    }

    public static Date getFirstDateOfMonth(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(5, 1);
        c.set(11, 0);
        c.set(12, 0);
        c.set(13, 0);
        Date date = c.getTime();
        return date;
    }

    public static String formatDate(Date d, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.format(d);
    }

    public static Date parseToDate(String sDate, String pattern) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.parse(sDate);
    }

    public static Date getMinTime(Date dt) {
        Date dt1 = null;

        try {
            dt1 = parseToDate(formatDate(dt, "yyyyMMdd"), "yyyyMMdd");
        } catch (ParseException var3) {
            var3.printStackTrace();
        }

        return dt1;
    }

    public static Date getMaxTime(Date dt) {
        Date dt1 = null;
        Calendar ca = Calendar.getInstance();
        ca.setTime(dt);
        ca.add(5, 1);
        dt1 = ca.getTime();
        dt1 = getMinTime(dt1);
        ca.setTime(dt1);
        ca.add(13, -1);
        dt1 = ca.getTime();
        return dt1;
    }

    public static Timestamp parseToTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    public static String formatDate(Timestamp d, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.format(d);
    }

    public static boolean isValidKey(String createKeyDate, long expire_time) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();

        try {
            Date var9 = dateformat.parse(createKeyDate);
            Date currentTime = dateformat.parse(dateformat.format(now));
            long time = (currentTime.getTime() - var9.getTime()) / 1000L;
            return time > expire_time;
        } catch (ParseException var91) {
            var91.printStackTrace();
            return false;
        }
    }

    public static Date compareDateInNull(Date createDate, String absDays, String sourceDate2) {
        String loseReadyDateStr = getNextDay(formatDate(createDate, "yyyy-MM-dd HH:mm:ss"), absDays.toString());
        Date loseReadyDate = toDate(loseReadyDateStr, "yyyy-MM-dd HH:mm:ss");
        loseReadyDate = getMaxTime(loseReadyDate);
        if("null".equals(sourceDate2) && "0".equals(absDays)) {
            return getMaxTime(new Date());
        } else if("null".equals(sourceDate2)) {
            return loseReadyDate;
        } else {
            Date sourceDate;
            if("0".equals(absDays)) {
                sourceDate = getMaxTime(toDate(sourceDate2, "yyyy-MM-dd HH:mm:ss"));
                return sourceDate;
            } else {
                sourceDate = getMaxTime(toDate(sourceDate2, "yyyy-MM-dd HH:mm:ss"));
                return loseReadyDate.after(sourceDate)?sourceDate:loseReadyDate;
            }
        }
    }

    public static boolean loseDate(Date loseDate) {
        Date nows = new Date();
        long hous = (nows.getTime() - loseDate.getTime()) / 1000L;
        return hous > 0L;
    }

    public static boolean betweenBeginAndEnd(Date paramDate, Date beginDate, Date endDate) {
        long bHous = (paramDate.getTime() - beginDate.getTime()) / 1000L;
        long eHous = (endDate.getTime() - paramDate.getTime()) / 1000L;
        return bHous > 0L && eHous > 0L;
    }

    public static Date compareDate(Date createDate, String absDays, Date sourceDate2) {
        sourceDate2 = getMaxTime(sourceDate2);
        String loseReadyDateStr = getNextDay(formatDate(createDate, "yyyy-MM-dd HH:mm:ss"), absDays.toString());
        Date loseReadyDate = toDate(loseReadyDateStr, "yyyy-MM-dd HH:mm:ss");
        loseReadyDate = getMaxTime(loseReadyDate);
        return loseReadyDate.after(sourceDate2)?sourceDate2:loseReadyDate;
    }

    public static String formatTime(int day, String standFormat) {
        String date = null;
        Calendar c = Calendar.getInstance();
        c.add(5, day);
        SimpleDateFormat format = new SimpleDateFormat(standFormat);
        date = format.format(c.getTime());
        return date;
    }

    public static String getTimeBeforeORAfter(int days, String pattern) {
        if(pattern == null) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar c = Calendar.getInstance();
        c.add(5, days);
        return sdf.format(c.getTime());
    }

    public static boolean isAfterToday(String tocompareStr) throws ParseException {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date tocompareDate = sdf.parse(tocompareStr);
        return tocompareDate.after(Calendar.getInstance().getTime());
    }

    public static Long countDifSeconds(Timestamp t1, Timestamp t2) {
        return t1 != null && t2 != null?(t1.compareTo(t2) >= 0?Long.valueOf((t1.getTime() - t2.getTime()) / 1000L):Long.valueOf((t2.getTime() - t1.getTime()) / 1000L)):Long.valueOf(0L);
    }

    public static int getDateOfMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(5);
    }

    public static String getTimeBeforeOrAfterSenconds(Timestamp sourceTime, long seconds) {
        if(sourceTime != null) {
            long time = sourceTime.getTime() + seconds * 1000L;
            sourceTime.setTime(time);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(sourceTime);
        } else {
            return "";
        }
    }

    public static String getTimeBeforeOrAfterMonth(int months, String pattern, Date sourceTime) {
        if(pattern == null) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar c = Calendar.getInstance();
        c.setTime(sourceTime);
        c.add(2, months);
        return sdf.format(c.getTime());
    }

    public static void main(String[] args) {
        Calendar lastDate = Calendar.getInstance();
        lastDate.add(2, -1);
        lastDate.add(5, 5);
        Date date = lastDate.getTime();
        long s = getDistinceDay(new Date(), date);
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(1);
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(2);
    }

    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(12);
    }

    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(13);
    }

    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");

        for(int i = 1; i < hex.length; ++i) {
            int data = Integer.parseInt(hex[i], 16);
            string.append((char)data);
        }

        return string.toString();
    }

    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();

        for(int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }
}
