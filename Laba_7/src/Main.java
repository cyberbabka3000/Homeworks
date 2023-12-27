import java.util.Random;


public class Main {
    private static final int SIZE = 1000000; // размер массива
    private static final int THREADS = 2; // количество потоков

    public static void main(String[] args) {
        int[] array = generateArray(SIZE); // генерируем массив случайных чисел
        int[] results = new int[THREADS]; // массив для хранения результатов

        SumThread[] threads = new SumThread[THREADS]; // create an array to store references to threads
        for (int i = 0; i < THREADS; i++) {
            int startIndex = i * array.length / THREADS; // начальный индекс для текущего потока
            int endIndex = (i + 1) * array.length / THREADS; // конечный индекс для текущего потока
            threads[i] = new SumThread(array, startIndex, endIndex, results, i); // сохраняем ссылку на поток
            threads[i].start(); // запускаем поток
        }

// ожидаем завершения работы всех потоков
        for (int i = 0; i < THREADS; i++) {
            try {
                threads[i].join(); // join the threads
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // складываем результаты из всех потоков в главном потоке
        int sum = 0;
        for (int result : results) {
            sum += result;
        }

        System.out.println("Сумма элементов: " + sum);
    }

    private static int[] generateArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100); // случайные числа от 0 до 99
        }
        return array;
    }
}

class SumThread extends Thread {
    private final int[] array;
    private final int startIndex;
    private final int endIndex;
    private final int[] results;
    private final int threadIndex;

    public SumThread(int[] array, int startIndex, int endIndex, int[] results, int threadIndex) {
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.results = results;
        this.threadIndex = threadIndex;
    }

    @Override
    public void run() {
        int sum = 0;

        // вычисляем сумму элементов массива для текущего потока
        for (int i = startIndex; i < endIndex; i++) {
            sum += array[i];
        }

        // сохраняем результат в массиве
        results[threadIndex] = sum;
    }
}