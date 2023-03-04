import javax.swing.*;

public class PathSelector {
    final private static String DEFAULT_PATH = "c:";
    private static String currentDirectoryPath = DEFAULT_PATH;
    private String filePath = DEFAULT_PATH;
    private String directoryPath = DEFAULT_PATH;

    public PathSelector() {
    }

    public PathSelector(String currentDirectoryPath) {
        PathSelector.currentDirectoryPath = currentDirectoryPath;
    }

    public String getFilePath() {
        selectFile();
        return filePath;
    }

    public String getFilePath(String currentDirectoryPath) {
        PathSelector.currentDirectoryPath = currentDirectoryPath;
        selectFile();
        return filePath;
    }
    public String getDirectoryPath() {
        selectDirectory();
        return directoryPath;
    }

    public String getDirectoryPath(String currentDirectoryPath) {
        PathSelector.currentDirectoryPath = currentDirectoryPath;
        selectDirectory();
        return directoryPath;
    }

    private void selectFile() {
        JFileChooser jFileChooser = new JFileChooser(currentDirectoryPath);
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int option = jFileChooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            filePath = jFileChooser.getSelectedFile().getAbsolutePath();
        }
    }

    private void selectDirectory() {
        JFileChooser jFileChooser = new JFileChooser(currentDirectoryPath);
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = jFileChooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            directoryPath = jFileChooser.getSelectedFile().getAbsolutePath();
        }
    }
}
