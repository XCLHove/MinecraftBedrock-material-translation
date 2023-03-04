import java.io.*;
import java.util.HashMap;

public class Config {
    private final String nameValueSeparator = "=";
    private final String configFilePath = ".\\config\\config.txt";
    private final static String DEFAULT_PATH = "c:";
    private final String EnglishFilePathName = "EnglishFilePath";
    HashMap<String, String> HashMap_Config;

    public Config() {
        HashMap_Config = new HashMap<>();
        if (!new File(configFilePath).exists()) {
            creatConfigFile();
        }
        else {
            readConfig();
        }
    }

    public void creatConfigFile() {
        //添加初始内容
        HashMap_Config.put(EnglishFilePathName, DEFAULT_PATH);

        File configFile = new File(configFilePath);
        configFile.getParentFile().mkdirs();
        try {
            configFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        refreshConfigFile();
    }

    public void readConfig() {
        BufferedReader bufferedReader_config;
        String aLine;
        try {
            bufferedReader_config = new BufferedReader(new FileReader(configFilePath));
            aLine = bufferedReader_config.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
         while (aLine != null) {
             String[] array_splitALine = aLine.split(nameValueSeparator);
             String configName = array_splitALine[0],
                     configValue = array_splitALine[1];
             HashMap_Config.put(configName, configValue);
             try {
                 aLine = bufferedReader_config.readLine();
             } catch (IOException e) {
                 throw new RuntimeException(e);
             }
         }
    }

    public void refreshConfigFile(){
        BufferedWriter bufferedWriter_configFile = null;
        try {
            bufferedWriter_configFile = new BufferedWriter(new FileWriter(configFilePath));
            for (String configName : HashMap_Config.keySet()) {
                String configValue = HashMap_Config.get(configName);
                String writeLine = configName + nameValueSeparator + configValue + "\n";
                bufferedWriter_configFile.write(writeLine);
                bufferedWriter_configFile.flush();
            }
            bufferedWriter_configFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String setEnglishFilePath(String EnglishFilePath) {
        String setSuccess =  HashMap_Config.put(EnglishFilePathName, EnglishFilePath);
        refreshConfigFile();
        return setSuccess;
    }

    public String getEnglishFilePath() {
        if (!HashMap_Config.containsKey(EnglishFilePathName)) {
            reBuildConfig();
        }
        return HashMap_Config.get(EnglishFilePathName);
    }

    private void reBuildConfig() {
        deleteConfig();
        creatConfigFile();
    }

    private void deleteConfig() {
        File configFile = new File(configFilePath);
        configFile.delete();
    }
}
