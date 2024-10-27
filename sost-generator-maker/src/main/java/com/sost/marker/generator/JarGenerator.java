package com.sost.marker.generator;

import java.io.*;

public class JarGenerator {
    public static void doGenerate(String projectPath) throws IOException, InterruptedException {
        // Windows
        String winMavenCommand = "mvn.cmd clean package -X -DskipTests=True";
        // Linux
        String linuxMavenCommand = "mvn clean package -DskipTests=True";
        String os = System.getProperty("os.name").toLowerCase();
        String mavenCommand = "";
        if (os.contains("win")) {
            mavenCommand = winMavenCommand;
        } else if (os.contains("linux")) {
            mavenCommand = linuxMavenCommand;
        }

        ProcessBuilder processBuilder = new ProcessBuilder(mavenCommand.split(" "));
        processBuilder.directory(new File(projectPath));

        Process process = processBuilder.start();

        // 读取命令的输出
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // 等待命令执行完成
        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("Maven命令执行成功");
        } else {
            System.out.println("Maven命令执行失败，错误码：" + exitCode);
        }
    }

    public static void main(String[] args) {
        try {
            doGenerate("C:\\Users\\32237\\myproject\\sost-generator\\sost-generator-maker\\generated\\acm-template-pro-generator");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
