package com.sost.marker.generator.File;

import cn.hutool.core.io.FileUtil;
import com.sost.marker.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class FileGenerator {
    public static void doGenerate(Object model) throws TemplateException, IOException {
        String inputRootPath = "C:\\Users\\32237\\myproject\\sost-generator\\sost-generator-demo-project\\acm-template-pro";
        String outputRootPath = "C:\\Users\\32237\\myproject\\acm-template-pro";

        String inputPath;
        String outputPath;

        inputPath = inputRootPath + File.separator + "src/com/yupi/acm/MainTemplate.java.ftl";
        outputPath = outputRootPath + File.separator + "src/com/sost/acm/MainTemplate.java";
        if (!FileUtil.exist(outputPath)) {
            String outputDir = outputRootPath + File.separator + "src/com/sost/acm";
            FileUtil.mkdir(outputDir);
            FileUtil.touch(outputPath);
        }

        DynamicFileGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, ".gitignore").getAbsolutePath();
        outputPath = new File(outputRootPath, ".gitignore").getAbsolutePath();
        StaticGenerator.copyFileByHutool(inputPath, outputPath);

        inputPath = new File(inputRootPath, "README.md").getAbsolutePath();
        outputPath = new File(outputRootPath, "README.md").getAbsolutePath();
        StaticGenerator.copyFileByHutool(inputPath, outputPath);
    }

    public static void main(String[] args) throws TemplateException, IOException {
        DataModel dataModel = new DataModel();
        dataModel.setAuthor("yishui");
        dataModel.setLoop(false);
        dataModel.setOutputText("求和结果：");
        FileGenerator.doGenerate(dataModel);
    }
}
