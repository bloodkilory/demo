package com.example.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Created by Liwh on 2016/4/21.
 */
public final class FreemarkerUtils {

    /**
     * 不可实例化
     */
    private FreemarkerUtils() {
    }

    private static Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);

    static {

        configuration.setDefaultEncoding("UTF-8");
        configuration.setLocale(new Locale("zh_CN"));
        configuration.setTemplateUpdateDelayMilliseconds(0);
        configuration.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);
        configuration.setWhitespaceStripping(true);
        configuration.setClassicCompatible(true);
        configuration.setNumberFormat("0.######");
        configuration.setBooleanFormat("true,false");
        configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
        configuration.setDateFormat("yyyy-MM-dd");
        configuration.setTimeFormat("HH:mm:ss");
        configuration.setObjectWrapper(new BeansWrapper(Configuration.VERSION_2_3_23));
    }

    /**
     * 解析字符串模板
     *
     * @param template
     *            字符串模板
     * @param model
     *            数据
     * @return 解析后内容
     */
    public static String process(String template, Map<String, ?> model) throws IOException, TemplateException {
        return process(template, model, configuration);
    }

    /**
     * 解析字符串模板
     *
     * @param template
     *            字符串模板
     * @param model
     *            数据
     * @param configuration
     *            配置
     * @return 解析后内容
     */
    public static String process(String template, Map<String, ?> model, Configuration configuration) throws IOException, TemplateException {
        if (template == null) {
            return null;
        }
        if (configuration == null) {
            configuration = new Configuration(Configuration.VERSION_2_3_23);
        }
        StringWriter out = new StringWriter();
        new Template("template", new StringReader(template), configuration).process(model, out);
        return out.toString();
    }



}
