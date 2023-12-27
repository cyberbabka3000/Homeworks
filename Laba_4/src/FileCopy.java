import java.io.*;

public class FileCopy {
    public static void main(String[] args) {
        String sourceFilePath = "source.txt"; // Путь к исходному файлу
        String targetFilePath = "target.txt"; // Путь к файлу, в который нужно скопировать содержимое
        try {
            copyFile(sourceFilePath, targetFilePath);
            System.out.println("Файл успешно скопирован.");
        } catch (IOException e) {
            System.err.println("Произошла ошибка при копировании файла: " + e.getMessage());
        }
    }

    // Метод для копирования содержимого из одного файла в другой
    public static void copyFile(String sourcePath, String targetPath) throws IOException {
        // Используем try-with-resources для автоматического закрытия потоков после использования
        try (InputStream inputStream = new FileInputStream(sourcePath);
             OutputStream outputStream = new FileOutputStream(targetPath)) {
            byte[] buffer = new byte[4096]; // Буфер для чтения и записи данных блоками по 4096 байт

            int bytesRead;

            // Чтение из исходного файла и запись в целевой файл
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}