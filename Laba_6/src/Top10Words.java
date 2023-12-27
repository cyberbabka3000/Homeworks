import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Top10Words {
    public static void main(String[] args) {
// Указываем путь к текстовому файлу
        String filePath = "C:\\Users\\asus\\Desktop\\Лабы по ИТиП\\Laba_6\\text";
        // Создаем объект Map для хранения слов и их количества в файле
        Map<String, Integer> wordCountMap = new HashMap<>();

        try {
            // Создаем объект Scanner для чтения файла
            Scanner scanner = new Scanner(new File(filePath));

            // Пока в файле есть следующее слово
            while (scanner.hasNext()) {
                // Читаем очередное слово
                String word = scanner.next().toLowerCase();

                // Проверяем, что слово состоит только из букв и цифр
                if (word.matches("[a-zA-Zа-яА-Я0-9]+")) {
                    // Если слово уже есть в Map, увеличиваем его количество на 1
                    if (wordCountMap.containsKey(word)) {
                        int count = wordCountMap.get(word);
                        wordCountMap.put(word, count + 1);
                    } else {
                        // Если слова еще нет в Map, добавляем его и устанавливаем количество 1
                        wordCountMap.put(word, 1);
                    }
                }
            }

            // Закрываем Scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Создаем список из пар (слово, количество) для сортировки по количеству повторений
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(wordCountMap.entrySet());

        // Сортируем список в порядке убывания количества повторений слов
        entryList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // Выводим топ-10 самых часто встречающихся слов
        for (int i = 0; i < 10 && i < entryList.size(); i++) {
            Map.Entry<String, Integer> entry = entryList.get(i);
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}