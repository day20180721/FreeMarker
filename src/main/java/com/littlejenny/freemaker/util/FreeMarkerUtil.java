package com.littlejenny.freemaker.util;

import com.littlejenny.freemaker.FreemakerApplication;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 王洪棟 - Lin
 * @created date 2023/11/15
 */
public class FreeMarkerUtil {
    private static final Configuration configuration;

    static {
        configuration = new Configuration(Configuration.VERSION_2_3_26);
        configuration.setClassForTemplateLoading(FreemakerApplication.class, "/templates");
        configuration.setDefaultEncoding("UTF-8");
    }

    public static String getResult(Map<String, Object> param, String templateName) throws IOException, TemplateException {
        Template template = configuration.getTemplate(templateName + ".ftlh");
        try (StringWriter stringWriter = new StringWriter()) {
            template.process(param, stringWriter);
            return stringWriter.toString();
        } catch (Exception e) {
            throw e;
        }
    }

    public static FreeMarkerParamFactory param() {
        return new FreeMarkerParamFactory();
    }

    public static class FreeMarkerParamFactory {
        private Map<String,Object> map = new HashMap<>();
        public FreeMarkerParamFactory put(String name,Object object){
            map.put(name,object);
            return this;
        }
        public Map<String,Object> build(){
            return map;
        }
    }
}
