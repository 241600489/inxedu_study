//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.inxedu.os.common.util;

import com.inxedu.os.common.util.ObjectUtils;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public HttpUtil() {
    }

    public static String doGet(String url, String queryString) {
        String response = null;
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        method.getParams().setParameter("http.protocol.content-charset", "UTF-8");

        try {
            if(StringUtils.isNotBlank(queryString)) {
                method.setQueryString(URIUtil.encodeQuery(queryString));
            }

            client.executeMethod(method);
            if(method.getStatusCode() == 200) {
                response = method.getResponseBodyAsString();
            }
        } catch (URIException var10) {
            ;
        } catch (IOException var11) {
            ;
        } finally {
            method.releaseConnection();
        }

        return response;
    }

    public static String doPost(String url, Map<String, String> params) {
        StringBuffer result = new StringBuffer();
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        method.getParams().setParameter("http.protocol.content-charset", "UTF-8");
        if(!ObjectUtils.isNull(params)) {
            NameValuePair[] var14 = new NameValuePair[params.size()];
            int var15 = 0;

            Entry entry;
            for(Iterator i$ = params.entrySet().iterator(); i$.hasNext(); var14[var15++] = new NameValuePair((String)entry.getKey(), (String)entry.getValue())) {
                entry = (Entry)i$.next();
            }

            method.setRequestBody(var14);
        }

        try {
            client.executeMethod(method);
            if(method.getStatusCode() == 200) {
                BufferedReader var141 = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "UTF-8"));
                String var151 = null;

                while((var151 = var141.readLine()) != null) {
                    result.append(var151);
                }
            }
        } catch (IOException var12) {
            ;
        } finally {
            method.releaseConnection();
        }

        return result.toString();
    }

    public static String doPostXml(String urlStr, String xmlStr) {
        String result = null;

        try {
            URL e = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection)e.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.write(xmlStr.getBytes());
            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer("");

            String lines;
            while((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }

            reader.close();
            connection.disconnect();
            result = sb.toString();
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        return result;
    }
}
