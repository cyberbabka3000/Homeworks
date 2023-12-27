import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindNumbersInText {
    public static void main(String[] args) {
        String text = "В тексте 42 числа, а также 3.14 и -7.5.";

        try {
            findAndPrintNumbers(text);
        } catch (NumberFormatException e) {
            System.err.println("Ошибка при преобразовании строки в число: " + e.getMessage());
        }
    }

    public static void findAndPrintNumbers(String text) throws NumberFormatException {
        // Регулярное выражение для поиска чисел (целых и с плавающей запятой)
        String regex = "[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        // Поиск и вывод чисел
        while (matcher.find()) {
            String numberStr = matcher.group();
            try {
                double number = Double.parseDouble(numberStr);
                System.out.println("Найдено число: " + number);
            } catch (NumberFormatException e) {
                // Обработка ошибки, если не удается преобразовать строку в число
                throw new NumberFormatException("Ошибка в числе \"" + numberStr + "\": " + e.getMessage());
            }
        }
    }
}