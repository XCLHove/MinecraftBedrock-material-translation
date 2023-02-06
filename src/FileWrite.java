import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWrite {
    public FileWrite() {}
    public void write(String writeStr, String filePath) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(writeStr);
            bw.close();
        } catch (IOException e) {
        }
    }
}
