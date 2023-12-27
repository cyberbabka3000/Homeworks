import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HyperlinkReplacer {
    public static void main(String[] args) {
        String text = "Пример текста с ссылкой: http://www.example.com и еще одной: www.test.com";

        try {
            String processedText = replaceLinks(text);
            System.out.println(processedText);
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    public static String replaceLinks(String text) throws Exception {
        // Регулярное выражение для поиска ссылок
        String regex = "\\b(?:https?://|www\\.)\\S+\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        // Замена найденных ссылок на гиперссылки
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String link = matcher.group();
            String hyperlink = "<a href=\"" + link + "\">" + link + "</a>";
            matcher.appendReplacement(result, hyperlink);
        }
        matcher.appendTail(result);

        return result.toString();
    }
}