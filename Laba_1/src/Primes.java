
public class Primes {
    public static void main(String[] args) {
        System.out.println("Простые числа меньше 100:");
        for (int i = 2; i < 100; i++) {//цикл начнется с числа 2) и будет продолжаться до тех пор, пока i меньше 100
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
    }

public static boolean isPrime(int n) {
    if (n <= 1) {
    return false; // 0 и 1 не считаются простыми числами
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false; // Если число делится нацело на какое-либо число от 2 до sqrt(n), то оно не простое
            }
        }
        return true;
    }
}