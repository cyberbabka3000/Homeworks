import java.util.*;

public class SalesTracker {
    private TreeSet<Product> soldProducts; // коллекция проданных товаров

    public SalesTracker() {
        soldProducts = new TreeSet<>();
    }

    // Метод для добавления проданного товара в коллекцию
    public void addProduct(Product product) {
        soldProducts.add(product);
    }

    // Метод для вывода списка проданных товаров
    public void displaySoldProducts() {
        System.out.println("Список проданных товаров:");
        for (Product product : soldProducts) {
            System.out.println(product);
        }
    }

    // Метод для подсчета общей суммы продаж
    public double calculateTotalSales() {
        double totalSales = 0.0;
        for (Product product : soldProducts) {
            totalSales += product.getPrice();
        }
        return totalSales;
    }

    // Метод для поиска наиболее популярного товара
    public Product findMostPopularProduct() {
        Product mostPopular = null;
        int maxQuantity = 0;
        for (Product product : soldProducts) {
            if (product.getQuantity() > maxQuantity) {
                mostPopular = product;
                maxQuantity = product.getQuantity();
            }
        }
        return mostPopular;
    }

    public static void main(String[] args) {
        SalesTracker tracker = new SalesTracker();

        // Пример добавления проданных товаров
        Product p1 = new Product("Телефон", 500.0, 10);
        Product p2 = new Product("Планшет", 800.0, 5);
        Product p3 = new Product("Ноутбук", 1200.0, 3);

        tracker.addProduct(p1);
        tracker.addProduct(p2);
        tracker.addProduct(p3);

        // Вывод списка проданных товаров
        tracker.displaySoldProducts();

        // Подсчет общей суммы продаж
        double totalSales = tracker.calculateTotalSales();
        System.out.println("Общая сумма продаж: " + totalSales);

        // Поиск наиболее популярного товара
        Product mostPopularProduct = tracker.findMostPopularProduct();
        System.out.println("Наиболее популярный товар: " + mostPopularProduct);
    }
}

// Класс для представления продукта
class Product implements Comparable<Product> {
    private String name;    // название товара
    private double price;   // цена товара
    private int quantity;   // количество товара

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int compareTo(Product otherProduct) {
        return name.compareTo(otherProduct.getName());
    }

    @Override
    public String toString() {
        return "Название: " + name + ", Цена: " + price + ", Количество: " + quantity;
    }
}