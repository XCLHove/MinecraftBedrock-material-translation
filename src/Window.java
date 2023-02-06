import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends JFrame implements ActionListener {
    //设置窗口长宽
    final int length = 500, width = 500;
    //文件路径
    String filePath = "c:", dictionaryPath = "c:";
    JTextField showFilePath, showDictionaryPath, showConfigPath;
    JButton fileSelect, dictionarySelect, selectConfig, start;
    JLabel filePathName, dictionaryPathName, configName;

    //构造方法
    public Window(String title) {
        //调用父类构造器设置标题
        super(title);

        //处理关闭窗口事件
        this.addWindowListener(new WindowAdapter() {
            //当窗口关闭时调用该处理方法
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void init() {
        //设置窗口长宽
        setSize(width, length);

        //设置布局
        setLayout(new FlowLayout());

        //
        filePathName = new JLabel("翻译文件路径");
        dictionaryPathName = new JLabel("字典文件路径");
        configName = new JLabel("配置文件路径");

        //路径显示文本框
        showFilePath = new JTextField(null, 25);
        showDictionaryPath = new JTextField(null, 25);
        showConfigPath = new JTextField(null, 25);

        //
        fileSelect = new JButton("选择翻译文件");
        dictionarySelect = new JButton("选择字典文件");
        start = new JButton("开始翻译");
        selectConfig = new JButton("选择配置文件");

        //添加监听事件
        fileSelect.addActionListener(this);
        dictionarySelect.addActionListener(this);
        start.addActionListener(this);
        selectConfig.addActionListener(this);

        //组件添加进窗口布局
//        add(configName);
//        add(showConfigPath);
//        add(selectConfig);

//        add(dictionaryPathName);
//        add(showDictionaryPath);
//        add(dictionarySelect);

        add(filePathName);
        add(showFilePath);
        add(fileSelect);

        add(start);

        //设置默认路径
        setDefaultPath();
    }

    public void setDefaultPath() {
        Config config;
        config = new Config();

        //获取配置文件中的路径
        if (config.getDictionaryPath() != null) {
            dictionaryPath = config.getDictionaryPath();
//            System.out.println(dictionaryPath);
        }
        if (config.getFilePath() != null) {
            filePath = config.getFilePath();
//            System.out.println(filePath);
        }

        //显示到文本域中
        showDictionaryPath.setText(dictionaryPath);
        showFilePath.setText(filePath);
        showConfigPath.setText(config.getConfigPath());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("选择翻译文件")) {
            FileSelect fp = new FileSelect();
            filePath = fp.select(filePath);
            if (filePath != null) {
                showFilePath.setText(filePath);
                //刷新配置文件
                Config config = new Config();
                config.setFilePath(filePath);
            }
        }
        if (e.getActionCommand().equals("选择字典文件")) {
            FileSelect dp = new FileSelect();
            dictionaryPath = dp.select(dictionaryPath);
            if (dictionaryPath != null) {
                showDictionaryPath.setText(dictionaryPath);
                //刷新配置文件
                Config config = new Config();
                config.setDictionaryPath(dictionaryPath);
            }
        }
        if (e.getActionCommand().equals("开始翻译")) {
            Translate translate = new Translate();
            filePath = showFilePath.getText();
            dictionaryPath = showDictionaryPath.getText();
            translate.startTranslate(dictionaryPath, filePath);
            ////刷新配置文件
            Config config = new Config();
            config.setFilePath(filePath);
        }
    }
}
