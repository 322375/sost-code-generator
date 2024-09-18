package com.sost.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @author yishui
 */
public class StaticGenerator {
    public static void copyFilesByRecursive(String inputPath, String outputPath) {
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            copyFileByRecursive(inputFile, outputFile);
        } catch (Exception e) {
            System.err.println("文件复制失败");
            e.printStackTrace();
        }
    }

    /**
     * 通过递归遍历文件夹，将文件复制到目标文件夹
     * 1. source: 文件, target: 文件: 覆盖文件
     * 2. source: 文件夹, target: 文件夹: 递归复制文件夹，把文件夹放在目录下
     * 3. source: 文件夹, target: 文件: 报错
     * 4. source: 文件, target: 文件夹: 递归复制文件夹，把文件放在目录下
     * @param inputFile
     * @param outputFile
     */
    private static void copyFileByRecursive(File inputFile, File outputFile) {
        boolean allNotEmpty = ObjectUtil.isAllNotEmpty(inputFile, outputFile);
        if (!allNotEmpty) {
            return;
        }
        if (inputFile.isDirectory()) {
            if (!outputFile.exists()) {
                outputFile.mkdirs();
            }
            File[] files = inputFile.listFiles();
            if (ArrayUtil.isEmpty(files)) {
                return;
            }

            for (File file : files) {
                copyFileByRecursive(file, new File(outputFile, file.getName()));
            }

        } else {
            Path descPath = outputFile.toPath().resolve(inputFile.getName());
            FileUtil.copy(inputFile.toPath(), descPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
    /**
     * 通过 hutool 拷贝文件
     * @param inputPath
     * @param outputPath
     */
    public static void copyFileByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }
}
