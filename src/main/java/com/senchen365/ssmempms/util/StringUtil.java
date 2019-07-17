package com.senchen365.ssmempms.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类名：StringUtil <br>
 * 作者：申殿青 <br>
 * 日期： 2019/6/5 <br>
 * 功能：TODO <br>
 * 版本：1.0.0 <br>
 * 历史纪录：  <br>
 */
@Component
public class StringUtil implements Converter<String,Date>{
    public static SimpleDateFormat SDF=new SimpleDateFormat("yyyy-MM-dd ");
    @Override
    public Date convert(String s) {
        Date date=null;
        try {
            date=SDF.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
