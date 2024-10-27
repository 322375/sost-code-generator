package ${basePackage}.generator.file;

import cn.hutool.core.io.FileUtil;
import ${basePackage}.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class FileGenerator {
    public static void doGenerate(Object model) throws TemplateException, IOException {
        String inputRootPath = ${fileConfig.inputRootPath?c};
        String outputRootPath = ${fileConfig.outputRootPath?c};

        String inputPath;
        String outputPath;

        <#list fileConfig.fileInfo as fileInfo>
            inputPath = new File(inputRootPath, "${fileInfo.inputPath}").getAbsolutePath();
            outputPath = new File(outputRootPath, "${fileInfo.outputPath}").getAbsolutePath();
            <#if fileInfo.generateType == "static">
                StaticGenerator.copyFileByHutool(inputPath, outputPath);
            <#else>
                if (!FileUtil.exist(outputPath)) {
                    FileUtil.touch(outputPath);
                }
                DynamicFileGenerator.doGenerate(inputPath, outputPath, model);
            </#if>
        </#list>
    }
}
