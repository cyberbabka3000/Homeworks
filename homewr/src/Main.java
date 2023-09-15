
public class Main {
    public static void main(String[] args) {

        //10 задача
        int students1 = 5;
        int desks1 = 2;
        int shortage1 = tables(students1, desks1);
        System.out.println("Не хватает " + shortage1 + " столов.");

        //9 задача
        int numberOfTickets1 = 70;
        double ticketPriceWithCommission1 = 1500;
        double revenue1 = ticketSaler(numberOfTickets1, ticketPriceWithCommission1);
        System.out.println("Общая выручка: " + revenue1);

        //8 задача
        int num1 = 36;
        int num2 = 48;

        int gcd = findGCD(num1, num2);
        System.out.println("Наибольший общий делитель чисел " + num1 + " и " + num2 + " равен " + gcd);
        //7 задача

        int n = 5; // Здесь можно заменить на любое другое число, для которого нужно вычислить факториал
        long factorial = calculateFactorial(n);
        System.out.println("Факториал " + n + " равен " + factorial);


        //6 задача
        double fabricLengthInMeters = 10.0; // Длина ткани в метрах
        double widthOfPieceInMeters = 1.5; // Ширина детали в метрах
        double lengthOfPieceInMeters = 2.0; // Длина детали в метрах

        int numberOfBeddings = calculateBeddings(fabricLengthInMeters, widthOfPieceInMeters, lengthOfPieceInMeters);
        System.out.println("Количество пододеяльников, которые можно сшить: " + numberOfBeddings);

        //5 задача
        int a = 10;
        int b = 5;
        int result = findLargerNumber(5, 9);
        System.out.println("Большее число: " + result);

        //4 задача

        int x = 5;
        int y = 5;
        int z = 5;

        String triangleType = determineTriangleType(5, 5, 5);
        System.out.println("Тип треугольника: " + triangleType);

        //3 задача

        int boxesCount = 3; // Количество коробок
        int bagsCount = 4;   // Количество мешков
        int barrelsCount = 2; // Количество бочек

        int totalItems = calculateTotalItems(boxesCount, bagsCount, barrelsCount);
        System.out.println("Общее количество товаров на складе: " + totalItems);

        //2 задача

        int trainingTimeInMinutes = 15; // Пример: время тренировки в минутах
        int intensityLevel = 1; // Пример: интенсивность тренировки (1 - низкая, 2 - средняя, 3 - высокая)

        double caloriesBurned = calculateCaloriesBurned(trainingTimeInMinutes, intensityLevel);
        System.out.println("Количество сожженных калорий: " + caloriesBurned + " ккал");

        //1 задача

        int gallons = 10; // Пример: количество галлонов для преобразования
        float liters = convert(gallons);
        System.out.println(gallons + " галлонов = " + liters + " литров");

    }


    // 1задача
    public static float convert(int x) {
        float gallon = 3.785f;
        return x * gallon;
    }

    // 2 задача
    public static double calculateCaloriesBurned(int minutes, int intensity) {
        double caloriesPerMinute = 0;

        switch (intensity) {
            case 1:
                caloriesPerMinute = 1.0; // Низкая интенсивность
                break;
            case 2:
                caloriesPerMinute = 2.0; // Средняя интенсивность
                break;
            case 3:
                caloriesPerMinute = 3.0; // Высокая интенсивность
                break;
        }

        return caloriesPerMinute * minutes;
    }

    //3 задача
    public static int calculateTotalItems(int boxesCount, int bagsCount, int barrelsCount) {
        // Количество товаров в каждом типе емкости
        int itemsInBox = 20;
        int itemsInBag = 50;
        int itemsInBarrel = 100;

        // Общее количество товаров на складе
        int totalItems = (boxesCount * itemsInBox) + (bagsCount * itemsInBag) + (barrelsCount * itemsInBarrel);

        return totalItems;
    }

    // 4 задача
    public static String determineTriangleType(int x, int y, int z) {
        if (x <= 0 || y <= 0 || z <= 0) {
            return "Не является треугольником"; // Отрицательные или нулевые стороны не могут образовать треугольник.
        }

        if (x + y <= z || x + z <= y||  y + z <= x) {
            return "Не является треугольником"; // Неравенство треугольника: сумма любых двух сторон должна 
            //быть больше третьей стороны.
        }

        if (x == y && y == z) {
            return "Равносторонний треугольник";
        } else if (x == y || x == z|| y == z) {
            return "Равнобедренный треугольник";
        } else {
            return "Разносторонний треугольник";
        }
    }

    //5 задача
    public static int findLargerNumber(int a, int b) {
        // Используем тернарный оператор для сравнения чисел и возврата большего
        int largerNumber = (a > b) ? a : b;
        return largerNumber;
    }

    //6 задача
    public static int calculateBeddings(double fabricLengthInMeters, double widthOfPieceInMeters, double lengthOfPieceInMeters) {
        if (fabricLengthInMeters <= 0 ||  widthOfPieceInMeters <= 0 || lengthOfPieceInMeters <= 0) {
            throw new IllegalArgumentException("Неверные входные данные. Длина и ширина должны быть больше нуля.");
        }


        double fabricAreaInSquareMeters = fabricLengthInMeters * widthOfPieceInMeters * 2; // Учитываем оба слоя ткани
        double pieceAreaInSquareMeters = widthOfPieceInMeters * lengthOfPieceInMeters;

        int numberOfBeddings = (int) (fabricAreaInSquareMeters / pieceAreaInSquareMeters);

        return numberOfBeddings;
    }

    //7 задача
    public static long calculateFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Факториал отрицательного числа не определен");
        }

        long result = 1; // хранение результата факториала
        for (int i = 1; i <= n; i++) { //цикл от i до n включительно, i++ увеличивает переменную на 1
            result *= i;//умножение с обратным присвоением результата
        }

        return result;
    }

    //8 задача по методу Евклида
    public static int findGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return findGCD(b, a % b);//когда меньшее число точно делит большее, меньшее число является gcd двух заданных чисел
    }//вычисляется остаток от деления n1 на n2, остаток передаётся в качестве первого аргумента. продолжается пока b не равняется 0

    //9 задача
    public static double ticketSaler(int numberOfTickets, double ticketPriceWithCommission) {
        // Вычисляем общую выручку, умножая количество билетов на стоимость одного билета
        double totalRevenue = numberOfTickets * ticketPriceWithCommission;
        double commission = totalRevenue * 0.28;
        totalRevenue -= commission;// вычитается значение разницы и записывается в totalRevenue
        return totalRevenue;
    }

    // 10 задача
    public static int tables(int students, int desks) {
        // Рассчитываем, сколько столов необходимо
        int tablesNeeded = (students + 1) / 2;

        // Вычисляем, сколько столов не хватает
        int shortage = Math.max(0, tablesNeeded - desks);//метод определения наибольшего из двух чисел, 
        //здесь первый аргумент равен 0, а второй разнице

        return shortage;
    }
}