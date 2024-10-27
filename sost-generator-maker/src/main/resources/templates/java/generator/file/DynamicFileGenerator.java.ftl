package ${basePackage}.generator.file;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 动态文件生成
 */
public class DynamicFileGenerator {

    public static void doGenerate(String inputPath, String outputPath, Object model) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);
        configuration.setDefaultEncoding("utf-8");

        // 创建模板对象
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);


        Writer out = new FileWriter(outputPath);
        template.process(model, out);
        out.close();
    }
}
