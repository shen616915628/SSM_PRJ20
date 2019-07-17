package com.senchen365.ssmempms.web.controller;

import com.senchen365.ssmempms.util.StringUtil;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 类名：WebController <br>
 * 作者：申殿青 <br>
 * 日期： 2019/6/5 <br>
 * 功能：TODO <br>
 * 版本：1.0.0 <br>
 * 历史纪录：  <br>
 */
@ComponentScan(basePackages = "com.senchen365.ssmempms")
@EnableWebMvc
@Configuration
public class WebController extends WebMvcConfigurerAdapter{
    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
        registry.addConverter(new StringUtil());
    }
}
