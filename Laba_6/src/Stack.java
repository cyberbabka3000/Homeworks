public class Stack<T> {
    private int size; // размер стека
    private T[] stackArray; // массив для хранения элементов стека
    private int top; // индекс верхнего элемента стека

    // конструктор класса
    public Stack(int size) {
        this.size = size;
        this.stackArray = (T[]) new Object[size];
        this.top = -1;
    }

    // метод для добавления элемента в стек
    public void push(T element) {
        if (top < size - 1) {
            stackArray[++top] = element;
        } else {
            System.out.println("Стек переполнен");
        }
    }

    // метод для удаления элемента из стека
    public T pop() {
        if (top >= 0) {
            return stackArray[top--];
        } else {
            System.out.println("Стек пуст");
            return null;
        }
    }

    // метод для получения верхнего элемента стека без его удаления
    public T peek() {
        if (top >= 0) {
            return stackArray[top];
        } else {
            System.out.println("Стек пуст");
            return null;
        }
    }
}