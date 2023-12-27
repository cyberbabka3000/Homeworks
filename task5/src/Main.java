import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        //task 1
        System.out.println(sameLetterPattern("ABAB", "CDCD")); // true
        System.out.println(sameLetterPattern("ABCBA", "BCDCB")); // true
        System.out.println(sameLetterPattern("FFGG", "CDCD")); // false
        System.out.println(sameLetterPattern("FFFF", "ABCD")); // false
        //task 2
        System.out.println(spiderVsFly("H3", "E2")); // "H3-H2-H1-A0-E1-E2"
        System.out.println(spiderVsFly("A4", "B2")); // "A4-A3-A2-B2"
        System.out.println(spiderVsFly("A4", "C2")); // "A4-A3-A2-B2-C2"
        //task 3
        System.out.println(digitsCount(4666)); // 4
        System.out.println(digitsCount(544)); // 3
        System.out.println(digitsCount(121317)); // 6
        System.out.println(digitsCount(0)); // 1
        System.out.println(digitsCount(12345)); // 5
        System.out.println(digitsCount(1289396387328L)); // 13
        //task 4
        System.out.println(totalPoints(new String[]{"cat", "create", "sat"}, "caster")); // 2
        System.out.println(totalPoints(new String[]{"trance", "recant"}, "recant")); // 108
        System.out.println(totalPoints(new String[]{"dote", "dotes", "toes", "set", "dot", "dots", "sted"}, "tossed")); // 13
        //task 5
        System.out.println(sumsUp(new int[]{1, 2, 3, 4, 5})); // [[3, 5]]
        System.out.println(sumsUp(new int[]{1, 2, 3, 7, 9})); // [[1, 7]]
        System.out.println(sumsUp(new int[]{10, 9, 7, 2, 8})); // []
        System.out.println(sumsUp(new int[]{1, 6, 5, 4, 8, 2, 3, 7})); // [[2, 6], [3, 5], [1, 7]]
        //task6
        // Пример использования функции
        String[] classmatesScores = {"95%", "83%", "90%", "87%", "88%", "93%"};
        String result = takeDownAverage(classmatesScores);
        System.out.println(result);
        //task 7
        System.out.println(caesarCipher("encode", "hello world", 3)); // "KHOOR ZRUOG"
        System.out.println(caesarCipher("decode", "EPQSWX PEWX XEWO!", 4)); // "ALMOST LAST TASK!"
        //task 8
        System.out.println(setupCount(5, 3)); // 60
        System.out.println(setupCount(7, 3)); // 210
        //task 9
        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra")); // "2011-4-2 17:23"
        System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome")); // "1983-8-1 00:01"
        System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing")); // "1971-1-1 02:40"
        //task 10
        System.out.println(isNew(3));    // true
        System.out.println(isNew(13));   // true
        System.out.println(isNew(321));  // false
        System.out.println(isNew(123));  // true
    }

    //Task1
    static boolean sameLetterPattern(String str1, String str2) {
        // Проверка на разную длину строк
        if (str1.length() != str2.length()) {
            return false;
        }

        // Создание двух отображений для хранения соответствий букв
        Map<Character, Character> charMap1 = new HashMap<>();
        Map<Character, Character> charMap2 = new HashMap<>();

        // Перебор символов в строках
        for (int i = 0; i < str1.length(); i++) {
            char char1 = str1.charAt(i);
            char char2 = str2.charAt(i);

            // Проверка соответствия символов в обоих отображениях
            if (charMap1.containsKey(char1)) {
                // Если символ уже присутствует в отображении, проверяем его соответствие
                if (charMap1.get(char1) != char2) {
                    // Если символы не соответствуют, возвращаем false
                    return false;
                }
            } else {
                // Если символ ещё не присутствует в отображении, добавляем его
                charMap1.put(char1, char2);
            }

            if (charMap2.containsKey(char2)) {
                // Если символ уже присутствует в отображении, проверяем его соответствие
                if (charMap2.get(char2) != char1) {
                    // Если символы не соответствуют, возвращаем false
                    return false;
                }
            } else {
                // Если символ ещё не присутствует в отображении, добавляем его
                charMap2.put(char2, char1);
            }
        }

        // Если все символы соответствуют, возвращаем true
        return true;
    }

    //Task 2
    public static String spiderVsFly(String spider, String fly) {
        int spiderRing = Integer.parseInt(String.valueOf(spider.charAt(1))); //для получения значения кольца
        char spiderRadial = spider.charAt(0);
        int flyRing = Integer.parseInt(String.valueOf(fly.charAt(1))); //для радиала каждого объекта
        char flyRadial = fly.charAt(0);

        StringBuilder path = new StringBuilder();//объект StringBuilder под названием path, который будет содержать путь паука до мухи.
        boolean clockwise = true;
/* паук двигается по кольцам в зависимости от того, находится ли паук на меньшем или большем кольце по сравнению с мухой.
При каждом шаге позиция паука записывается в path, а затем паук перемещается на следующее кольцо.
*/
        while (spiderRing != flyRing) {
            if (spiderRing < flyRing) {
                path.append(spiderRadial);
                path.append(spiderRing);
                path.append("-");
                if (clockwise) {
                    spiderRing++;
                } else {
                    spiderRing--;
                }
            } else {
                path.append(spiderRadial);
                path.append(spiderRing);
                path.append("-");
                if (clockwise) {
                    spiderRing--;
                } else {
                    spiderRing++;
                }
            }
        }

        List<Character> radialList = new ArrayList<>();//создается список
        radialList.add(spiderRadial); //добавляется радиал паука
//в зависимости от направления движения паука (по или против часовой стрелки) в список добавляются все остальные радиалы,
// через которые паук должен пройти.
        if (clockwise) {
            for (char c = (char) (spiderRadial + 1); c <= 'H'; c++) {
                radialList.add(c);
            }
            for (char c = 'A'; c < flyRadial; c++) {
                radialList.add(c);
            }
        } else {
            for (char c = (char) (spiderRadial - 1); c >= 'A'; c--) {
                radialList.add(c);
            }
            for (char c = 'H'; c > flyRadial; c--) {
                radialList.add(c);
            }
        }

        for (char radial : radialList) {
            path.append(radial);
            path.append(flyRing);
            path.append("-");
        }

        path.append(fly);//добавляется в path

        return path.toString();
    }

    //Task 3
    static int digitsCount(long number) {
        // Проверка на случай нуля
        if (number == 0) {
            return 1;
        } else {
            // Вызываем вспомогательную рекурсивную функцию
            return countDigitsRecursive(number);
        }
    }

    static int countDigitsRecursive(long number) {
        // Базовый случай: число равно 0, возвращаем 0
        if (number == 0) {
            return 0;
        } else {
            // Рекурсивный случай: увеличиваем счетчик и вызываем функцию для оставшейся части числа
            return 1 + countDigitsRecursive(number / 10);
        }
    }

    //Task 4
    static int totalPoints(String[] words, String scrambledWord) {
        int totalPoints = 0;
        Map<Character, Integer> scrambledWordCount = countLetters(scrambledWord);

        // Перебираем слова в массиве
        for (String word : words) {
            Map<Character, Integer> wordCount = countLetters(word);

            // Проверяем, является ли текущее слово допустимым
            if (isValidWord(scrambledWordCount, wordCount)) {
                // Вычисляем баллы для текущего слова и добавляем их к общему количеству баллов
                int wordPoints = calculatePoints(word.length());
                totalPoints += wordPoints;
            }
        }

        return totalPoints;
    }

    static Map<Character, Integer> countLetters(String word) {
        Map<Character, Integer> letterCount = new HashMap<>();

        // Перебираем буквы в слове
        for (char letter : word.toCharArray()) {
            // Помещаем букву в карту, увеличивая счетчик или устанавливая его в 1, если буква встречается впервые
            letterCount.put(letter, letterCount.getOrDefault(letter, 0) + 1);
        }

        return letterCount;
    }

    static boolean isValidWord(Map<Character, Integer> scrambledWordCount, Map<Character, Integer> wordCount) {
        // Перебираем записи в карте для текущего слова
        for (Map.Entry<Character, Integer> entry : wordCount.entrySet()) {
            char letter = entry.getKey();
            int count = entry.getValue();

            // Проверяем, что буква присутствует в перемешанном слове и её количество не меньше, чем в текущем слове
            if (!scrambledWordCount.containsKey(letter) || scrambledWordCount.get(letter) < count) {
                return false;
            }
        }

        return true;
    }

    static int calculatePoints(int wordLength) {
        // Возвращаем количество баллов в соответствии с длиной слова, с учетом бонуса за слова длиной 6 букв
        if (wordLength == 3) {
            return 1;
        } else if (wordLength == 4) {
            return 2;
        } else if (wordLength == 5) {
            return 3;
        } else if (wordLength == 6) {
            return 4 + 50; // 4 балла + 50 баллов бонуса
        }

        return 0; // Недопустимое слово
    }

    //Task 5
    static List<List<Integer>> sumsUp(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();

        // Перебираем элементы массива
        for (int i = 0; i < arr.length; i++) {
            // Перебираем элементы после текущего элемента
            for (int j = i + 1; j < arr.length; j++) {
                // Проверяем, равна ли сумма текущей пары 8
                if (arr[i] + arr[j] == 8) {
                    // Создаем список с текущей парой чисел и добавляем его в результат
                    List<Integer> pair = Arrays.asList(arr[i], arr[j]);
                    result.add(pair);
                }
            }
        }

        return result;
    }

    //Task 6
    public static String takeDownAverage(String[] classmatesScores) {
        // Преобразуем оценки в проценты и вычислим средний балл класса до теста
        double classAverageBeforeTest = Arrays.stream(classmatesScores)
                .mapToDouble(score -> Double.parseDouble(score.replace("%", "")))
                .average()
                .orElse(0);

        // Снижаем средний балл на 5%
        double classAverageAfterTest = classAverageBeforeTest * 0.95;

        // Вычисляем вашу оценку, чтобы средний балл стал classAverageAfterTest
        double yourScore = classAverageAfterTest * (classmatesScores.length + 1) - Arrays.stream(classmatesScores)
                .mapToDouble(score -> Double.parseDouble(score.replace("%", "")))
                .sum();

        // Округляем до ближайшего процента
        long roundedScore = Math.round(yourScore);

        // Возвращаем ответ в виде строки
        return roundedScore + "%";
    }

    //Task 7
    static String caesarCipher(String operation, String message, int shift) {
        StringBuilder result = new StringBuilder();

        // Перебираем символы входного сообщения
        for (char ch : message.toCharArray()) {
            // Проверяем, является ли символ буквой
            if (Character.isLetter(ch)) {
                // Определяем базовый символ ('A' для заглавных букв, 'a' для строчных)
                char base = Character.isUpperCase(ch) ? 'A' : 'a';

                // Вычисляем новый символ с учетом сдвига
                int newChar = (ch - base + (operation.equals("encode") ? shift : 26 - shift)) % 26;

                // Учитываем отрицательные значения при декодировании
                if (newChar < 0) {
                    newChar += 26;
                }

                // Добавляем символ к результату
                result.append((char) (newChar + base));
            } else {
                // Если символ не является буквой, добавляем его к результату без изменений
                result.append(ch);
            }
        }

        return result.toString();
    }

    //Task 8
    static int setupCount(int n, int k) {
        // Проверка на допустимость входных данных
        if (n < k || k < 0) {
            throw new IllegalArgumentException("Invalid input: n must be greater than or equal to k, and k must be non-negative.");
        }

        // Базовый случай: 0! = 1
        if (k == 0) {
            return 1;
        } else {
            // Рекурсивно вычисляем количество размещений
            return n * setupCount(n - 1, k - 1);
        }
    }

    //Task 9
    static String timeDifference(String cityA, String timestampA, String cityB) {
        // Создание объекта для форматирования времени
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm", Locale.US);

        // Преобразование временной метки в объект LocalDateTime для первого города
        LocalDateTime localDateTimeA = LocalDateTime.parse(timestampA, formatter);

        // Получение временных зон для обоих городов
        ZoneId zoneA = ZoneId.of(getTimeZone(cityA));
        ZoneId zoneB = ZoneId.of(getTimeZone(cityB));

        // Преобразование времени в объект ZonedDateTime для обоих городов
        ZonedDateTime zonedDateTimeA = ZonedDateTime.of(localDateTimeA, zoneA);
        ZonedDateTime zonedDateTimeB = zonedDateTimeA.withZoneSameInstant(zoneB);

        // Форматирование и вывод разницы во времени
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm");
        return zonedDateTimeB.format(outputFormatter);
    }

    static String getTimeZone(String city) {
        switch (city) {
            case "Los Angeles":
                return "America/Los_Angeles";
            case "New York":
                return "America/New_York";
            case "Caracas":
                return "America/Caracas";
            case "Buenos Aires":
                return "America/Argentina/Buenos_Aires";
            case "London":
                return "Europe/London";
            case "Rome":
                return "Europe/Rome";
            case "Moscow":
                return "Europe/Moscow";
            case "Tehran":
                return "Asia/Tehran";
            case "New Delhi":
                return "Asia/Kolkata";
            case "Beijing":
                return "Asia/Shanghai";
            case "Canberra":
                return "Australia/Sydney";
            default:
                throw new IllegalArgumentException("Unknown city: " + city);
        }
    }


    //Task 10
    public static boolean isNew(int number) {
        // Преобразование числа в массив символов для удобства манипуляций
        char[] digits = Integer.toString(number).toCharArray();

        // Проверка на ведущие нули
        if (digits.length > 1 && digits[0] == '0') {
            return false;
        }

        // Перебор всех возможных перестановок
        for (int i = 0; i < digits.length - 1; i++) {
            // Обмен текущей цифры с каждой цифрой, следующей за ней
            for (int j = i + 1; j < digits.length; j++) {
                // Обмен значениями
                char temp = digits[i];
                digits[i] = digits[j];
                digits[j] = temp;

                // Преобразование обновленного массива символов обратно в число
                int permutedNumber = Integer.parseInt(new String(digits));

                // Проверка, является ли полученное число "новым"
                if (permutedNumber < number) {
                    // Возврат false, если найдена перестановка меньшего числа
                    return false;
                }

                // Восстановление исходного порядка цифр для следующей итерации
                temp = digits[i];
                digits[i] = digits[j];
                digits[j] = temp;
            }
        }

        // Если не найдено меньшее число, возврат true
        return true;
    }
}
