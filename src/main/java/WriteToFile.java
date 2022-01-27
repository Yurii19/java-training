import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {

    public static void writeToFile(String logEntry) {
        try {
            FileWriter myWriter = new FileWriter("log.txt",true);
            myWriter.write(logEntry+ "|+|");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
