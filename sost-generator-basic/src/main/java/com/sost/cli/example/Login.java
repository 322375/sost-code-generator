package com.sost.cli.example;

import com.sost.cli.tools.CheckAndFillArgs;
import com.sost.cli.tools.MustInput;
import picocli.CommandLine;

import java.util.concurrent.Callable;

public class Login implements Callable<Integer> {

    @CommandLine.Option(names = {"-u", "--user"}, description = "User name")
    String user;

    @CommandLine.Option(names = {"-p", "--password"}, description = "Password", interactive = true, arity = "0..1")
    @MustInput
    String password;

    @CommandLine.Option(names = {"-cp", "--checkPassword"}, description = "Check password", interactive = true, arity = "0..1")
    @MustInput
    String checkPassword;

    @Override
    public Integer call() throws Exception {
        System.out.println("password=" + password);
        System.out.println("checkPassword=" + checkPassword);
        return 0;
    }

    public static void main(String[] args) {
        String[] strings = CheckAndFillArgs.checkAndFillArgs(Login.class, args);
        new CommandLine(new Login()).execute(strings);
    }
}
