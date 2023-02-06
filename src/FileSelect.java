import javax.swing.*;

public class FileSelect {
    String filePath = "c:";
    public FileSelect() {
    }

    public String select() {
        JFileChooser jfchooser = new JFileChooser(filePath);
        int option = jfchooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {//说明选定了一个文件
            //把文件地址取出给filePath变量
            filePath = jfchooser.getSelectedFile().getAbsolutePath();
            return filePath;
        }
        return null;
    }

    public String select(String openPath) {
        filePath = openPath;
        JFileChooser jfchooser = new JFileChooser(filePath);
        int option = jfchooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {//说明选定了一个文件
            //把文件地址取出给filePath变量
            filePath = jfchooser.getSelectedFile().getAbsolutePath();
        }
        return filePath;
    }
}
