package com.sost.cli.tools;

import cn.hutool.core.util.ArrayUtil;
import picocli.CommandLine;

import java.lang.reflect.Field;

/**
 * 命令行参数检查填充工具类
 * @author 32237
 */
public class CheckAndFillArgs {
    /**
     * 遍历字段，检查命令行输入参数，并自动填写缺失必填参数
     * @param mainClass
     * @param args
     * @return
     */
    public static String[] checkAndFillArgs(Class mainClass, String[] args) {
        Field[] fields = mainClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(MustInput.class) && field.isAnnotationPresent(CommandLine.Option.class)) {
                String[] optionArgs = field.getAnnotation(CommandLine.Option.class).names();
                boolean shouldAddArgs = true;

                for (String optionArg : optionArgs) {
                    if (containsArg(args, optionArg)) {
                        shouldAddArgs = false;
                    }
                }

                if (!shouldAddArgs) {
                    continue;
                }


                args = ArrayUtil.addAll(args, new String[]{optionArgs[0]});
            }
        }
        return args;
    }

    /**
     * 检查命令行参数是否包含相关参数
     * @param args
     * @param optionArg
     * @return
     */
    private static boolean containsArg(String[] args, String optionArg) {
        for (String arg : args) {
            if (arg.equals(optionArg) || arg.startsWith(optionArg + "=")) {
                return true;
            }
        }
        return false;
    }
}
