import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Пример использования DataManager

            // Создаем DataManager
            DataManager dataManager = new DataManager();

            // Регистрируем обработчик данных (фильтр)
            dataManager.registerDataProcessor(new DataFilter());

            // Загружаем данные из файла source.txt
            dataManager.loadData("src/resources/source.txt");

            // Обрабатываем данные параллельно с использованием Stream API
            dataManager.processData();

            // Сохраняем обработанные данные в новый файл destination.txt
            dataManager.saveData("src/resources/destination.txt");

            System.out.println("Обработка данных успешно завершена.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}