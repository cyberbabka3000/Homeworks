import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введите пароль: ");
            String password = scanner.nextLine();

            if (validatePassword(password)) {
                System.out.println("Пароль корректен.");
            } else {
                System.out.println("Пароль не соответствует требованиям.");
            }
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public static boolean validatePassword(String password) throws Exception {
        // Регулярное выражение для проверки пароля
        String regex = "^(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        // Проверка соответствия пароля заданным критериям
        if (matcher.matches()) {
            return true;
        } else {
            throw new Exception("Пароль не соответствует требованиям.");
        }
    }
}