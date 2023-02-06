import javax.swing.*;
import java.io.*;
import java.util.HashMap;

public class Translate {
    //储存翻译字典
    HashMap<String, String> dictionary;

    //分隔符
    final String separator = ":";

    //构造方法
    public Translate() {
    }

    //读取翻译字典
    public void readDictionary(String dictionaryPath) {
        dictionary = new HashMap<>();
        String line = null;
        FileReader fr;
        BufferedReader br = null;
        try {
            fr = new FileReader(dictionaryPath);
            br = new BufferedReader(fr);
            //读取一行的内容并储存到line变量
            line = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (line != null) {
            line.indexOf(":", 1);
            if (line.indexOf(":", 1) == -1) {
                try {
                    //读取下一行的内容
                    line = br.readLine();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                continue;
            }
            String[] temp;

            // 用“:”分割字符串并储存到temp数组
            temp = line.split(separator);

            //将读取到的内容放入键值对
            dictionary.put(temp[0], temp[1]);

            try {
                //读取下一行的内容
                line = br.readLine();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //开始翻译
    public void startTranslate(String dictionaryPath, String filePath) {
        //读取字典文件
        readDictionary(dictionaryPath);
        //
        HashMap<String, Integer> translation = new HashMap<>();
        String line = null;
        FileReader fr;
        BufferedReader br = null;
        try {
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            line = br.readLine();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "未找到待翻译文件！");
        } catch (IOException e) {
        }
        while (line != null) {
            String[] temp;
            // 用“:”分割字符串并储存到temp数组
            temp = line.split(separator);

            //处理第一行
            if (temp.length == 1) {
                try {
                    line = br.readLine();
                } catch (IOException e) {
                }
                continue;
            }

            //翻译并存入name变量
            String name = temp[0];
            if (dictionary.get(temp[0]) != null) {
                name = dictionary.get(temp[0]);
            }

            //count变量储存数量
            int count = Integer.parseInt(temp[1].substring(1));

            //存在同种材料
            if (translation.containsKey(name)) {
                //同类材料数量相加
                count += translation.get(name);
            }

            //存入translation
            translation.put(name, count);

            try {
                line = br.readLine();
            } catch (IOException e) {
            }
        }
        String translationAll = "";
        //读取translation的内容并存入字符串变量translationAll
        for (String name : translation.keySet()) {
            int count = translation.get(name);
            translationAll += name + ":" + count + "\n";
        }

        //给写入文件命名
        String newFile, temp[];
        temp = filePath.split("\\.");
        newFile = filePath.replace(temp[temp.length - 2], temp[temp.length - 2] + "(翻译)");

        //将translationAll的内容写入文件newFile
        FileWrite fileWrite = new FileWrite();
        fileWrite.write(translationAll, newFile);
        JOptionPane.showMessageDialog(null, "翻译完毕,新文件路径：\n" + newFile);
    }
}
