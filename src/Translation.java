import javax.swing.*;
import java.io.*;
import java.util.HashMap;

class Translation {
    Dictionary dictionary;
    HashMap<String, String> HashMap_dictionary;
    HashMap<String, Integer> HashMap_translation;

    //构造方法
    public Translation() {
        dictionary = new Dictionary();
        HashMap_dictionary = dictionary.getDictionary();
        HashMap_translation = new HashMap<>();
    }

    //开始翻译
    public void startTranslating(String filePath) {
        BufferedReader bufferedReader_filePath = null;
        String aline = "";
        try {
            bufferedReader_filePath = new BufferedReader(new FileReader(filePath));
            aline = bufferedReader_filePath.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String writeALineToNotInDictionary = "";
        while (aline != null) {
            String[] array_splitALine;
            String EnglishChineseSeparator = ":";
            array_splitALine = aline.split(EnglishChineseSeparator);
            //检查读取内容
            if (array_splitALine.length != 2) {
                try {
                    //内容不正确则读取下一行
                    aline = bufferedReader_filePath.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    continue;
                }
            }
            String EnglishName = array_splitALine[0];
            String ChineseName = EnglishName;
            //
            if (HashMap_dictionary.get(EnglishName) == null) {
                dictionary.addNotInDictionary(EnglishName);
            } else {
                ChineseName = HashMap_dictionary.get(EnglishName);
            }
            //quantity储存数量
            int quantity = 0;

            try {
                quantity = Integer.parseInt(array_splitALine[1].substring(1));
            } catch (NumberFormatException e) {
                try {
                    //数量不正确则不处理
                    aline = bufferedReader_filePath.readLine();
                } catch (IOException IOe) {
                    throw new RuntimeException(e);
                } finally {
                    continue;
                }
            }
            //存在同种材料
            if (HashMap_translation.containsKey(ChineseName)) {
                //同类材料数量相加
                quantity += HashMap_translation.get(ChineseName);
            }
            //存入
            HashMap_translation.put(ChineseName, quantity);

            try {
                //读取下一行
                aline = bufferedReader_filePath.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //刷新NotInDictionary文件
        dictionary.refreshNotInDictionaryFile();
        //翻译完毕的内容写入新文件
        writeToNewFile(filePath);
    }

    private void writeToNewFile(String filePath) {
        String ChineseNameQuantitySeparator = ":";
        //给写入文件命名
        String[] array_splitFilePath = filePath.split("\\.");
        int array_splitFilePathLength = array_splitFilePath.length;
        String newFilePath = filePath.replace(array_splitFilePath[array_splitFilePathLength - 2], array_splitFilePath[array_splitFilePathLength - 2] + "(翻译)");
        try {
            BufferedWriter bufferedWriter_newFile = new BufferedWriter(new FileWriter(newFilePath));
            for (String ChineseName : HashMap_translation.keySet()) {
                int quantity = HashMap_translation.get(ChineseName);
                String writeALine = ChineseName + ChineseNameQuantitySeparator + quantity + "\n";
                bufferedWriter_newFile.write(writeALine);
                bufferedWriter_newFile.flush();
            }
            bufferedWriter_newFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JOptionPane.showMessageDialog(null, "翻译内容写入完毕,新文件路径：\n" + newFilePath);
    }
}
