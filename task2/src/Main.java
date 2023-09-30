
import java.security.SecureRandom;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //1 Задача
        String str = "Donald"; // Пример строки без повторяющихся символов
        System.out.println("Есть ли повторяющиеся символы: " + hasDuplicateCharacters(str)); // Выводим false

        //2 Задача
        String fullName = "Ryan Gosling"; // Пример строки с фамилией и именем
        String initials = getInitials(fullName);
        System.out.println("Инициалы без пробелов: " + initials); // Выводим "ИП"

        //3 Задача
        int[] numbers = {44, 32, 86, 19}; // Пример массива чисел
        int result = findEvenOddDifference(numbers);
        System.out.println("Разница между суммой четных и нечетных чисел: " + result);

        //4 Задача
        int[] arr1 = {1, 2, 3, 4, 5}; // Пример массива, содержащего элемент равный среднему
        System.out.println(containsAverageElement(arr1)); // Выводим true

        int[] arr2 = {1, 2, 3, 4, 6}; // Пример массива без элемента, равного среднему
        System.out.println(containsAverageElement(arr2)); // Выводим false

        //5 Задача

        int[] originalArray = {1, 2, 3}; // Пример исходного массива
        int[] resultArray = multiplyByIndex(originalArray);

        // Выводим результат
        System.out.print("Исходный массив: ");
        for (int num : originalArray) {
            System.out.print(num + " ");
        }

        System.out.println();

        System.out.print("Массив, умноженный на индексы: ");
        for (int num : resultArray) {
            System.out.print(num + " ");
        }

        //6 Задача
        String input = "Hello World"; // Пример строки
        String reversed = reverseString(input);

        System.out.println("Исходная строка: " + input);
        System.out.println("Строка в обратном порядке: " + reversed);

        //7 Задача
        int n = 7;
        int tribonacciNumber = getTribonacciNumber(n);
        System.out.println("Число Трибоначчи с порядковым номером " + n + " равно " + tribonacciNumber);

        //8 Задача
        int length = 5; // Задайте желаемую длину квази-хэша
        String quasiHash = generateQuasiHash(length);
        System.out.println("Сгенерированный квази-хэш: " + quasiHash);

        //9 Задача
        String stenogram = "Hello, I’m under the water, please help me";
        String response = botHelper(stenogram);
        System.out.println(response);

        //10 Задача
        String str1 = "hello";
        String str2 = "world";

        boolean results = isAnagram(str1, str2);

        if (results) {
            System.out.println(str1 + " и " + str2 + " - это анаграммы.");
        } else {
            System.out.println(str1 + " и " + str2 + " - это не анаграммы.");
        }
    }

    //10 Задача
    public static boolean isAnagram(String str1, String str2) {
        // Приведем строки к нижнему регистру и уберем пробелы (опционально)
        str1 = str1.toLowerCase().replaceAll("\s", "");
        str2 = str2.toLowerCase().replaceAll("\s", "");

        // Если длины строк разные, они не могут быть анаграммами
        if (str1.length() != str2.length()) {
            return false;
        }

        // Преобразуем строки в массивы символов и сортируем их
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        // Сравниваем отсортированные массивы
        return Arrays.equals(arr1, arr2);
    }

    //9 Задача
    public static String botHelper(String stenogram) {
        // Приведем строку к нижнему регистру для регистронезависимого поиска
        stenogram = stenogram.toLowerCase();

        // Проверяем, содержит ли строка слово "help"
        if (stenogram.contains("help")) {
            return "Вызов сотрудника";
        } else {
            return "Продолжайте ожидание";
        }
    }

    //8 Задача
    public static String generateQuasiHash(int length) {
        String characters = "abcdef0123456789"; //строка, которая содержит символы, из которых будет генерироваться случайная строка
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }


    //7 Задача
    public static int getTribonacciNumber(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Порядковый номер не может быть отрицательным.");
        }

        if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        }

        int[] tribonacci = new int[n];
        tribonacci[0] = 0;//первый эл
        tribonacci[1] = 0;//второй эл
        tribonacci[2] = 1;//третий эл

        for (int i = 3; i < n; i++) {//начинаем вычисления с четвертого элемента
            tribonacci[i] = tribonacci[i - 1] + tribonacci[i - 2] + tribonacci[i - 3];
        }//В этой строке вычисляется значение, которое равно сумме трех предыдущих элементов

        return tribonacci[n - 1]; // Вернуть число Трибоначчи с порядковым номером n
    }

    //6 Задача
    public static String reverseString(String input) {
        // Создаем объект StringBuilder для эффективной работы со строкой
        StringBuilder reversed = new StringBuilder();

        // Перебираем символы исходной строки в обратном порядке и добавляем их к reversed
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed.append(input.charAt(i));
        }

        // Преобразуем StringBuilder обратно в строку и возвращаем её
        return reversed.toString();
    }

    //5 Задача
    public static int[] multiplyByIndex(int[] arr) {
        int length = arr.length;//длина первого
        int[] result = new int[length];//новый по длине перого

        for (int i = 0; i < length; i++) {
            result[i] = arr[i] * i;//для каждого элемента arr[i], его значение умножается на его индекс i
        }

        return result;
    }

    //4 Задача
    public static boolean containsAverageElement(int[] arr) {
        if (arr.length == 0) {
            // Если массив пуст, возвращаем false
            return false;
        }

        // Вычисляем сумму всех элементов массива
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }

        // Вычисляем среднее арифметическое
        double average = (double) sum / arr.length;

        // Проверяем, есть ли элемент в массиве, равный среднему арифметическому
        for (int num : arr) {
            if (num == average) {
                return true;
            }
        }

        // Если не найдено совпадений, возвращаем false
        return false;
    }

    //3 Задача
    public static int findEvenOddDifference(int[] numbers) {
        int sumEven = 0; // Переменная для суммы четных чисел
        int sumOdd = 0;  // Переменная для суммы нечетных чисел

        // Проходим по массиву чисел
        for (int number : numbers) {
            // Проверяем, является ли число четным
            if (number % 2 == 0) {
                sumEven += number; // Если четное, добавляем к сумме четных
            } else {
                sumOdd += number;  // Если нечетное, добавляем к сумме нечетных
            }
        }

        // Вычисляем разницу между суммой четных и суммой нечетных чисел
        int difference = sumEven - sumOdd;

        return difference;
    }


    //2 Задача
    public static String getInitials(String fullName) {
        // Разделяем строку по пробелу, чтобы получить фамилию и имя
        String[] parts = fullName.split(" ");

        // Инициализируем переменную для хранения инициалов
        StringBuilder initials = new StringBuilder();

        // Проходим по каждой части (фамилии и имени)
        for (String part : parts) {
            // Получаем первый символ части и добавляем его к инициалам
            if (!part.isEmpty()) {
                initials.append(part.charAt(0));
            }
        }

        // Возвращаем инициалы без пробелов
        return initials.toString();
    }

    //1 Задача
    public static boolean hasDuplicateCharacters(String str) {
        // Создаем массив для хранения флагов для каждого символа
        boolean[] charFlags = new boolean[128]; // Предполагаем, что используется кодировка ASCII

        // Проходим по каждому символу в строке
        for (int i = 0; i < str.length(); i++) {
            int charValue = (int) str.charAt(i);

            // Если флаг для текущего символа уже установлен в true, значит символ повторяется
            if (charFlags[charValue]) {
                return true;
            }

            // Устанавливаем флаг для текущего символа в true
            charFlags[charValue] = true;
        }

        // Если не было обнаружено повторяющихся символов, возвращаем false
        return false;
    }

}

