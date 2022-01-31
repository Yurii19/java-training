import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {

    public static void writeToFile(String logEntry) {
        try( FileWriter myWriter = new FileWriter("log.txt",true)) {

            myWriter.write(logEntry+ "|+|");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
