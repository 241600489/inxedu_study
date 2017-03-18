//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.inxedu.os.common.util;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PropertyUtil {
    private static Map<String, PropertyUtil> instance = Collections.synchronizedMap(new HashMap());
    protected String sourceUrl;
    protected ResourceBundle resourceBundle;
    private static Map<String, String> convert = Collections.synchronizedMap(new HashMap());

    protected PropertyUtil(String sourceUrl) {
        this.sourceUrl = sourceUrl;
        this.load();
    }

    public static PropertyUtil getInstance(String sourceUrl) {
        Class var1 = PropertyUtil.class;
        synchronized(PropertyUtil.class) {
            PropertyUtil manager = (PropertyUtil)instance.get(sourceUrl);
            if(manager == null) {
                manager = new PropertyUtil(sourceUrl);
                instance.put(sourceUrl, manager);
            }

            return manager;
        }
    }

    private synchronized void load() {
        try {
            this.resourceBundle = ResourceBundle.getBundle(this.sourceUrl);
        } catch (Exception var2) {
            var2.printStackTrace();
            throw new RuntimeException("sourceUrl = " + this.sourceUrl + "file load error!", var2);
        }
    }

    public String getProperty(String key) {
        try {
            return new String(this.resourceBundle.getString(key).getBytes("iso-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException var3) {
            return this.resourceBundle.getString(key);
        }
    }

    public Map<String, String> readyConvert() {
        Enumeration enu = this.resourceBundle.getKeys();

        while(enu.hasMoreElements()) {
            String key = (String)enu.nextElement();
            String value = this.resourceBundle.getString(key);
            convert.put(value, key);
        }

        return convert;
    }

    public Map<String, String> readyConvert(ResourceBundle resourcebundle) {
        Enumeration enu = resourcebundle.getKeys();

        while(enu.hasMoreElements()) {
            String key = (String)enu.nextElement();
            String value = resourcebundle.getString(key);
            convert.put(value, key);
        }

        return convert;
    }
}
