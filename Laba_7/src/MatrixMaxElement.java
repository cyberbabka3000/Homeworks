import java.util.Random;

class MaxElementFinder implements Runnable {
    private int[] row;
    private int maxElement;

    public MaxElementFinder(int[] row) {
        this.row = row;
        this.maxElement = Integer.MIN_VALUE;
    }

    public int getMaxElement() {
        return maxElement;
    }

    @Override
    public void run() {
        for (int element : row) {
            if (element > maxElement) {
                maxElement = element;
            }
        }
    }
}

public class MatrixMaxElement {
    public static void main(String[] args) throws InterruptedException {
        int[][] matrix = generateMatrix(5, 5);

        int maxElement = findMaxElement(matrix);

        System.out.println("Max element: " + maxElement);
    }

    private static int[][] generateMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(100);
            }
        }

        return matrix;
    }

    private static int findMaxElement(int[][] matrix) throws InterruptedException {
        int maxElement = Integer.MIN_VALUE;
        MaxElementFinder[] finders = new MaxElementFinder[matrix.length];
        Thread[] threads = new Thread[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            finders[i] = new MaxElementFinder(matrix[i]);
            threads[i] = new Thread(finders[i]);
            threads[i].start();
        }

        for (int i = 0; i < matrix.length; i++) {
            threads[i].join();
            int rowMaxElement = finders[i].getMaxElement();
            if (rowMaxElement > maxElement) {
                maxElement = rowMaxElement;
            }
        }

        return maxElement;
    }
}