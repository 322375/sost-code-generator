package ${basePackage}.generator.file;

import cn.hutool.core.io.FileUtil;

/**
 * @author yishui
 */
public class StaticGenerator {
    /**
     * 通过 hutool 拷贝文件
     * @param inputPath
     * @param outputPath
     */
    public static void copyFileByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }
}
