// Абстрактный класс
abstract class Transport {
    private String brand;
    private int year;
    private double price;
    private static int count = 0; // Добавляем статическую переменную

    public Transport(String brand, int year, double price) {
        this.brand = brand;
        this.year = year;
        this.price = price;
        count++; // Увеличиваем счетчик при создании объекта
    }

    // Геттеры и сеттеры
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Абстрактный метод
    public abstract void start();

    // Метод с перегрузкой
    public void accelerate(int speed) {
        System.out.println(brand + " is accelerating to " + speed + " mph.");
    }

    // Статический метод для получения количества созданных объектов
    public static int getCount() {
        return count;
    }
}

// Дочерние классы
class PassengerCar extends Transport {
    private int passengerCapacity;

    public PassengerCar(String brand, int year, double price, int passengerCapacity) {
        super(brand, year, price);
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public void start() {
        System.out.println(getBrand() + " starts smoothly.");
    }

    // Дополнительный метод
    public void park() {
        System.out.println(getBrand() + " is parked.");
    }
}

class Truck extends Transport {
    private double maxCargoWeight;

    public Truck(String brand, int year, double price, double maxCargoWeight) {
        super(brand, year, price);
        this.maxCargoWeight = maxCargoWeight;
    }

    @Override
    public void start() {
        System.out.println(getBrand() + " roars to life.");
    }

    // Дополнительный метод
    public void loadCargo(double weight) {
        System.out.println("Loading " + weight + " tons of cargo into " + getBrand() + ".");
    }
}

class Motorcycle extends Transport {
    private boolean hasSidecar;

    public Motorcycle(String brand, int year, double price, boolean hasSidecar) {
        super(brand, year, price);
        this.hasSidecar = hasSidecar;
    }

    @Override
    public void start() {
        System.out.println(getBrand() + " revs its engine.");
    }
}

public class Main {
    public static void main(String[] args) {
        PassengerCar car = new PassengerCar("Toyota Camry", 2022, 25000.0, 5);
        Truck truck = new Truck("Ford F-150", 2020, 40000.0, 2.5);
        Motorcycle motorcycle = new Motorcycle("Harley-Davidson", 2021, 15000.0, true);

        // Вызов методов и дополнительных методов
        car.start();
        car.accelerate(60);
        car.park();

        truck.start();
        truck.accelerate(50);
        truck.loadCargo(2.0);

        motorcycle.start();
        motorcycle.accelerate(70);

        // Использование статической переменной для подсчета созданных объектов
        System.out.println("Total transports created: " + Transport.getCount());
    }
}