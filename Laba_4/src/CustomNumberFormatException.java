import java.io.FileWriter;
import java.io.IOException;

public class CustomNumberFormatException extends NumberFormatException {
    public CustomNumberFormatException(String message) {
        super(message);
    }

    public void logException(String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write("CustomNumberFormatException: " + getMessage() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}