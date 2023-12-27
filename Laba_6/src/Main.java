public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(5); // создание стека типа Integer с размером 5

        stack.push(10); // добавление элемента в стек
        stack.push(20);
        stack.push(30);

        System.out.println("Верхний элемент: "+ stack.peek()); // получение верхнего элемента стека (30)

        System.out.println("Новый верхний элемент: "+stack.pop()); // удаление и получение верхнего элемента стека (30)
        System.out.println("Новый верхний элемент: "+stack.pop()); // удаление и получение верхнего элемента стека (20)

        stack.push(40); // добавление элемента в стек
        stack.push(50);
        stack.push(60);

        System.out.println(stack.pop()); // удаление и получение верхнего элемента стека (60)

        System.out.println(stack.peek()); // получение верхнего элемента стека (50)
    }
}