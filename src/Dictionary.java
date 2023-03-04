import java.io.*;
import java.util.HashMap;

public class Dictionary {
    public static String dictionaryFilePath = ".\\config\\dictionary.txt";
    public static String notInDictionaryFilePath = ".\\config\\notInDictionary.txt";
    private String EnglishChineseSeparator = ":";
    private HashMap<String, String> HashMap_dictionary = new HashMap<>();
    private HashMap<String, String> HashMap_notInDictionary = new HashMap<>();

    public Dictionary() {
        if (new File(dictionaryFilePath).exists()) {
            readDictionaryFile();
        } else {
            creatDictionaryFile();
        }
        if (new File(notInDictionaryFilePath).exists()) {
            readNotInDictionaryFile();
        } else {
            creatNotInDictionaryFile();
        }
    }

    public void readDictionaryFile() {
        String aline = "";
        BufferedReader bufferedReader_dictionary = null;
        try {
            bufferedReader_dictionary = new BufferedReader(new FileReader(dictionaryFilePath));
            //从EnglishToChineseDictionary.txt读取一行
            aline = bufferedReader_dictionary.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (aline != null) {
            //判断读取的内容正确与否
            if (aline.indexOf(":", 1) == -1) {
                try {
                    //内容不正确则读取下一行
                    aline = bufferedReader_dictionary.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                continue;
            }
            //分割读取到的一行内容
            String[] array_splitALine = aline.split(EnglishChineseSeparator);
            String EnglishName = array_splitALine[0],
                    ChineseName = array_splitALine[1];
            //储存
            HashMap_dictionary.put(EnglishName, ChineseName);
            try {
                //读取下一行
                aline = bufferedReader_dictionary.readLine();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void readNotInDictionaryFile() {
        String aline = "";
        BufferedReader bufferedReader_notInDictionary = null;
        try {
            bufferedReader_notInDictionary = new BufferedReader(new FileReader(notInDictionaryFilePath));
            //读取一行
            aline = bufferedReader_notInDictionary.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (aline != null) {
            //判断读取的内容正确与否
            if (aline.indexOf(EnglishChineseSeparator, 1) == -1) {
                try {
                    //内容不正确则读取下一行
                    aline = bufferedReader_notInDictionary.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                continue;
            }
            //分割读取到的一行内容
            String[] array_splitALine = aline.split(EnglishChineseSeparator);
            String EnglishName = array_splitALine[0];
            //储存
            HashMap_notInDictionary.put(EnglishName, null);
            try {
                //读取下一行
                aline = bufferedReader_notInDictionary.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private boolean creatDictionaryFile() {
        //字典文件初始内容
        HashMap_dictionary.put("stained_glass", "染色玻璃");
        HashMap_dictionary.put("tinted_glass", "遮光玻璃");
        HashMap_dictionary.put("glass", "玻璃");
        HashMap_dictionary.put("unpowered_repeater", "中继器");
        HashMap_dictionary.put("powered_repeater", "中继器");
        HashMap_dictionary.put("unpowered_comparator", "比较器");
        HashMap_dictionary.put("powered_comparator", "比较器");
        HashMap_dictionary.put("redstone_wire", "红石粉");
        HashMap_dictionary.put("unlit_redstone_torch", "红石火把");
        HashMap_dictionary.put("redstone_torch", "红石火把");
        HashMap_dictionary.put("redstone_lamp", "红石灯");
        HashMap_dictionary.put("lit_redstone_lamp", "红石灯");
        HashMap_dictionary.put("redstone_block", "红石块");
        HashMap_dictionary.put("sticky_piston", "粘性活塞");
        HashMap_dictionary.put("sticky_piston_arm_collision", "粘性活塞");
        HashMap_dictionary.put("piston", "活塞");
        HashMap_dictionary.put("hopper", "漏斗");
        HashMap_dictionary.put("observer", "侦测器");
        HashMap_dictionary.put("wall_sign", "告示牌");
        HashMap_dictionary.put("heavy_weighted_pressure_plate", "压力板");
        HashMap_dictionary.put("quartz_block", "石英块");
        HashMap_dictionary.put("dirt", "泥土块");
        HashMap_dictionary.put("dispenser", "发射器");
        HashMap_dictionary.put("dropper", "投掷器");
        HashMap_dictionary.put("stone_block_slab", "平滑石半砖");
        HashMap_dictionary.put("lever", "拉杆");
        HashMap_dictionary.put("air", "空气");
        HashMap_dictionary.put("structure_block", "结构方块");
        HashMap_dictionary.put("quartz_stairs", "石英楼梯");
        HashMap_dictionary.put("prismarine", "海晶石砖");
        HashMap_dictionary.put("sea_lantern", "海晶灯");
        HashMap_dictionary.put("barrel", "木桶");
        HashMap_dictionary.put("stone_button", "石质按钮");
        HashMap_dictionary.put("end_rod", "末地烛");
        HashMap_dictionary.put("target", "标靶");
        HashMap_dictionary.put("chest", "箱子");
        HashMap_dictionary.put("blue_ice", "蓝冰");
        HashMap_dictionary.put("cobblestone_wall", "石墙");
        HashMap_dictionary.put("water", "水(包括流动)");
        HashMap_dictionary.put("concrete", "混凝土");
        HashMap_dictionary.put("soul_sand", "灵魂沙");
        HashMap_dictionary.put("bubble_column", "气泡柱");
        HashMap_dictionary.put("honey_block", "蜂蜜块");
        HashMap_dictionary.put("double_stone_block_slab", "台阶");
        HashMap_dictionary.put("shulker_box", "潜影盒");
        HashMap_dictionary.put("diamond_block", "钻石块");
        HashMap_dictionary.put("spruce_button", "云杉按钮");
        HashMap_dictionary.put("beacon", "信标");
        HashMap_dictionary.put("stained_glass_pane", "彩色玻璃板");
        HashMap_dictionary.put("standing_sign", "告示牌");
        HashMap_dictionary.put("piston_arm_collision", "活塞臂");
        HashMap_dictionary.put("birch_stairs", "桦木楼梯");
        HashMap_dictionary.put("lectern", "讲台");
        HashMap_dictionary.put("wall_banner", "旗帜");
        HashMap_dictionary.put("obsidian", "黑曜石");
        HashMap_dictionary.put("golden_rail", "动力铁轨");
        HashMap_dictionary.put("rail", "普通铁轨");
        HashMap_dictionary.put("wooden_button", "木制按钮");
        HashMap_dictionary.put("cake", "蛋糕");
        HashMap_dictionary.put("detector_rail", "侦测铁轨");
        HashMap_dictionary.put("brewing_stand", "酿造台");
        HashMap_dictionary.put("undyed_shulker_box", "潜影盒");
        HashMap_dictionary.put("slime", "黏液块");
        HashMap_dictionary.put("bell", "钟");
        HashMap_dictionary.put("moving_block", "活塞");
        HashMap_dictionary.put("reeds", "甘蔗");
        HashMap_dictionary.put("normal_stone_stairs", "石质楼梯");
        HashMap_dictionary.put("yellow_glazed_terracotta", "黄色带釉陶瓦");
        HashMap_dictionary.put("packed_ice", "浮冰");
        //创建文件
        boolean creatDictionaryFileSuccess = creatFile(dictionaryFilePath);
        //刷新文件的内容
        boolean refreshDictionaryFileSuccess = refreshDictionaryFile();
        return creatDictionaryFileSuccess && refreshDictionaryFileSuccess;
    }

    private boolean creatNotInDictionaryFile() {
        //创建文件
        boolean creatNotInDictionaryFileSuccess = creatFile(notInDictionaryFilePath);
        //刷新文件的内容
        boolean refreshNotInDictionaryFileSuccess = refreshNotInDictionaryFile();
        return creatNotInDictionaryFileSuccess && refreshNotInDictionaryFileSuccess;
    }

    private boolean deleteDictionaryFile() {
        return deleteFile(dictionaryFilePath);
    }

    private boolean deleteNotInDictionaryFile() {
        return deleteFile(notInDictionaryFilePath);
    }

    public boolean creatFile(String filePath) {
        File file = new File(filePath);
        //创建文件父目录
        file.getParentFile().mkdirs();
        boolean creatFileSuccess = false;
        try {
            //创建文件
            creatFileSuccess = file.createNewFile();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return creatFileSuccess;
    }

    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return file.delete();
    }
    public boolean refreshDictionaryFile() {
        try {
            BufferedWriter bufferedWriter_Dictionary = new BufferedWriter(new FileWriter(dictionaryFilePath));
            String writeALine;
            for (String EnglishName : HashMap_dictionary.keySet()) {
                String ChineseName = HashMap_dictionary.get(EnglishName);
                writeALine = EnglishName + EnglishChineseSeparator + ChineseName + "\n";
                //写入一行内容
                bufferedWriter_Dictionary.write(writeALine);
                bufferedWriter_Dictionary.flush();
            }
            bufferedWriter_Dictionary.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean refreshNotInDictionaryFile() {
        try {
            BufferedWriter bufferedWriter_notInDictionary = new BufferedWriter(new FileWriter(notInDictionaryFilePath));
            String writeALine;
            for (String EnglishName : HashMap_notInDictionary.keySet()) {
                if (HashMap_dictionary.containsKey(EnglishName)) {
                    continue;
                }
                writeALine = EnglishName + EnglishChineseSeparator + "\n";
                //写入一行内容
                bufferedWriter_notInDictionary.write(writeALine);
                bufferedWriter_notInDictionary.flush();
            }
            bufferedWriter_notInDictionary.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public String addDictionary(String EnglishName, String ChineseName) {
        return HashMap_dictionary.put(EnglishName, ChineseName);
    }

    public String addNotInDictionary(String EnglishName) {
        return HashMap_notInDictionary.put(EnglishName, null);
    }

    public HashMap<String, String> getDictionary() {
        return HashMap_dictionary;
    }
}
