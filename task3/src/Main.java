import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // 1 Задача
        String inputString1 = "apple";
        String resultString1 = replaceVowelsWithAsterisk(inputString1);
        System.out.println(resultString1);

        // 2 Задача
        String inputString2 = "hello";
        String resultString2 = replaceDoublePattern(inputString2);
        System.out.println(resultString2);

        // 3 Задача
        int a = 1; // Высота блока
        int b = 3; // Ширина блока
        int c = 5; // Глубина блока
        int w = 4; // Ширина отверстия
        int h = 5; // Высота отверстия
        boolean canFit = canBlockFit(a, b, c, w, h);
        System.out.println("Может ли блок поместиться в отверстие? " + canFit);

        // 4 Задача
        int number = 243;
        boolean result = hasSameParity(number);
        System.out.println("Сумма квадратов цифр имеет ту же четность: " + result);

        // 5 Задача
        int[] coefficients = {1, -6, 9}; // Пример коэффициентов a, b и c
        int numberOfIntegerRoots = countIntegerRoots(coefficients);
        System.out.println("Количество целочисленных корней: " + numberOfIntegerRoots);

        // 6 Задача
        System.out.println(salesData(new String[][] {
                {"Apple", "Shop1", "Shop2", "Shop3", "Shop4"},
                {"Banana", "Shop2", "Shop3", "Shop4"},
                {"Orange", "Shop1", "Shop3", "Shop4"},
                {"Pear", "Shop2", "Shop4"}
        }));

        // 7 Задача
        String sentence1 = "apple eagle egg goat"; // Пример предложения, которое можно разбить на слова по правилам
        System.out.println("Можно ли разбить предложение по правилам: " + canFormWordChain(sentence1));

        // 8 Задача
        int[] array1 = {3, 1, 4, 2, 7, 5};
        int[] array2 = {1, 2, 3, 4, 5};
        System.out.println(waveForm(array1));
        System.out.println(waveForm(array2));

        // 9 Задача
        String sentence2 = "Hello world";
        String sentence3 = "Actions speak louder than words.";

        System.out.println(commonVowel(sentence2)); // "o"
        System.out.println(commonVowel(sentence3)); // "a"

        // 10 Задача
        int[][] firstMatrix = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 29, 10},
                {5, 5, 5, 5, 35},
                {7, 4, 3, 14, 2},
                {1, 0, 11, 10, 1}
        };
        int[][] secondMatrix = {
                {6, 4, 19, 0, 0},
                {81, 25, 3, 1, 17},
                {48, 12, 60, 32, 14},
                {91, 47, 16, 65, 217},
                {5, 73, 0, 4, 21}
        };
        System.out.println(Arrays.deepToString(dataScience(firstMatrix)));
        System.out.println(Arrays.deepToString(dataScience(secondMatrix)));
    }

    // 1 Задача замена гласных
    public static String replaceVowelsWithAsterisk(String input) {
        // Создаем регулярное выражение для поиска гласных букв (русских и английских)
        String regex = "[АЕИОУЫЭЮЯаеиоуыэюяAEIOUaeiou]";

        // Заменяем гласные буквы на символ '*'
        String result = input.replaceAll(regex, "*");

        return result;
    };

    // 2 Задача две идущие подряд на double
    public static String replaceDoublePattern(String input) {
        // Создаем регулярное выражение для поиска двух идущих подряд букв
        String doublePatternRegex = "(.)(\\1)";

        // Заменяем двойные буквы на "Double*"
        String result = input.replaceAll(doublePatternRegex, "Double$1");

        return result;
    }

    // 3 Задача влезет ли блок
    public static boolean canBlockFit(int a, int b, int c, int w, int h) {
        // Проверяем, может ли блок поместиться в отверстие по высоте и ширине
        boolean canFitHeight = a <= h && b <= w;
        boolean canFitWidth = a <= w && b <= h;

        // Проверяем, может ли блок поместиться в отверстие по глубине
        boolean canFitDepth = c <= w || c <= h;
        // Блок может поместиться в отверстие, если удовлетворяются все условия
        return canFitHeight && canFitWidth && canFitDepth;
    }

    // 4 Задача сумма квадратов имеет ту же четность, что и само число
    public static boolean hasSameParity(int number) {
        // Вычисляем сумму квадратов цифр числа
        int sumOfSquares = 0;
        int num = Math.abs(number); // Берем модуль числа, чтобы работать только с положительными цифрами

        while (num > 0) {
            int digit = num % 10;
            sumOfSquares += digit * digit;
            num /= 10;
        }

        // Проверяем, имеет ли сумма квадратов ту же четность, что и исходное число
        return (number % 2 == 0 && sumOfSquares % 2 == 0) || (number % 2 != 0 && sumOfSquares % 2 != 0);
    }

    // 5 Задача берёт массив целых, возвращает число целочисленных корней кв ур
    public static int countIntegerRoots(int[] coefficients) {
        if (coefficients.length != 3) {
            throw new IllegalArgumentException("Массив коэффициентов должен содержать 3 элемента (a, b, c).");
        }

        int a = coefficients[0];
        int b = coefficients[1];
        int c = coefficients[2];

        int discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
            // Два вещественных корня
            return 0;
        } else if (discriminant == 0) {
            // Один корень (два корня совпадают)
            return 1;
        } else {
            // Нет вещественных корней
            return 2;
        }
    }

    // 6 Задача принимает двумерный массив и возвращает товары купленные
    public static ArrayList<String> salesData(String[][] all) {
        // Создаем список для хранения товаров, которые были проданы во всех магазинах
        ArrayList<String> products = new ArrayList<>();
        int maxShopCount = 0;
        // Начинаем перебирать строки во входном массиве
        for (String[] shops : all) {
            int shopCount = shops.length;

            if (shopCount > maxShopCount){
                maxShopCount = shopCount;
                products.clear();
                products.add(shops[0]);
            } else if (shopCount == maxShopCount) {
                products.add(shops[0]);
            }
        }
        return products;
    }


    // 7 Задача можно ли разбить приложение так что чтобы каждое слово начиналос  с последней буквы предыдущего
    public static boolean canFormWordChain(String sentence) {
        // Проверяем, что предложение не пустое и состоит из более чем одного слова
        if (sentence == null || sentence.trim().isEmpty()) {
            return false;
        }

        String[] words = sentence.split(" "); // Разбиваем предложение на слова
        int numWords = words.length;

        // Проверяем, что есть хотя бы два слова в предложении
        if (numWords < 2) {
            return false;
        }

        // Проверяем, что каждое слово начинается с последней буквы предыдущего слова
        for (int i = 1; i < numWords; i++) {
            String prevWord = words[i - 1];
            String currentWord = words[i];

            if (prevWord.charAt(prevWord.length() - 1) != currentWord.charAt(0)) {
                return false;
            }
        }

        return true;
    }

    // 8 Задача определение волнообразного массива (если разница между соседними чередуется между убываением и возраст)
    public static boolean waveForm(int[] arr) {
        // проверка на случаи,когда массив путой или менее 2-х элементов
        if (arr == null || arr.length<2){
            return false; // если массив не может быть волнообразным
        }
        //проходим по элемнтам массива от 0 до предпоследнего
        for (int i = 0; i <arr.length - 1; i++){
            //если текущий индекс четный  и текущий элемент меньше или равен след, дальше нечет индекс
            if((i % 2 == 0 && arr[i] <= arr[i+1])||(i % 2 !=0 && arr[i] >= arr[i+1])){
                return false;
            }
        }
        return true;
    }
    // 9 Задача наиболее часто встр гласная
    public static char commonVowel(String sentence) {
        // Приводим предложение к нижнему регистру
        sentence = sentence.toLowerCase();

        // Создаем мапу для подсчета гласных и их количества
        Map<Character, Integer> vowelCount = new HashMap<>();

        // Определяем гласные буквы
        String vowels = "aeiou";

        // Проходим по каждому символу в предложении
        for (char c : sentence.toCharArray()) {
            // Проверяем, является ли символ гласной
            if (vowels.contains(String.valueOf(c))) {
                // Увеличиваем счетчик для данной гласной
                vowelCount.put(c, vowelCount.getOrDefault(c, 0) + 1);
            }
        }
        // Ищем гласную с наибольшим количеством встреч в мапе
        char mostCommonVowel = ' ';
        int maxCount = 0;

        for (Map.Entry<Character, Integer> entry : vowelCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostCommonVowel = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return mostCommonVowel;
    }
    // 10 Задача измерения среднего арифметического массива n-ой строки
    public static int[][] dataScience(int[][] matrix) {
        // Определяем размерность матрицы
        int n = matrix[0].length;

        // Проходим по каждой строке матрицы
        for (int mainRow = 0; mainRow < n; mainRow++) {
            int newElement = 0;

            // Проходим по каждой строке матрицы
            for (int sideRow = 0; sideRow < n; sideRow++) {
                // Проверяем, что текущая строка не совпадает с главной диагональю
                if (sideRow != mainRow) {
                    // Суммируем элементы из разных строк в столбце, кроме элемента на главной диагонали
                    newElement += matrix[sideRow][mainRow];
                }
            }
            // Заменяем элемент на главной диагонали на новое значение
            matrix[mainRow][mainRow] = (int) Math.round((double) newElement / (n - 1));
        }
        return matrix;
    }
}