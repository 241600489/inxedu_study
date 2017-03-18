//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.inxedu.os.common.util;

import com.inxedu.os.common.util.DateUtils;
import com.inxedu.os.common.util.ObjectUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public StringUtils() {
    }

    public static String starMobile(String mobile) {
        if(mobile.length() == 11) {
            String starmobile = mobile.charAt(0) + String.valueOf(mobile.charAt(1)) + mobile.charAt(2) + "****" + mobile.charAt(7) + mobile.charAt(8) + mobile.charAt(9) + mobile.charAt(10);
            return starmobile;
        } else {
            return mobile;
        }
    }

    public static String getRandomString(int strLength) {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();

        for(int i = 0; i < strLength; ++i) {
            int charInt;
            char c;
            if(random.nextBoolean()) {
                charInt = 48 + random.nextInt(10);
                c = (char)charInt;
                buffer.append(c);
            } else {
                if(random.nextBoolean()) {
                    charInt = 65 + random.nextInt(26);
                } else {
                    charInt = 97 + random.nextInt(26);
                }

                if(charInt == 79) {
                    charInt = 111;
                }

                c = (char)charInt;
                buffer.append(c);
            }
        }

        return buffer.toString();
    }

    public static String md5(String str) {
        if(str == null) {
            return null;
        } else {
            byte[] newByte1 = str.getBytes();

            try {
                MessageDigest var7 = MessageDigest.getInstance("MD5");
                byte[] newByte2 = var7.digest(newByte1);
                String cryptograph = "";

                for(int i = 0; i < newByte2.length; ++i) {
                    String temp = Integer.toHexString(newByte2[i] & 255);
                    if(temp.length() < 2) {
                        temp = "0" + temp;
                    }

                    cryptograph = cryptograph + temp;
                }

                return cryptograph;
            } catch (Exception var71) {
                var71.printStackTrace();
                return null;
            }
        }
    }

    public static boolean validEmail(String sEmail) {
        String pattern = "^([a-z0-9A-Z]+[-|\\.|_]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return sEmail.matches(pattern);
    }

    public static boolean validMaxLen(String str, int length) {
        return str != null && !str.equals("")?str.length() <= length:false;
    }

    public static boolean validMinLen(String str, int length) {
        return str != null && !str.equals("")?str.length() >= length:false;
    }

    public static boolean equals(String str1, String str2) {
        return str1 != null && !str1.equals("") && str2 != null && !str2.equals("")?str1.equals(str2):false;
    }

    public static int toInt(String str) {
        if(str != null && !str.equals("")) {
            int value1;
            try {
                value1 = Integer.parseInt(str);
            } catch (Exception var3) {
                var3.printStackTrace();
                value1 = 0;
            }

            return value1;
        } else {
            return 0;
        }
    }

    public static String arrayToString(Object[] array, String split) {
        if(array == null) {
            return "";
        } else {
            StringBuffer str = new StringBuffer("");

            for(int i = 0; i < array.length; ++i) {
                if(i != array.length - 1) {
                    str.append(array[i].toString()).append(split);
                } else {
                    str.append(array[i].toString());
                }
            }

            return str.toString();
        }
    }

    public static String getWebInfPath() {
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").toString();
        if(filePath.toLowerCase().indexOf("file:") > -1) {
            filePath = filePath.substring(6, filePath.length());
        }

        if(filePath.toLowerCase().indexOf("classes") > -1) {
            filePath = filePath.replaceAll("/classes", "");
        }

        if(System.getProperty("os.name").toLowerCase().indexOf("window") < 0) {
            filePath = "/" + filePath;
        }

        if(!filePath.endsWith("/")) {
            filePath = filePath + "/";
        }

        return filePath;
    }

    public static String getRootPath() {
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").toString();
        if(filePath.toLowerCase().indexOf("file:") > -1) {
            filePath = filePath.substring(6, filePath.length());
        }

        if(filePath.toLowerCase().indexOf("classes") > -1) {
            filePath = filePath.replaceAll("/classes", "");
        }

        if(filePath.toLowerCase().indexOf("web-inf") > -1) {
            filePath = filePath.substring(0, filePath.length() - 9);
        }

        if(System.getProperty("os.name").toLowerCase().indexOf("window") < 0) {
            filePath = "/" + filePath;
        }

        if(filePath.endsWith("/")) {
            filePath = filePath.substring(0, filePath.length() - 1);
        }

        return filePath;
    }

    public static int formatPage(String page) {
        byte iPage = 1;
        if(page != null && !page.equals("")) {
            int iPage1;
            try {
                iPage1 = Integer.parseInt(page);
            } catch (Exception var4) {
                var4.printStackTrace();
                iPage1 = 1;
            }

            return iPage1;
        } else {
            return iPage;
        }
    }

    public static String getFileSize(String fileSize) {
        String temp = "";
        DecimalFormat df = new DecimalFormat("0.00");
        double dbFileSize = Double.parseDouble(fileSize);
        if(dbFileSize >= 1024.0D) {
            if(dbFileSize >= 1048576.0D) {
                if(dbFileSize >= 1.073741824E9D) {
                    temp = df.format(dbFileSize / 1024.0D / 1024.0D / 1024.0D) + " GB";
                } else {
                    temp = df.format(dbFileSize / 1024.0D / 1024.0D) + " MB";
                }
            } else {
                temp = df.format(dbFileSize / 1024.0D) + " KB";
            }
        } else {
            temp = df.format(dbFileSize / 1024.0D) + " KB";
        }

        return temp;
    }

    public static String getEntry() {
        Random random = new Random(100L);
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(new String("yyyyMMddHHmmssS"));
        return md5(formatter.format(now) + random.nextDouble());
    }

    public static String toUTF8(String str) {
        if(str != null && !str.equals("")) {
            try {
                return new String(str.getBytes("ISO8859-1"), "UTF-8");
            } catch (Exception var2) {
                var2.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }

    public static String to(String str, String charset) {
        if(str != null && !str.equals("")) {
            try {
                return new String(str.getBytes("ISO8859-1"), charset);
            } catch (Exception var3) {
                var3.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }

    public static String getChineseNum(int num) {
        String[] chineseNum = new String[]{"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        return chineseNum[num];
    }

    public static String replaceEnter(String str) {
        return str == null?null:str.replaceAll("\r", "").replaceAll("\n", "");
    }

    public static String getTxtWithoutHTMLElement(String element) {
        if(null == element) {
            return element;
        } else {
            Pattern pattern = Pattern.compile("<[^<|^>]*>");
            Matcher matcher = pattern.matcher(element);
            StringBuffer txt = new StringBuffer();

            String temp;
            while(matcher.find()) {
                temp = matcher.group();
                if(temp.matches("<[\\s]*>")) {
                    matcher.appendReplacement(txt, temp);
                } else {
                    matcher.appendReplacement(txt, "");
                }
            }

            matcher.appendTail(txt);
            temp = txt.toString().replaceAll("[\r|\n]", "");
            temp = temp.replaceAll("\\s+", " ");
            return temp;
        }
    }

    public static String toTrim(String strtrim) {
        return null != strtrim && !strtrim.equals("")?strtrim.trim():"";
    }

    public static String createUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String cutffStr(String sourceStr, int length, String charactor) {
        String resultStr = sourceStr;
        if(sourceStr != null && !"".equals(sourceStr)) {
            if(sourceStr.length() > length) {
                resultStr = sourceStr.substring(0, length);
                resultStr = resultStr + charactor;
            }

            return resultStr;
        } else {
            return "";
        }
    }

    public static boolean isNumber(String str) {
        if(str != null && !str.trim().equals("")) {
            boolean flag = false;

            try {
                Long.parseLong(str);
                flag = true;
            } catch (Exception var3) {
                flag = false;
            }

            return flag;
        } else {
            return false;
        }
    }

    public static String getLength(Object goodsName, int length) {
        if(goodsName == null) {
            return null;
        } else {
            String temp = String.valueOf(goodsName);
            if(temp.length() <= length) {
                return temp;
            } else {
                temp = temp.substring(0, length) + "...";
                return temp;
            }
        }
    }

    public static String handleEmail(String email) {
        if(email == null) {
            return "";
        } else {
            String[] aryEmail = email.split("@");
            if(aryEmail != null && aryEmail.length == 2 && aryEmail[0] != null) {
                String firstPart = aryEmail[0].substring(aryEmail[0].length() / 2, aryEmail[0].length());
                if(firstPart != null && !"".equals(firstPart)) {
                    char[] repeatChar = new char[firstPart.length()];

                    for(int i = 0; i < firstPart.length(); ++i) {
                        repeatChar[i] = 42;
                    }

                    email = email.replaceFirst(firstPart + "@", new String(repeatChar) + "@");
                }
            }

            return email;
        }
    }

    public static boolean isMobileNo(String tocheckNo) {
        return Pattern.matches("^1\\d{10}$", tocheckNo);
    }

    public static boolean neNullAndDigital(String source, boolean ingoreDigital, Integer length) {
        boolean isvalid = false;
        if(source != null && !"".equals(source.trim())) {
            isvalid = true;
        }

        if(!ingoreDigital && isvalid) {
            isvalid = Pattern.matches("^[1-9]\\d{0,}", source);
        }

        if(isvalid && length != null) {
            isvalid = source.trim().length() <= length.intValue();
        }

        return isvalid;
    }

    public static boolean validNull(String str) {
        return str != null && !str.trim().equals("");
    }

    public static boolean validNull(String... str) {
        for(int i = 0; i < str.length; ++i) {
            if(str[i] == null || str[i].trim().equals("")) {
                return false;
            }
        }

        return true;
    }

    public static String getRootPath(String resource) {
        String filePath = Thread.currentThread().getContextClassLoader().getResource(resource).toString();
        if(filePath.toLowerCase().indexOf("file:") > -1) {
            filePath = filePath.substring(6, filePath.length());
        }

        if(filePath.toLowerCase().indexOf("classes") > -1) {
            filePath = filePath.replaceAll("/classes", "");
        }

        if(filePath.toLowerCase().indexOf("web-inf") > -1) {
            filePath = filePath.substring(0, filePath.length() - 9);
        }

        if(System.getProperty("os.name").toLowerCase().indexOf("window") < 0) {
            filePath = "/" + filePath;
        }

        if(!filePath.endsWith("/")) {
            filePath = filePath + "/";
        }

        return filePath;
    }

    public static String getRandStr(int n) {
        Random random = new Random();
        String sRand = "";

        for(int i = 0; i < n; ++i) {
            String rand = String.valueOf(random.nextInt(10));
            sRand = sRand + rand;
        }

        return sRand;
    }

    public static String getSysTimeRandom() {
        return System.currentTimeMillis() + "" + (new Random()).nextInt(100);
    }

    public static String getSysTimeRandom(int count) {
        String resultRandom = System.currentTimeMillis() + "" + (new Random()).nextInt(100);
        String resultRandomPro = "";
        int resultCount = resultRandom.length();
        if(count < resultCount) {
            return resultRandom.substring(resultCount - 1 - count, resultCount - 1);
        } else {
            for(int i = 0; i < count - resultCount; ++i) {
                resultRandomPro = resultRandomPro + "0";
            }

            return resultRandomPro + resultRandom;
        }
    }

    public static String[] parseParam(String source) {
        if(source != null && !"".equals(source)) {
            String[] resultAry = source.split("&");
            return resultAry;
        } else {
            throw new IllegalArgumentException("source is null");
        }
    }

    public static String[] parseParamArray(String source) {
        if(source != null && !"".equals(source)) {
            String[] resultAry = source.split("\\|");
            return resultAry;
        } else {
            throw new IllegalArgumentException("source is null");
        }
    }

    public static String convStrToHessian(String item, int count) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < count; ++i) {
            sb.append(URLEncoder.encode(item, "utf-8")).append("|");
        }

        if(sb != null && sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public static String convToHessian(String item, int count) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < count; ++i) {
            sb.append(URLEncoder.encode(item, "utf-8")).append("|");
        }

        return sb.toString();
    }

    public static String convAryToStr(String sourceStr, String sourceChar, String resultChar, boolean isTrans) {
        if(isTrans) {
            sourceChar = "\\" + sourceChar;
        }

        String[] sourceStrAry = sourceStr.split(sourceChar);
        int count = sourceStrAry.length;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < count; ++i) {
            try {
                Long.parseLong(sourceStrAry[i]);
            } catch (Exception var9) {
                var9.printStackTrace();
            }

            sb.append(sourceStrAry[i]).append(resultChar);
        }

        if(sb != null && sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public static String queryParam(String param, String queryParam) {
        return validNull(param)?queryParam + "=" + param + "&":"";
    }

    public static Timestamp convertToTimestamp(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date myDate = null;
        Timestamp myTimestamp = null;

        try {
            myDate = sdf.parse(time);
            myTimestamp = new Timestamp(myDate.getTime());
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return myTimestamp;
    }

    public static String randomBase() {
        String result = String.valueOf(System.currentTimeMillis() % 10L);
        return result;
    }

    public static Long getDeliveryIdBase(Long id) {
        if(id == null) {
            throw new IllegalArgumentException();
        } else {
            return Long.valueOf(id.longValue() % 10L);
        }
    }

    public static boolean checkIp(String strIp1, String StrIp2) {
        boolean boo = false;
        if("".equals(StrIp2)) {
            return true;
        } else {
            boolean isOrderIpRule = strIp1.matches("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
            if(!isOrderIpRule) {
                return boo;
            } else {
                String[] ipArray = StrIp2.split(",");

                for(int i = 0; i < ipArray.length; ++i) {
                    String ipArr = ipArray[i];
                    String ipay = "";
                    if(ipArr.contains("*")) {
                        ipay = ipArr.substring(0, ipArr.lastIndexOf("."));
                        boo = strIp1.substring(0, strIp1.lastIndexOf(".")).equals(ipay);
                        if(boo) {
                            return boo;
                        }
                    } else if(ipArr.contains("-")) {
                        ipay = ipArr.substring(ipArr.lastIndexOf(".") + 1);
                        String[] ipayArray = ipay.split("-");
                        String ips = strIp1.substring(strIp1.lastIndexOf(".") + 1);
                        if(Integer.parseInt(ipayArray[0]) <= Integer.parseInt(ips) && Integer.parseInt(ips) <= Integer.parseInt(ipayArray[1])) {
                            boo = true;
                            return boo;
                        }
                    } else {
                        boo = strIp1.equals(ipArr);
                        if(boo) {
                            return boo;
                        }
                    }
                }

                return boo;
            }
        }
    }

    public static boolean isEmpty(String str) {
        return null == str || str.trim().length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String packagingString(String str, String prefix, String suffix) {
        if(isEmpty(str)) {
            str = "";
        }

        if(isEmpty(prefix)) {
            prefix = "";
        }

        if(isEmpty(suffix)) {
            suffix = "";
        }

        return prefix + str + suffix;
    }

    public static String[] sortArray(String[] array) {
        for(int i = 0; i < array.length; ++i) {
            for(int j = 0; j < i; ++j) {
                if(array[i].compareTo(array[j]) < 0) {
                    String temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }

        return array;
    }

    public static int bisearch(String[] sourceArray, String seek) {
        if(null != sourceArray && sourceArray.length != 0 && null != seek) {
            int bottom = 0;
            int top = sourceArray.length - 1;

            while(bottom <= top) {
                int mid = (bottom + top) / 2;
                int result = sourceArray[mid].compareTo(seek);
                if(0 == result) {
                    return mid;
                }

                if(result > 0) {
                    top = mid - 1;
                } else {
                    bottom = mid + 1;
                }
            }

            return -1;
        } else {
            return -1;
        }
    }

    public static boolean isBlank(String str) {
        return null == str || str.trim().length() == 0;
    }

    public static boolean isBlank(Object obj) {
        return ObjectUtils.isNull(obj)?true:isBlank(obj.toString());
    }

    public static String getModelDate(Date oldDate) {
        if(ObjectUtils.isNotNull(oldDate)) {
            Date newDate = new Date();
            long second = (newDate.getTime() - oldDate.getTime()) / 1000L;
            if(second <= 60L) {
                return second + "秒前";
            } else if(60L < second && second <= 3600L) {
                second /= 60L;
                return second + "分钟前";
            } else if(3600L < second && second <= 86400L) {
                second = second / 60L / 60L;
                return second + "小时前";
            } else if(86400L < second && second <= 864000L) {
                String formatDate = DateUtils.formatDate(oldDate, "HH:mm:ss");
                second = second / 60L / 60L / 24L;
                return second + "天前 " + formatDate;
            } else {
                return DateUtils.formatDate(oldDate, "yyyy-MM-dd HH:mm:ss");
            }
        } else {
            return "";
        }
    }
}
