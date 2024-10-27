package com.sost.marker;

import com.sost.marker.cli.CommandExecutor;
import com.sost.marker.cli.command.GenerateCommand;
import com.sost.marker.cli.tools.CheckAndFillArgs;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String[] newArags = CheckAndFillArgs.checkAndFillArgs(GenerateCommand.class, args);
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(newArags);
    }
}