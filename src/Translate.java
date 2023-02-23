import javax.swing.*;
import java.io.*;
import java.util.HashMap;

public class Translate {
    //储存翻译字典
    HashMap<String, String> dictionaryHashMap;
    //分隔符
    String SEPARATOR = ":";
    Dictionary dictionary;

    //构造方法
    public Translate() {
        dictionary = new  Dictionary();
    }

    //读取翻译字典
    public void readDictionary() {
        //System.out.println("Translate.readDictionary");
        dictionaryHashMap = new HashMap<>();
        //获取翻译字典
        dictionaryHashMap = dictionary.getDictionary();
    }

    //开始翻译
    public void startTranslate(String filePath) {
        //System.out.println("Translate.startTranslate");
        //读取翻译字典
        readDictionary();
        //读取待翻译文件
        HashMap<String, Integer> translation = new HashMap<>();
        String line = "", notInDictinaryStr = "";
        FileReader fr;
        BufferedReader br = null;
        try {
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            line = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (line != null) {
            String[] temp;
            // 用“:”分割字符串并储存到temp数组
            temp = line.split(SEPARATOR);

            //处理异常
            if (temp.length != 2) {
                try {
                    line = br.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                finally {
                    continue;
                }
            }

            //翻译并存入name变量
            String name = temp[0];
            if (dictionaryHashMap.get(name) != null) {
                name = dictionaryHashMap.get(name);
            }
            else {
                notInDictinaryStr += name + ":" + "\n";
            }

            //count变量储存数量
            int count;
            try {
                count = Integer.parseInt(temp[1].substring(1));
            } catch (NumberFormatException e) {
                try {
                    line = br.readLine();
                } catch (IOException IOe) {
                    throw new RuntimeException(e);
                }
                finally {
                    continue;
                }
            }
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
        String translationStr = "";
        //读取translation的内容并存入字符串变量translationStr
        for (String name : translation.keySet()) {
            int count = translation.get(name);
            translationStr += name + ":" + count + "\n";
        }
        //给写入文件命名
        String newFile, temp[];
        temp = filePath.split("\\.");
        newFile = filePath.replace(temp[temp.length - 2], temp[temp.length - 2] + "(翻译)");

        //将translationAll的内容写入文件newFile
        FileWrite fileWrite = new FileWrite();
        fileWrite.write(translationStr, newFile);
        //将未翻译的内容notInDictinaryStr写入notInDictionary.txt文件
        dictionary.writeNotInDictionary(notInDictinaryStr);
        //
        JOptionPane.showMessageDialog(null, "翻译完毕,新文件路径：\n" + newFile);
    }
}
