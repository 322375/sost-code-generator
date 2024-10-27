package com.sost.marker.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.sost.marker.generator.File.DynamicFileGenerator;
import com.sost.marker.generator.File.StaticGenerator;
import com.sost.marker.generator.JarGenerator;
import com.sost.marker.meta.Meta;
import com.sost.marker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        Meta meta = MetaManager.getMetaObject();
        System.out.println(meta);

        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + File.separator + "generated" + File.separator + meta.getName();
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }

        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResourcePath = classPathResource.getAbsolutePath();

        String outputBasePackage = meta.getBasePackage();
        String outputBasePackagePath = StrUtil.join("/", StrUtil.split(outputBasePackage, "."));
        String outputBaseJavaPackagePath = outputPath + File.separator + "src\\main\\java" + File.separator + outputBasePackagePath;;


        String inputFilePath;
        String outputFilePath;

        inputFilePath = inputResourcePath + File.separator + "templates\\java\\model\\DataModel.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "\\model\\DataModel.java";
        if (!FileUtil.exist(outputFilePath)) {
            FileUtil.touch(outputFilePath);
        }
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.command.ConfigCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ConfigCommand.java";
        if (!FileUtil.exist(outputFilePath)) {
            FileUtil.touch(outputFilePath);
        }
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // cli.command.GenerateCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/GenerateCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/GenerateCommand.java";
        if (!FileUtil.exist(outputFilePath)) {
            FileUtil.touch(outputFilePath);
        }
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // cli.command.ListCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ListCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ListCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // cli.CommandExecutor
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/CommandExecutor.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // Main
        inputFilePath = inputResourcePath + File.separator + "templates/java/Main.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/Main.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // generator.DynamicGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/file/DynamicFileGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/file/DynamicFileGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // generator.MainGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/file/FileGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/file/FileGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // generator.StaticGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/file/StaticGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/file/StaticGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // cli.tools.CheckAndFillArgs.java.ftl
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/tools/CheckAndFillArgs.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/tools/CheckAndFillArgs.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // cli.tools.MustInput.java.ftl
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/tools/MustInput.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/tools/MustInput.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // pom.xml
        inputFilePath = inputResourcePath + File.separator + "templates/pom.xml.ftl";
        outputFilePath = outputPath + File.separator + "pom.xml";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // 构建 jar 包
        JarGenerator.doGenerate(outputPath);


        JarGenerator.doGenerate(outputPath);
        return;

    }
}
