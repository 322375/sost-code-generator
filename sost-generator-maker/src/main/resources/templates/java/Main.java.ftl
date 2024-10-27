package ${basePackage};

import ${basePackage}.cli.command.CommandExecutor;
import ${basePackage}.cli.command.GenerateCommand;
import ${basePackage}.cli.tools.CheckAndFillArgs;


public class Main {
    public static void main(String[] args) {
        String[] newArags = CheckAndFillArgs.checkAndFillArgs(GenerateCommand.class, args);
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(newArags);
    }
}