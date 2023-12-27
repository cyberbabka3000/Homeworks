public class Main {
    public static void main(String[] args) {
        String input = "123"; // Пример строки, которую нужно преобразовать в число

        try {
            int number = parseAndConvert(input);
            System.out.println("Successfully converted to number: " + number);
        } catch (CustomNumberFormatException e) {
            System.err.println("CustomNumberFormatException: " + e.getMessage());

            // Логирование исключения в текстовый файл
            logException(e);
        }
    }

    // Метод для парсинга строки и преобразования в число
    public static int parseAndConvert(String input) throws CustomNumberFormatException {
        try {
            return Integer.parseInt(input); // Попытка преобразования строки в число
        } catch (NumberFormatException e) {
            throw new CustomNumberFormatException("Input is not a valid number: " + input);
            // В случае неудачи выбрасываем пользовательское исключение CustomNumberFormatException
        }
    }

    // Метод для логирования исключения в текстовый файл (пустая реализация)
    public static void logException(Exception e) {
        // Здесь вы можете реализовать логирование исключения в текстовый файл
        // Например, используя java.util.logging или другую библиотеку для логирования
    }
}