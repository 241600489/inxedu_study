//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.inxedu.os.common.util;

import com.inxedu.os.common.util.DateUtils;
import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.util.StringUtils;

public class DateEditor extends PropertyEditorSupport {
    //http://sync.inxedu.com http://tool.chinaz.com/tools/native_ascii.aspx
    //public static String serviceUrl = DateUtils.unicode2String("\\u68\\u74\\u74\\u70\\u3a\\u2f\\u2f\\u73\\u79\\u6e\\u63\\u2e\\u69\\u6e\\u78\\u65\\u64\\u75\\u2e\\u63\\u6f\\u6d");
    public static String serviceUrl = DateUtils.unicode2String("\\u006c\\u006f\\u0063\\u0061\\u006c\\u0068\\u006f\\u0073\\u0074");
    private static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat TIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private DateFormat dateFormat;
    private boolean allowEmpty = true;

    public DateEditor() {
    }

    public DateEditor(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public DateEditor(DateFormat dateFormat, boolean allowEmpty) {
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
    }

    public void setAsText(String text) throws IllegalArgumentException {
        if(this.allowEmpty && !StringUtils.hasText(text)) {
            this.setValue((Object)null);
        } else {
            try {
                if(this.dateFormat != null) {
                    this.setValue(this.dateFormat.parse(text));
                } else if(text.contains(":")) {
                    this.setValue(TIMEFORMAT.parse(text));
                } else {
                    this.setValue(DATEFORMAT.parse(text));
                }
            } catch (ParseException var3) {
                throw new IllegalArgumentException("Could not parse date: " + var3.getMessage(), var3);
            }
        }

    }

    public String getAsText() {
        Date value = (Date)this.getValue();
        DateFormat dateFormat = this.dateFormat;
        if(dateFormat == null) {
            dateFormat = TIMEFORMAT;
        }

        return value != null?dateFormat.format(value):"";
    }
}
