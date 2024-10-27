package com.sost.marker.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.sost.marker.cli.tools.MustInput;
import com.sost.marker.generator.File.FileGenerator;
import com.sost.marker.model.DataModel;
import lombok.Data;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "generate", description = "生成代码", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {

    @CommandLine.Option(names = {"-l", "--loop"}, description = "是否循环", arity = "0..1", interactive = true, echo = true)
    private boolean loop;

    @CommandLine.Option(names = {"-a", "--author"}, description = "作者", arity = "0..1",interactive = true, echo = true)
    @MustInput
    private String author = "sost";

    @CommandLine.Option(names = {"-o", "--output"}, description = "输出文本", arity = "0..1", interactive = true, echo = true)
    @MustInput
    private String outputText = "sum = ";

    @Override
    public Integer call() throws Exception {
        DataModel dataModel = new DataModel();
        BeanUtil.copyProperties(this, dataModel);
        FileGenerator.doGenerate(dataModel);
        return 0;
    }
}
