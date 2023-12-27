import java.util.Scanner;

public class ArrayAverageCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите размер массива: ");
            int size = scanner.nextInt();

            int[] array = new int[size];
            System.out.println("Введите элементы массива:");

            // Ввод элементов массива
            for (int i = 0; i < size; i++) {
                array[i] = scanner.nextInt();
            }

            // Вычисление среднего арифметического
            int sum = 0;
            for (int i = 0; i < size; i++) {
                sum += array[i];
            }
            float avg = (float) sum / size;

            System.out.println("Среднее арифметическое: " + avg);
        }
        catch (NegativeArraySizeException e) {
            System.out.println("Размер массива не может быть отрицательным!");
        }
        catch (NumberFormatException e) {
            System.out.println("Один из элементов массива не является числом!");
        }
        catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }
}