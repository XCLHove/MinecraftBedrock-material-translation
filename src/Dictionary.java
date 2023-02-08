import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Dictionary {
    String dictionaryPath = ".\\config\\translationDictionary.txt";
    String notInDictionaryPath = ".\\config\\notInDictinary.txt";
    String SEPARATOR = ":";
    private HashMap<String, String> dictionary;

    public Dictionary () {
        readDictionary();
    }

    public void readDictionary() {
        //System.out.println("Dictionary.readDictionary");
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
            temp = line.split(SEPARATOR);

            //将读取到的内容放入键值对
            dictionary.put(temp[0], temp[1]);

            try {
                //读取下一行的内容
                line = br.readLine();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
//        return dictionary;
    }

    public HashMap<String, String> getDictionary() {
        //System.out.println("Dictionary.getDictionary");
//        //System.out.println(dictionary);
        return dictionary;
    }

    public void refreshNotInDictionary() {
        //System.out.println("Dictionary.refreshNotInDictionary");
        String line = "", strAll = "";
        FileReader fr;
        BufferedReader br = null;
        HashMap<String, String> dictionary = new HashMap<>();
        dictionary = getDictionary();
        //notInDictinary.txt文件不存在
        if (!(new File(notInDictionaryPath).exists())) {
            try {
                //创建notInDictinary.txt文件
                new File(notInDictionaryPath).createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //读取notInDictinary.txt文件
        try {
            fr = new FileReader(notInDictionaryPath);
            br = new BufferedReader(fr);
            //读取一行的内容并储存到line变量
            line = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (line != null) {
            //notInDictinary.txt的内容在translationDictionary.txt不存在
            if (!dictionary.containsKey(line)) {
                //则把notInDictinary.txt的内容存入strAll
                line += ":" + "\n";
                strAll += line;
            }
            //读取下一行的内容
            try {
                line = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        FileWrite fileWrite = new FileWrite();
        fileWrite.write(strAll, notInDictionaryPath);
    }

    public void writeNotInDictionary(String writeStr) {
        //System.out.println("Dictionary.writeNotInDictionary");
        new FileWrite().write(writeStr, notInDictionaryPath);
    }
}
