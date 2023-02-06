import javax.swing.*;
import java.io.*;
import java.util.HashMap;

public class Config {
    String filePath,
            dictionaryPath = ".\\config\\dictionary.txt",
            configPath = ".\\config\\config.txt",
            separator = "=";
    HashMap<String, String> config;

    public Config() {
        //配置文件初始化
        configInit();
        //读取配置文件
        readConfig();
    }

    public void configInit() {
        File config = new File(configPath);
        //配置文件不存在
        if (!config.exists()) {
            System.out.println("!config.exists()");
            try {
                //创建配置文件
                config.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //
            FileWrite filewrite = new FileWrite();
            //配置文件初始内容
            String configInit = "filePath=c:\n" + "dictionaryPath=.\\config\\config.txt\n";
            //初始内容写入配置文件
            filewrite.write(configInit, configPath);
        }
    }

    public int readConfig() {
        config = new HashMap<>();
        String line = "";
        FileReader fr;
        BufferedReader br;
        try {
            fr = new FileReader(configPath);
            br = new BufferedReader(fr);

            //读取一行的内容并储存到line变量
            line = br.readLine();

        } catch (IOException e) {
            return 1;
        }
        while (line != null) {
            String[] temp;

            // 分割字符串并储存到temp数组
            temp = line.split(separator);
            if (!temp[0].equals("filePath") && !temp[0].equals("dictionaryPath")) {
                JOptionPane.showMessageDialog(null, "配置文件错误！请选择正确的配置文件。");
                return 1;
            }
            //将读取到的内容放入键值对
            config.put(temp[0], temp[1]);

            try {
                //读取下一行的内容
                line = br.readLine();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
