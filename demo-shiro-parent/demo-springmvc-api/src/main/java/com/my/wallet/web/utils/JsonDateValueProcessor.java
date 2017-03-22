package com.my.wallet.web.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDateValueProcessor implements JsonValueProcessor {

	private String datePattern ="yyyy-MM-dd HH:mm:ss";  
    
	public JsonDateValueProcessor() {
        super();
    }

    
	public JsonDateValueProcessor(String format) {
        super();
        this.datePattern = format;
    }
      
	private Object process(Object value) {
        try {
            if (value instanceof Date) {
            	TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));
                SimpleDateFormat sdf = new SimpleDateFormat(datePattern, Locale.CHINA);
                return sdf.format((Date) value);
            }
            return value == null ? "" : value.toString();
        } catch (Exception e) {
            return "";
        }

    }


    public Object processArrayValue(Object value, JsonConfig config) {  
        return process(value);  
    }  
  
    public Object processObjectValue(String key, Object value, JsonConfig config) {  
        return process(value);  
    }  
    
}
