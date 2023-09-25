

public class Palindrome {
    public static String reverseString(String s) {
        String reversed = "";
        for (int i = s.length() - 1; i >= 0; i--) {//Это цикл for, который итерируется от последнего символа строки s
            // (его индекс равен s.length() - 1) до первого символа
            reversed += s.charAt(i);//Передача данных
        }
        return reversed;
    }

    public static boolean isPalindrome(String s) {
        // Удаляем пробелы из строки и приводим ее к нижнему регистру
        s = s.replaceAll(" ", "").toLowerCase();
//Этот метод заменяет все пробелы (представленные строкой " ") в строке s на пустую строку "".
// Пробелы удаляются из строки s. toLowerCase() - Этот метод преобразует все символы в строке в нижний регистр
        String reversed = reverseString(s);

        return s.equals(reversed);
    }

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {//цикл for итерируется через элементы массива args. i это переменная-счетчик,
    // которая начинается с 0 и увеличивается на каждой итерации до тех пор, пока i не станет равным длине массива args.
            String s = args[i];
            if (isPalindrome(s)) {
                System.out.println(s + " является палиндромом.");
            } else {
                System.out.println(s + " не является палиндромом.");
            }
        }
    }
}