package com.littlejenny.freemaker.conf;

import com.littlejenny.freemaker.FreemakerApplication;
import freemarker.template.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@org.springframework.context.annotation.Configuration
public class FreeMarkerConfiguration {
    @Bean
    public Configuration  freeMarkerConfigurer() {
        //初始化freemarker設定
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
        //指定樣版所在位置 (這裡是classpath的templates資料夾底下)
        configuration.setClassForTemplateLoading(FreemakerApplication.class, "/templates");
        configuration.setDefaultEncoding("UTF-8");
        return configuration;
    }
}
