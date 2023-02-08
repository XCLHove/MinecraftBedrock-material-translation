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
    String filePath = "c:";
    JTextField showFilePath;
    JButton fileSelect, start;
    JLabel filePathName;
    Config config;

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
        //System.out.println("Window.init");
        //设置窗口长宽
        setSize(width, length);
        //设置布局
        setLayout(new FlowLayout());
        //
        filePathName = new JLabel("翻译文件路径");
        //路径显示文本框
        showFilePath = new JTextField(null, 25);
        //
        fileSelect = new JButton("选择翻译文件");
        start = new JButton("开始翻译");
        //添加监听事件
        fileSelect.addActionListener(this);
        start.addActionListener(this);
        //
        add(filePathName);
        add(showFilePath);
        add(fileSelect);
        //
        add(start);
        //设置默认路径
        setDefaultPath();
    }

    public void setDefaultPath() {
        //System.out.println("Window.setDefaultPath");
        config = new Config();
        //更新notInDictinary.txt文件(去重)
        Dictionary dictionary = new Dictionary();
        dictionary.refreshNotInDictionary();
        //获取配置文件中的路径
        filePath = config.getFilePath();
        //显示到文本域中
        if (filePath != null) {
            showFilePath.setText(filePath);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("选择翻译文件")) {
            FileSelect fp = new FileSelect();
            filePath = fp.select(filePath);
            if (filePath != null) {
                showFilePath.setText(filePath);
                //刷新配置文件
                config.setFilePath(filePath);
            }
        }
        if (e.getActionCommand().equals("开始翻译")) {
            //从文本域获取路径
            filePath = showFilePath.getText();
            //开始翻译filePath路径的文件
            Translate translate = new Translate();
            translate.startTranslate(filePath);
            ////刷新配置文件
            config.setFilePath(filePath);
        }
    }
}
