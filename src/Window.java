import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends JFrame implements ActionListener {
    //设置窗口长宽
    final int length = 500, width = 500;
    JTextField showEnglishFilePath;
    JButton translationFileSelectButton, StartTransltingteButton;
    JLabel translationFilePathName;

    //构造方法
    public Window() {
        super("MCBE投影材料统计翻译器");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        windowInit();
    }

    private void windowInit() {
        //System.out.println("Window.init");
        //设置窗口长宽
        setSize(width, length);
        //设置布局
        setLayout(new FlowLayout());
        //
        translationFilePathName = new JLabel("翻译文件路径");
        //路径显示文本框
        showEnglishFilePath = new JTextField(null, 25);
        //
        translationFileSelectButton = new JButton("选择翻译文件");
        StartTransltingteButton = new JButton("开始翻译");
        //添加监听事件
        translationFileSelectButton.addActionListener(this);
        StartTransltingteButton.addActionListener(this);
        //
        add(translationFilePathName);
        add(showEnglishFilePath);
        add(translationFileSelectButton);
        //
        add(StartTransltingteButton);
        //设置默认路径
        fileInit();
    }

    private void fileInit() {
        //System.out.println("Window.setDefaultPath");
        Dictionary dictionary = new Dictionary();
        dictionary.refreshNotInDictionaryFile();
        //
        Config config = new Config();
        String translationFilePath = config.getEnglishFilePath();
        if (translationFilePath != null) {
            showEnglishFilePath.setText(translationFilePath);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(translationFileSelectButton.getText())) {
            selectEnglishFile();
        }
        if (e.getActionCommand().equals(StartTransltingteButton.getText())) {
            StartTranslate();
        }
    }

    private void selectEnglishFile() {
        String currentDirectoryPath = showEnglishFilePath.getText();
        String EnglishFilePath = new PathSelector(currentDirectoryPath).getFilePath();
        //
        showEnglishFilePath.setText(EnglishFilePath);
        //刷新配置文件
        Config config = new Config();
        config.setEnglishFilePath(EnglishFilePath);
    }

    private void StartTranslate() {
        Translation translate = new Translation();
        String EnglishFilePath = showEnglishFilePath.getText();
        translate.startTranslating(EnglishFilePath);
        //刷新配置文件
        Config config = new Config();
        config.setEnglishFilePath(EnglishFilePath);
    }
}
