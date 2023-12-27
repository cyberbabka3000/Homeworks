import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введите текст: ");
            String text = scanner.nextLine();

            System.out.println("Введите букву для поиска слов: ");
            char startLetter = scanner.next().charAt(0);

            findAndPrintWords(text, startLetter);
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public static void findAndPrintWords(String text, char startLetter) throws Exception {
        // Регулярное выражение для поиска слов, начинающихся с заданной буквы
        String regex = "\\b" + startLetter + "\\w*\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        // Поиск и вывод слов
        System.out.println("Найденные слова:");
        while (matcher.find()) {
            String word = matcher.group();
            System.out.println(word);
        }
    }
}