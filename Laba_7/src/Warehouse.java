import java.util.concurrent.Semaphore;

public class Warehouse {
    private static final int NUM_OF_LOADERS = 3;
    private static final int MAX_WEIGHT = 150;
    private static final int NUM_OF_PRODUCTS = 10;

    private static final Semaphore weightSemaphore = new Semaphore(MAX_WEIGHT);
    private static final Semaphore loaderSemaphore = new Semaphore(NUM_OF_LOADERS);

    public static void main(String[] args) {
        Thread[] loaders = new Thread[NUM_OF_LOADERS];

        for (int i = 0; i < NUM_OF_LOADERS; i++) {
            loaders[i] = new Thread(new Loader(i));
            loaders[i].start();
        }

        try {
            for (int i = 0; i < NUM_OF_LOADERS; i++) {
                loaders[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Loader implements Runnable {
        private int id;

        public Loader(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            int weightSum = 0;
            int[] products = new int[NUM_OF_PRODUCTS];

            for (int i = 0; i < NUM_OF_PRODUCTS; i++) {
                try {
                    weightSemaphore.acquire(); // заблокировать доступ к складу

                    int weight = (int) (Math.random() * 50) + 1; // случайный вес товара от 1 до 50 кг
                    weightSum += weight;
                    products[i] = weight;

                    if (weightSum >= MAX_WEIGHT) {
                        System.out.println("Грузчик " + id + " собрал " + weightSum + " кг товаров");
                        weightSum = 0;
                        loaderSemaphore.acquire(); // отправиться на другой склад и начать разгрузку

                        // имитация разгрузки
                        Thread.sleep(2000);

                        loaderSemaphore.release(); // разрешить следующему грузчику начать сборку и отправиться на другой склад
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    weightSemaphore.release(); // разблокировать доступ к складу
                }
            }
        }
    }
}