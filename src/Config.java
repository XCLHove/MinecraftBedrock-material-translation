import javax.swing.*;
import java.io.*;
import java.util.HashMap;

public class Config {
    String filePath = "c:",
            dictionaryPath = ".\\config\\dictionary.txt",
            configPath = ".\\config\\config.txt",
            separator = "=";
    HashMap<String, String> config;

    public Config() {
        //读取配置文件
        readConfig();
    }

    public void configInit() {
        config.put("filePath","c:");
        config.put("dictionaryPath", ".\\config\\translationDictionary.txt");
        refreshConfiguration();
    }

    public int readConfig() {
        config = new HashMap<>();
        String line = "";
        FileReader fr;
        BufferedReader br;
        File configFile = new File(configPath);
        //配置文件不存在
        if (!configFile.exists()) {
            configInit();
            return 0;
        }
        //读取一行的内容并储存到line变量
        try {
            fr = new FileReader(configPath);
            br = new BufferedReader(fr);
            line = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //配置文件为空
        if (line == null) {
            configInit();
        }
        //分割字符串line并储存到temp数组
        boolean fp=false, dp=false;
        while (line != null) {
            String[] temp;
            temp = line.split(separator);
            //判断配置文件的内容正确与否
            if (temp.length != 2) {
                break;
            }
            if (temp[0].equals("filePath")) {
                fp=true;
            }
            if (temp[0].equals("dictionaryPath")) {
                dp=true;
            }
            try {
                line = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //将读取到的内容放入键值对
            config.put(temp[0], temp[1]);
        }
        if (!(fp && dp)) {
            configInit();
            readConfig();
        }
        return 0;
    }

    public String getFilePath() {
        filePath = config.get("filePath");
        return filePath;
    }

    public String getDictionaryPath() {
        dictionaryPath = config.get("dictionaryPath");
        return dictionaryPath;
    }

    public String getConfigPath() {
        return configPath;
    }

    public void setFilePath(String filePath) {
        config.put("filePath", filePath);
        refreshConfiguration();
    }

    public void setDictionaryPath(String dictionaryPath) {
        config.put("dictionaryPath", dictionaryPath);
        refreshConfiguration();
    }

    public void refreshConfiguration() {
        String writeAll = "";
        for (String pathName : config.keySet()) {
            String path = config.get(pathName);
            writeAll += pathName + "=" + path + "\n";
        }
        //写入新配置
        FileWrite fileWrite = new FileWrite();
        fileWrite.write(writeAll, configPath);
    }
}
