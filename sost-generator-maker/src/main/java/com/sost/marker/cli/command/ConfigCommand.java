package com.sost.marker.cli.command;

import cn.hutool.core.util.ReflectUtil;
import com.sost.marker.model.DataModel;
import picocli.CommandLine;

import java.lang.reflect.Field;

@CommandLine.Command(name = "config", description = "查看参数信息", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("查看参数信息");

        Field[] fields = ReflectUtil.getFields(DataModel.class);
        for (Field field : fields) {
            System.out.printf("参数名称: %s, 参数类型: %s\n", field.getName(), field.getType());
            System.out.println("------------------------");
        }
    }
}
