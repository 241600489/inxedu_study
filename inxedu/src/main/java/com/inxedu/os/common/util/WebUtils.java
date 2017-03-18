//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.inxedu.os.common.util;

import com.inxedu.os.common.constants.CommonConstants;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class WebUtils {
    public static String MYDOMAIN;

    public WebUtils() {
    }

    public static void setCookie(HttpServletResponse response, String key, String value, int days) {
        setCookie(response, key, value, days, MYDOMAIN);
    }

    public static void setCookie(HttpServletResponse response, String key, String value, int days, String domain) {
        if(key != null && value != null) {
            Cookie cookie = new Cookie(key, value);
            cookie.setMaxAge(days * 24 * 60 * 60);
            cookie.setPath("/");
            if(StringUtils.isNotEmpty(domain)) {
                cookie.setDomain(domain);
            }

            response.addCookie(cookie);
        }

    }

    public static void setCookieOnSeconds(HttpServletResponse response, String key, String value, int seconds) {
        final String domain = MYDOMAIN;
        if (key != null && value != null) {
            Cookie cookie = new Cookie(key, value);
            cookie.setMaxAge(seconds);
            cookie.setPath("/");
            if (StringUtils.isNotEmpty(domain)) {
                cookie.setDomain(domain);
            }
            response.addCookie(cookie);
        }

    }

    public static String getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        String resValue = "";
        if(cookies != null && cookies.length > 0) {
            for(int i = 0; i < cookies.length; ++i) {
                if(key.equalsIgnoreCase(cookies[i].getName()) && StringUtils.isNotEmpty(cookies[i].getValue())) {
                    resValue = cookies[i].getValue();
                }
            }
        }

        return resValue;
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        deleteCookieDomain(request, response, name, MYDOMAIN);
    }

    public static void deleteCookieDomain(HttpServletRequest request, HttpServletResponse response, String name, String domain) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0) {
            for(int i = 0; i < cookies.length; ++i) {
                if(name.equalsIgnoreCase(cookies[i].getName())) {
                    Cookie ck = new Cookie(cookies[i].getName(), (String)null);
                    ck.setPath("/");
                    if(StringUtils.isNotEmpty(domain)) {
                        ck.setDomain(domain);
                    }
                    ck.setMaxAge(0);
                    response.addCookie(ck);
                    return;
                }
            }
        }

    }

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1")) {
                InetAddress inet = null;

                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException var4) {
                    var4.printStackTrace();
                }

                ipAddress = inet.getHostAddress();
            }
        }

        if(ipAddress != null && ipAddress.length() > 15 && ipAddress.indexOf(",") > 0) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }

        return ipAddress;
    }

    public static String getServletRequestUrlParms(HttpServletRequest request) {
        StringBuffer sbUrlParms = request.getRequestURL();
        sbUrlParms.append("?");
        Enumeration parNames = request.getParameterNames();

        while(parNames.hasMoreElements()) {
            String parName = ((String)parNames.nextElement()).toString();

            try {
                sbUrlParms.append(parName).append("=").append(URLEncoder.encode(request.getParameter(parName), "UTF-8")).append("&");
            } catch (UnsupportedEncodingException var5) {
                return "";
            }
        }

        return sbUrlParms.toString();
    }

    public static String getServletRequestUriParms(HttpServletRequest request) {
        StringBuffer sbUrlParms = new StringBuffer(request.getRequestURI());
        sbUrlParms.append("?");
        Enumeration parNames = request.getParameterNames();

        while(parNames.hasMoreElements()) {
            String parName = ((String)parNames.nextElement()).toString();

            try {
                sbUrlParms.append(parName).append("=").append(URLEncoder.encode(request.getParameter(parName), "UTF-8")).append("&");
            } catch (UnsupportedEncodingException var5) {
                return "";
            }
        }

        return sbUrlParms.toString();
    }

    public static boolean checkLoginName(String value) {
        return value.matches(CommonConstants.loginRegex);
    }

    public static boolean checkMobile(String value) {
        return value.matches(CommonConstants.telRegex);
    }

    public static boolean checkEmail(String value, int length) {
        if(length == 0) {
            length = 40;
        }

        return value.matches(CommonConstants.emailRegex) && value.length() <= length;
    }

    public static boolean isPasswordAvailable(String password) {
        String partten = "^[_0-9a-zA-Z]{6,}$";
        boolean flag = password.matches(partten) && password.length() >= 6 && password.length() <= 16;
        return flag;
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String her = request.getHeader("x-requested-with");
        return StringUtils.isNotEmpty(her);
    }

    public static boolean isNotAjaxRequest(HttpServletRequest request) {
        return !isAjaxRequest(request);
    }

    public static String getUserAgent(HttpServletRequest request) {
        String uabrow = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(uabrow);
        Browser browser = userAgent.getBrowser();
        OperatingSystem os = userAgent.getOperatingSystem();
        return browser.getName().toLowerCase() + ";" + os.getName().toLowerCase();
    }

    public static String replaceTagHTML(String src) {
        String regex = "\\<(.+?)\\>";
        return StringUtils.isNotEmpty(src)?src.replaceAll(regex, ""):"";
    }

    static {
        MYDOMAIN = CommonConstants.MYDOMAIN;
    }
}
