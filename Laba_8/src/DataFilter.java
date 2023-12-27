import java.util.List;

public class DataFilter {
    @DataProcessor
    public static void filterData(List<String> data) {
        // Пример: оставляем только строки, содержащие слово "Java"
        data.removeIf(line -> !line.toLowerCase().contains("java"));
    }
}