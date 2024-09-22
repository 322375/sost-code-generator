package com.sost.generator;

import com.sost.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.imageio.IIOException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 动态文件生成
 */
public class DynamicGenerator {
    public static void main(String[] args) throws IOException, TemplateException {
       String projectPath = System.getProperty("user.dir");
       String inputPath = projectPath + File.separator + "src/main/resource/templates/MainTemplate.java.ftl";
       String outputPath = projectPath + File.separator + "MainTemplate.java";
       MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
       mainTemplateConfig.setAuthor("yishui");
       mainTemplateConfig.setLoop(false);
       mainTemplateConfig.setOutputText("求和结果：");
       doGenerate(inputPath, outputPath, mainTemplateConfig);
;    }

    public static void doGenerate(String inputPath, String outputPath, Object model) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);
        configuration.setDefaultEncoding("utf-8");

        // 创建模板对象
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);

        // 创建数据模型
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("yishui");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果：");

        Writer out = new FileWriter(outputPath);
        template.process(mainTemplateConfig, out);
        out.close();
    }
}
