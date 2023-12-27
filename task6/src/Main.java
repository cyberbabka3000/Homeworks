import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Main{

    public static void main(String[] args) {
        // & 1
        System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.", "sworn love lived"));
        // * worldevolvesin
        System.out.println(hiddenAnagram("An old west action hero actor", "Clint Eastwood"));
        // * noldwestactio
        System.out.println(hiddenAnagram("Mr. Mojo Rising could be a song title", "Jim Morrison"));
        // * mrmojorisin
        System.out.println(hiddenAnagram("Banana? margaritas", "ANAGRAM"));
        // * anamarg
        System.out.println(hiddenAnagram("D  e b90it->?$ (c)a r...d,,#~", "bad credit"));
        // * debitcard
        System.out.println(hiddenAnagram("Bright is the moon", "Bongo mirth") + "\n");
        // * notfound


        // & 2
        System.out.println(collect("intercontinentalisationalism", 6));
        // * ["ationa", "interc", "ntalis", "ontine"]
        System.out.println(collect("strengths", 3));
        // * ["eng", "str", "ths"]
        System.out.println(collect("pneumonoultramicroscopicsilicovolcanoconiosis", 15) + "\n");
        // * ["croscopicsilico", "pneumonoultrami", "volcanoconiosis"]


        // & 3
        System.out.println(nicoCipher("myworldevolvesinhers", "tesh"));
        // * yowmledrovlvsnieesrh
        System.out.println(nicoCipher("andiloveherso", "tesha"));
        // * lnidaevheo s or
        System.out.println(nicoCipher("mubashirhassan", "crazy"));
        // * bmusarhiahass n
        System.out.println(nicoCipher("edabitisamazing", "matt"));
        // * deabtiismaaznig
        System.out.println(nicoCipher("iloveher", "612345") + "\n");
        // * lovehir    e

        // & 4
        System.out.println(Arrays.toString(twoProduct(new int[] {1, 2, 3, 9, 4, 5, 15}, 45)));
        // * [9, 5]
        System.out.println(Arrays.toString(twoProduct(new int[] {1, 2, 3, 9, 4, 15, 3, 5}, 45)));
        // * [3, 15]
        System.out.println(Arrays.toString(twoProduct(new int[] {1,  2, -1,  4,  5,  6,  10, 7}, 20)));
        // * [4, 5]
        System.out.println(Arrays.toString(twoProduct(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10)));
        // * [2, 5]
        System.out.println(Arrays.toString(twoProduct(new int[] {100, 12, 4, 1, 2}, 15)) + "\n");
        // * []

        // & 5
        System.out.println(Arrays.toString(isExact(6)));            // * [6, 3]
        System.out.println(Arrays.toString(isExact(24)));           // * [24, 4]
        System.out.println(Arrays.toString(isExact(125)));          // * []
        System.out.println(Arrays.toString(isExact(720)));          // * [720, 6]
        System.out.println(Arrays.toString(isExact(1024)));         // * []
        System.out.println(Arrays.toString(isExact(40320)) + "\n"); // * [40320, 8]

        // & 6
        System.out.println(fractions("0.(6)"));            // * "2/3"
        System.out.println(fractions("1.(1)"));            // * "10/9"
        System.out.println(fractions("3.(142857)"));       // * "22/7"
        System.out.println(fractions("0.19(2367)"));       // * "5343/27775"
        System.out.println(fractions("0.1097(3)") + "\n"); // * "823/7500"

        // & 7
        System.out.println(pilish_string("33314444")); // *  333 1 4444
        System.out.println(pilish_string("TOP"));      // *  TOP
        System.out.println(pilish_string("X"));        // *  XXX
        System.out.println(pilish_string("") + "\n");  // *  ""

        // & 8
        System.out.println(generateNonconsecutive("3 + 5 * (2 - 6)"));           // *  -17
        System.out.println(generateNonconsecutive("6 - 18 / (-1 + 4)") + "\n");  // *  0

        // & 9
        System.out.println(isValid("aabbcd"));                  // *  NO
        System.out.println(isValid("aabbccddeefghi"));          // *  NO
        System.out.println(isValid("abcdefghhgfedecba")+ "\n"); // *  YES

        // & 10
        System.out.println(findLCS("abcd", "bd"));        // *  "bd"
        System.out.println(findLCS("aggtab", "gxtxamb")); // *  "gtab"
    }

    /* 1. hiddenAnagram: данная функция принимает две строки и ищет скрытые анаграммы в первой строке,
     используя буквы второй строки. */

    public static String hiddenAnagram(String a, String b) {
        a = a.toLowerCase().replaceAll("[^a-z]", "");
        b = b.toLowerCase().replaceAll("[^a-z]", "");
        int[] setB = new int[26];
        for (char c : b.toCharArray()) setB[c - 97]++;

        for (int i = 0; i <= a.length() - b.length(); i++) {
            int[] setA = new int[26];
            for (char c : a.substring(i, i + b.length()).toCharArray()) setA[c - 97]++;
            if (Arrays.equals(setA, setB)) {
                return a.substring(i, i + b.length());
            }
        }
        return "notfound";
    }

    /* 2. collect: данная функция принимает строку и число n, и разбивает строку на подстроки длиной n.
     Затем эти подстроки сортируются и возвращаются в виде списка. */

    public static List<String> collect(String str, int n) {
        if (str.length() < n) {
            return new ArrayList<>();
        } else {
            List<String> list = new ArrayList<>();
            list.add(str.substring(0, n));
            list.addAll(collect(str.substring(n), n));
            return list.stream().sorted().collect(Collectors.toList());
        }
    }

    /* 3. nicoCipher: данная функция реализует шифр Нико. Она принимает две строки - сообщение и ключ,
     и шифрует сообщение, используя расположение символов ключа в алфавитном порядке. */

    public static String nicoCipher(String message, String key) {
//Вычисляется длина ключа и количество дополнительных пробелов, которые нужно добавить к сообщению,
//чтобы они делились на длину ключа. Дополнительные пробелы добавляются в конец сообщения.
        int keyLength = key.length();
        int extraSpaces = (keyLength - (message.length() % keyLength)) % keyLength;
        message += new String(new char[extraSpaces]).replace("\0", " ");
//Создается массив order длины ключа и заполняется значениями от 0 до длины ключа
        Integer[] order = new Integer[keyLength];
        for (int i = 0; i < keyLength; i++) {
            order[i] = i;
        }
//Массив order сортируется по символам ключа в лексикографическом порядке.
        Arrays.sort(order, (a, b) -> Character.compare(key.charAt(a), key.charAt(b)));
//Вычисляется результат шифрования путем перестановки символов сообщения в соответствии с порядком из массива order.
//Результат шифрования возвращается в виде строки.
        char[] result = new char[message.length()];
        for (int i = 0; i < message.length(); i++) {
            int columnIndex = order[i % keyLength];
            int rowIndex = i / keyLength;
            result[i] = message.charAt(rowIndex * keyLength + columnIndex);
        }

        return new String(result);
    }

    /* 4. twoProduct: данная функция принимает массив целых чисел и целевое число n.
    Она находит пару чисел из массива, произведение которых равно n, и возвращает их в виде массива. */

    public static int[] twoProduct(int[] arr, int n) {
        HashSet<Integer> set = new HashSet<>();
//находит два числа в массиве, произведение которых равно n, и возвращает их в виде массива.
//Если такие числа не найдены, возвращается пустой массив.
        for (int m : arr) {
            int target = n / m;
            if (n % m == 0 && set.contains(target))
                return new int[] { target, m };
            set.add(m);
        }
        return new int[] {};
    }

    /* 5. isExact: данная функция принимает число n и проверяет, является ли оно "точным" факториалом,
     то есть получено умножением целых чисел. Если число является точным факториалом,
      то функция возвращает массив [n, k], где k - количество множителей. */
//Перегрузка с тремя параметрами isExact(int f, int m, int n) выполняет рекурсивную проверку, увеличивая f на m+1,
//пока f не станет больше или равно n. Если f становится равным n, возвращается массив из двух чисел [f, m].
//Если f становится больше n, возвращается пустой массив.
    public static int[] isExact(int f, int m, int n) {
        return (f < n) ? isExact(f * (m + 1), m + 1, n) : new int[] { f, m };
    }
    //Перегрузка с одним параметром isExact(int n) вызывает первую перегрузку с начальными значениями f=1 и m=1.
//Если результирующий массив содержит число n, возвращается этот массив, иначе возвращается пустой массив.
    public static int[] isExact(int n) {
        int[] res = isExact(1, 1, n);
        return (res[0] == n) ? res : new int[] {};
    }

    /* 6. fractions: данная функция принимает строковое представление десятичной дроби и
     возвращает ее соответствующую обыкновенную дробь в виде строки. */

    public static String fractions(String frac) {
        int startBracket = frac.indexOf('(');
//Функция заменяет повторяющиеся десятичные дроби внутри скобок на последовательность девяти повторений этой десятичной дроби.
//функция преобразует полученную строку в число с плавающей точкой и находит наименьшее целое число a, такое
//что округленное значение числа умноженное на a является целым числом. Возвращается строка, содержащая округленное
//значение числа умноженное на a, разделенное на a.
        if (startBracket != -1) {
            String repeating = frac.substring(startBracket + 1, frac.length() - 1);
            frac = frac.substring(0, startBracket) + repeating.repeat(9 - repeating.length());
        }
        double a = Double.parseDouble(frac);
        int div = 2;
        while (Math.ceil(a * div) - a * div > 0.000001) div++;
        return (int) Math.ceil(a * div) + "/" + div;
    }

    /* 7. pilish_string: данная функция принимает строку и преобразует ее в строку,
     где длина каждого слова соответствует цифре числа π (пи). */

    public static String pilish_string(String str) {
        String res = "";
        String pi = String.valueOf(Math.PI).replace(".", "");
        int piIndex = 0, strIndex = 0;
//Функция pilish_string принимает на вход строку str и преобразует ее в строку, содержащую последовательность чисел,
//составленных из цифр числа Пи. Длина каждого числа определяется соответствующей цифрой числа Пи.
//Если строка str заканчивается, но последнее число еще не полностью заполнено,
//оно дополняется повторением последней цифры. Возвращается полученная строка.
        while (strIndex < str.length()) {
            int p = pi.charAt(piIndex++) - '0';
            int n = Math.min(p, str.length() - strIndex);
            res += str.substring(strIndex, strIndex + n);
            strIndex += n;
            if (strIndex < str.length()) res += ' ';
            else
                while (n++ < p) res += res.charAt(res.length() - 1);
        }
        return res;
    }

    /* 8. generateNonconsecutive: данная функция принимает строку с арифметическим выражением и вычисляет его значение.
     При вычислении игнорируются парные скобки и присутствуют ограничения на последовательность операций. */

    public static String generateNonconsecutive(String str) {
//Определяет регулярное выражение part, которое ищет числа с арифметическими операциями (сумма, разность, произведение, деление) и скобками
        Pattern part = Pattern.compile("^( [\\-+*/] )*(\\()*(-*\\d+)(\\))*");
        boolean start = true;
//Инициализирует переменные и объекты, включая объект currentNode класса Node, который представляет операции и числа,
//и устанавливает начальное значение равным "+" и левый узел равным "0".
        Node currentNode = new Node("+");
        currentNode.setLeft(new Node("0"));
        int parLevel = 0;
//for, который обрабатывает строку str.
        for (int i = 0; i < str.length();) {
            Matcher matcher = part.matcher(str).region(i, str.length());
//Использует регулярное выражение part и методы matcher.find() и matcher.group() для анализа подстроки str и проверки синтаксиса.
            if (!matcher.find()){
//Если обнаружена ошибка синтаксиса, выводит сообщение "Syntax error" и возвращает null.
                System.out.println("Syntax error");
                return null;
            }
//Если найдена операция, создает новый узел операции и числа и устанавливает соответствующие связи между узлами.
            if (matcher.group(1) == null){
                if (!start){
                    System.out.println("Syntax error");
                    return null;
                }
                currentNode.setRight(new Node(matcher.group(3)));
                start = false;
                i = matcher.end();
//создает новый узел
                if (!(matcher.group(2) == null)){
                    parLevel++;
                }
                continue;
            }
            Node opNode = new Node(String.valueOf(matcher.group(1).charAt(1)));
            Node numNode = new Node(matcher.group(3));
            opNode.setRight(numNode);
//Если найдена операция, создает новый узел операции и числа и устанавливает соответствующие связи между узлами.
            if (parLevel > 0){
                if (matcher.group(1).charAt(1) == '*' || matcher.group(1).charAt(1) == '/'){
                    opNode.setLeft(currentNode.right);
                    currentNode.setRight(opNode);
                }
                else {
                    opNode.setLeft(currentNode.right);
                    currentNode.setRight(opNode);
                    currentNode = opNode;
                }
            }
            if (parLevel == 0){
                if (matcher.group(1).charAt(1) == '*' || matcher.group(1).charAt(1) == '/'){
                    opNode.setLeft(currentNode.right);

                    currentNode.setRight(opNode);
                }
                else {
                    opNode.setLeft(currentNode);
                    currentNode = opNode;
                }
            }

            if (!(matcher.group(2) == null)){
                currentNode = opNode;
                parLevel++;
            }
            if (!(matcher.group(4) == null)){
                if (!(currentNode.root == null)){
                    currentNode = currentNode.root;
                }
                parLevel--;
            }
            i = matcher.end();
        }
        try {
            double res = currentNode.getUltimateRoot().computeNode();
            if (res % 1 == 0){
                return String.valueOf((int) res);
            }
            return String.valueOf(res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    static class Node {
        String value;
        Node left;
        Node root;
        Node right;


        public void setLeft(Node node){
            this.left = node;
            node.root = this;
        }

        public void setRight(Node node){
            this.right = node;
            node.root = this;
        }

        public Node getUltimateRoot(){
            if (root != null){
                return root.getUltimateRoot();
            }
            return this;
        }

        public double computeNode() throws Exception {
            if ("+-*/".contains(value)){
                double num1 = left.computeNode();
                double num2 = right.computeNode();

                switch (value){
                    case "+":
                        return num1 + num2;
                    case "-":
                        return num1 - num2;
                    case "*":
                        return num1 * num2;
                    case "/":
                        if (num2 == 0){
                            throw new ArithmeticException("Division by zero");
                        }
                        return num1 / num2;
                    default:
                        throw new Exception("Syntax error");
                }

            }
            return Double.parseDouble(value);
        }
        //класс Node для представления узлов операций и чисел. Узлы связываются друг с другом, а метод computeNode() рекурсивно вычисляет результат операции.
        Node(String value) {
            this.value = value;
            this.root = null;
            right = null;
            left = null;
        }
    }


    /* 9. isValid: данная функция принимает строку и определяет, можно ли удалить один символ так,
     чтобы остальные символы встречались одинаковое количество раз. */

    public static String isValid(String str) {
//создания массива charCounts размером 26, где каждый элемент представляет счетчик для каждой буквы английского алфавита (от 'a' до 'z').
        int[] charCounts = new int[26];
//цикл for проходит по каждому символу str и увеличивает счетчик символа в массиве charCounts на 1.
//При этом символ должен быть английской буквой от 'a' до 'z'.
        for (char c : str.toCharArray()) {
            charCounts[c - 'a']++;
        }
        int prevCount = -1;
        int removals = 0;
// по каждому элементу массива charCounts и проверяет, если счетчик больше нуля.
//Если prevCount равен -1 (то есть это первый символ), prevCount принимает значение счетчика, иначе,
//если prevCount отличается от текущего счетчика, добавляется разница между prevCount и текущим счетчиком к переменной removals.
//Если removals превышает 1, возвращается "NO".
        for (int count : charCounts) {
            if (count > 0) {
                if (prevCount == -1) {
                    prevCount = count;
                } else if (prevCount != count) {
                    removals += Math.abs(prevCount - count);
                    if (removals > 1) return "NO";
                }
            }
        }
        return "YES";
    }

    /* 10. findLCS: данная функция принимает две строки и находит наибольшую общую подпоследовательность
     (Longest Common Subsequence) между ними. */

    public static String findLCS(String s1, String s2) {
//Алгоритм использует динамическое программирование и заполняет двумерный массив dp размером (s1.length() + 1) x (s2.length() + 1).
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        StringBuilder lcs = new StringBuilder();
//Значение dp[i][j] представляет длину LCS для подстроки s1[0...i-1] и s2[0...j-1].
//Затем алгоритм проходит по массиву dp справа налево, начиная с конца строк s1 и s2, и сравнивает символы на каждой позиции.
//Если символы равны, то текущий символ добавляется к LCS и индексы уменьшаются на 1. Если символы не равны,
//алгоритм перемещается в направлении с большим значением LCS.
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int i = s1.length(), j = s2.length();

        //В конце алгоритма, полученная LCS высчитывается, и возвращается в виде строки.
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return lcs.reverse().toString();
    }
}