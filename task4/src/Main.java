import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        //1 task
        String input1 = "abracadabra";
        String result1 = removeDuplicates(input1);
        System.out.println(result1);

        String input2 = "paparazzi";
        String result2 = removeDuplicates(input2);
        System.out.println(result2);

        // 2 task

        int n = 2; // Замените на желаемое значение n
        List<String> combinations = generateBracketCombinations(n);
        for (String combination : combinations) {
            System.out.print(combination);
        }

        //3 task
        System.out.println();
        int m = 3; // Замените на желаемую длину последовательности
        List<String> bincombinations = generateBinaryCombinations(m);
        for (String bincombination : bincombinations) {
            System.out.print(bincombination + " ");
        }

        // 4 task
        System.out.println();
        String input = "abcdjuwx"; // Замените на вашу входную строку
        String longestSequence = findLongestConsecutiveSequence(input);
        System.out.println("Самый длинный последовательный ряд: " + longestSequence);

        // 5 task
        input = "aaabbcdd"; // Используем заново предыдущий input
        String result = countAndSort(input);
        System.out.println(result);

        // 6 task
        input = "eight"; // Замените на вашу входную строку
        int result3 = convertToNumber(input);
        System.out.println(input + " " +  result3);

        // 7 task
        input = "123412324";
        String result4 = findLongestSubstring(input);
        System.out.println("Longest Substring with Unique Characters: " + result4);

        // 8 task
        int[][] matrix1 = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(shortestWay(matrix1)); // ➞ 7

        int[][] matrix2 = {
                {2, 7, 3},
                {1, 4, 8},
                {4, 5, 9}
        };
        System.out.println(shortestWay(matrix2)); // ➞ 21

        // 9 task
        String input3 = "t3o the5m 1One all6 r4ule ri2ng";
        String input4 = "re6sponsibility Wit1h gr5eat power3 4comes g2reat";

        System.out.println(numericOrder(input3)); // "One ring to rule them all"
        System.out.println(numericOrder(input4)); // "With great power comes great responsibility"

        // 10 task
        System.out.println(switchNums(519, 723));    // ➞ 953
        System.out.println(switchNums(491, 3912));   // ➞ 9942
        System.out.println(switchNums(6274, 71259)); // ➞ 77659
    }



    //1 task
    public static String removeDuplicates(String input) {
        // Базовый случай: если строка пуста, вернуть пустую строку
        if (input.length() == 0) {
            return "";
        }

        // Если первый символ содержится в остальной части строки, пропустить его
        if (input.substring(1).contains(input.substring(0, 1))) {
            return removeDuplicates(input.substring(1));
        } else {
            // Иначе, вернуть текущий символ и результат рекурсивного вызова для оставшейся части строки
            return input.substring(0, 1) + removeDuplicates(input.substring(1));
        }
    }

    // 2 task
    public static List<String> generateBracketCombinations(int n) {
        List<String> result = new ArrayList(); // Создаем список для хранения комбинаций скобок
        generateCombinations(result, "", 0, 0, n); // Вызываем вспомогательную функцию для генерации комбинаций
        return result; // Возвращаем список сгенерированных комбинаций
    }

    // Вспомогательная рекурсивная функция для генерации комбинаций скобок
    private static void generateCombinations(List<String> result, String current, int open, int close, int n) {
        // Если длина текущей комбинации равна удвоенному числу пар скобок, то это - допустимая комбинация
        if (current.length() == 2 * n) {
            result.add(current); // Добавляем комбинацию в список
            return; // Завершаем рекурсию
        }

        // Если еще есть открывающие скобки, добавляем их
        if (open < n) {
            generateCombinations(result, current + "(", open + 1, close, n);
        }

        // Если у нас уже есть хотя бы одна открывающая скобка, то мы можем добавить закрывающую
        if (close < open) {
            generateCombinations(result, current + ")", open, close + 1, n);
        }
    }


    // 3 task

    public static List<String> generateBinaryCombinations(int m) {
        List<String> result = new ArrayList(); // Создаем список для хранения бинарных комбинаций
        generateCombinations(result, "", m); // Вызываем вспомогательную функцию для генерации комбинаций
        return result; // Возвращаем список сгенерированных комбинаций
    }

    // Вспомогательная рекурсивная функция для генерации бинарных комбинаций
    private static void generateCombinations(List<String> result, String current, int m) {
        // Если длина текущей последовательности становится равной m, это означает, что мы сформировали бинарную комбинацию
        if (current.length() == m) {
            result.add(current); // Добавляем комбинацию в список
            return; // Завершаем рекурсию
        }

        // Добавить "1" к текущей последовательности и рекурсивно вызвать функцию
        generateCombinations(result, current + "1", m);

        // Если текущая последовательность не заканчивается "0", добавить "0" и рекурсивно вызвать функцию
        if (current.isEmpty() || current.charAt(current.length() - 1) != '0') {
            generateCombinations(result, current + "0", m);
        }
    }


    // 4 task
    public static String findLongestConsecutiveSequence(String str) {
        if (str == null || str.isEmpty()) {
            return ""; // Если строка пуста или равна null, вернуть пустую строку
        }

        String longestSequence = ""; // Инициализируем строку для хранения самой длинной последовательности
        String currentSequence = Character.toString(str.charAt(0)); // Инициализируем текущую последовательность первым символом строки

        for (int i = 1; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            char prevChar = str.charAt(i - 1);

            if (currentChar == prevChar + 1 || currentChar == prevChar - 1) {
                // Если текущий символ следует за предыдущим символом (по алфавиту),
                // добавляем его к текущей последовательности
                currentSequence += currentChar;
            } else {
                // Если текущий символ не следует за предыдущим, это означает,
                // что последовательность прервалась. Сравниваем длину текущей последовательности
                // с длиной самой длинной последовательности, которую мы нашли до сих пор.
                // Если текущая последовательность длиннее, обновляем самую длинную последовательность.
                if (currentSequence.length() > longestSequence.length()) {
                    longestSequence = currentSequence;
                }
                currentSequence = Character.toString(currentChar); // Начинаем новую последовательность с текущего символа
            }
        }

        // После завершения цикла, проверяем длину последней последовательности
        if (currentSequence.length() > longestSequence.length()) {
            longestSequence = currentSequence;
        }

        return longestSequence; // Возвращаем самую длинную последовательность
    }



    // 5 task
    public static String countAndSort(String str) {
        if (str == null || str.isEmpty()) {
            return ""; // Если строка пуста или равна null, вернуть пустую строку
        }

        StringBuilder result = new StringBuilder(); // Создаем StringBuilder для формирования результата
        int count = 1; // Инициализируем счетчик количества повторений символа

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                count++; // Если текущий символ равен предыдущему, увеличиваем счетчик
            } else {
                result.append(str.charAt(i - 1)); // Добавляем символ в результат
                result.append(count); // Добавляем количество повторений в результат
                count = 1; // Сбрасываем счетчик для нового символа
            }
        }

        result.append(str.charAt(str.length() - 1)); // Добавляем последний символ в результат
        result.append(count); // Добавляем количество повторений последнего символа в результат

        char[] resultArray = result.toString().toCharArray(); // Преобразуем результат в массив символов

        // Сортируем результат по возрастанию количества повторений символов
        for (int i = 0; i < resultArray.length - 2; i += 2) {
            for (int j = i + 2; j < resultArray.length; j += 2) {
                if (resultArray[i + 1] > resultArray[j + 1]) {
                    // Если текущий символ имеет большее количество повторений, меняем его местами со следующим символом
                    char tempChar = resultArray[i];
                    resultArray[i] = resultArray[j];
                    resultArray[j] = tempChar;
                    tempChar = resultArray[i + 1];
                    resultArray[i + 1] = resultArray[j + 1];
                    resultArray[j + 1] = tempChar;
                }
            }
        }

        return new String(resultArray); // Возвращаем отсортированную строку
    }

    // 6 task
    private static final HashMap<String, Integer> numberWords = new HashMap<>();

    static {
        // Инициализация статического словаря для соответствия слов числовым значениям
        numberWords.put("one", 1);
        numberWords.put("two", 2);
        numberWords.put("three", 3);
        numberWords.put("four", 4);
        numberWords.put("five", 5);
        numberWords.put("six", 6);
        numberWords.put("seven", 7);
        numberWords.put("eight", 8);
        numberWords.put("nine", 9);
        numberWords.put("ten", 10);
        numberWords.put("eleven", 11);
        numberWords.put("twelve", 12);
        numberWords.put("thirteen", 13);
        numberWords.put("fourteen", 14);
        numberWords.put("fifteen", 15);
        numberWords.put("sixteen", 16);
        numberWords.put("seventeen", 17);
        numberWords.put("eighteen", 18);
        numberWords.put("nineteen", 19);
        numberWords.put("twenty", 20);
        numberWords.put("thirty", 30);
        numberWords.put("forty", 40);
        numberWords.put("fifty", 50);
        numberWords.put("sixty", 60);
        numberWords.put("seventy", 70);
        numberWords.put("eighty", 80);
        numberWords.put("ninety", 90);
        numberWords.put("hundred", 100);
        numberWords.put("thousand", 1000);
    }

    public static int convertToNumber(String numberInWords) {
        String[] words = numberInWords.split(" "); // Разбиваем входную строку на слова
        int number = 0; // Инициализируем итоговое числовое значение
        int tempNumber = 0; // Инициализируем временное числовое значение

        for (String word : words) {
            int value = numberWords.get(word); // Получаем числовое значение для текущего слова из словаря

            if (value == 100) {
                tempNumber *= value; // Если слово "hundred", умножаем временное значение на 100
            } else if (value == 1000) {
                number += tempNumber * value; // Если слово "thousand", добавляем временное значение, умноженное на 1000, к итоговому числу
                tempNumber = 0; // Сбрасываем временное значение
            } else {
                tempNumber += value; // Добавляем числовое значение текущего слова к временному значению
            }
        }

        number += tempNumber; // Добавляем оставшееся временное значение к итоговому числу

        return number; // Возвращаем итоговое числовое значение
    }

    // 7 task

    public static String findLongestSubstring(String input) {
        int maxLength = 0; // Длина самой длинной подстроки
        String longestSubstring = ""; // Самая длинная подстрока
        int startIndex = 0; // Индекс начала текущей подстроки
        HashSet<Character> uniqueCharacters = new HashSet<>(); // Множество для отслеживания уникальных символов

        for (int endIndex = 0; endIndex < input.length(); endIndex++) {
            char currentChar = input.charAt(endIndex);

            // Пока текущий символ уже существует в текущей подстроке, удаляем символы с начала подстроки
            while (uniqueCharacters.contains(currentChar)) {
                uniqueCharacters.remove(input.charAt(startIndex));
                startIndex++;
            }

            uniqueCharacters.add(currentChar); // Добавляем текущий символ в множество уникальных символов

            if (endIndex - startIndex + 1 > maxLength) {
                // Если текущая подстрока длиннее самой длинной, обновляем maxLength и longestSubstring
                maxLength = endIndex - startIndex + 1;
                longestSubstring = input.substring(startIndex, endIndex + 1);
            }
        }

        return longestSubstring; // Возвращаем самую длинную подстроку с уникальными символами
    }


    // 8 task

    public static int shortestWay(int[][] matrix) {
        int n = matrix.length; // Определяем размер матрицы (предполагается, что она квадратная)
        int[][] dp = new int[n][n]; // Создаем матрицу для хранения минимальных сумм

        // Заполняем первую строку и первый столбец матрицы dp
        dp[0][0] = matrix[0][0]; // Начальная точка имеет ту же сумму, что и значение в матрице
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0]; // Перемещаемся только вниз по столбцу
            dp[0][i] = dp[0][i - 1] + matrix[0][i]; // Перемещаемся только вправо по строке
        }

        // Заполняем остальные ячейки матрицы dp
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                // Минимальная сумма достигается при перемещении из верхней (i-1, j) или левой (i, j-1) ячейки
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }

        // Минимальная сумма для пути от верхнего левого угла до нижнего правого угла матрицы
        return dp[n - 1][n - 1];
    }

    // 9 task

    public static String numericOrder(String input) {
        // Разбиваем входную строку на массив слов
        String[] words = input.split(" ");

        // Создаем Map для хранения чисел и связанных с ними слов
        Map<Integer, String> wordMap = new HashMap<>();

        // Создаем регулярное выражение для поиска чисел в словах
        Pattern pattern = Pattern.compile("\\d+");

        // Проходим по каждому слову из входной строки
        for (String word : words) {
            // Ищем числа в слове с помощью регулярного выражения
            Matcher matcher = pattern.matcher(word);
            if (matcher.find()) {
                // Извлекаем число и слово без числа
                int num = Integer.parseInt(matcher.group());
                String cleanWord = word.replaceAll("\\d", "");

                // Добавляем число и слово в Map
                wordMap.put(num, cleanWord);
            }
        }

        // Создаем объект StringBuilder для построения результата
        StringBuilder result = new StringBuilder();

        // Проходим по числам от 1 до размера wordMap
        for (int i = 1; i <= wordMap.size(); i++) {
            String word = wordMap.get(i);
            if (word != null) {
                result.append(word);
                if (i < wordMap.size()) {
                    result.append(" ");
                }
            }
        }

        // Возвращаем полученную строку
        return result.toString();
    }


    // 10 task

    public static int switchNums(int num1, int num2) {
        // Преобразуем входные числа в строки
        String strNum1 = Integer.toString(num1);
        String strNum2 = Integer.toString(num2);

        // Создаем массивы символов для обоих чисел
        char[] charArray1 = strNum1.toCharArray();
        char[] charArray2 = strNum2.toCharArray();

        // Создаем массив, в котором будем хранить замененные символы
        char[] switchedChars = new char[charArray2.length];

        // Пройдемся по символам второго числа и заменим их на максимальные из первого числа
        for (int i = 0; i < charArray2.length; i++) {
            char maxDigit = '0';

            // Найдем максимальную цифру в первом числе, которая еще не использована
            for (int j = 0; j < charArray1.length; j++) {
                if (charArray1[j] > maxDigit) {
                    maxDigit = charArray1[j];
                }
            }

            // Если нашли максимальную цифру, заменяем ею текущий символ во втором числе
            if (charArray2[i] < maxDigit) {
                switchedChars[i] = maxDigit;

                // Обновляем первое число, убирая использованную цифру
                for (int j = 0; j < charArray1.length; j++) {
                    if (charArray1[j] == maxDigit) {
                        charArray1[j] = '0'; // Убираем использованную цифру, заменяя её на '0'
                        break;
                    }
                }
            } else {
                switchedChars[i] = charArray2[i]; // Если не нашли максимальную цифру, оставляем текущий символ
            }
        }

        // Преобразуем массив символов обратно в строку и затем в число
        String switchedStr = new String(switchedChars);
        int switchedNum = Integer.parseInt(switchedStr);

        return switchedNum;
    }

}