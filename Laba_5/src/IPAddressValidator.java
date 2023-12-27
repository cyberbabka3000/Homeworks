import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPAddressValidator {
    public static void main(String[] args) {
        String ipAddress = "192.168.0.1"; // Замените эту строку на ваш IP-адрес для проверки

        try {
            if (isValidIPAddress(ipAddress)) {
                System.out.println("IP-адрес корректен.");
            } else {
                System.out.println("IP-адрес некорректен.");
            }
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    public static boolean isValidIPAddress(String ipAddress) throws Exception {
        // Регулярное выражение для проверки IP-адреса
        String regex = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ipAddress);

        // Проверка соответствия IP-адреса заданным критериям
        if (matcher.matches()) {
            return true;
        } else {
            throw new Exception("Некорректный формат IP-адреса.");
        }
    }
}