import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWrite {
    public FileWrite() {
    }

    public void write(String writeStr, String filePath) {
//        //System.out.println("write:" + "(" + writeStr + ")" + "--->" + "(" + filePath + ")");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(writeStr);
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
