
public class Main {
    public static void main(String[] args) {
        HashTable library = new HashTable();

        // Добавление книг в библиотеку
        Book book1 = new Book("ISBN123", "Author 1", 5);
        library.put("ISBN123", book1);

        Book book2 = new Book("ISBN456", "Author 2", 3);
        library.put("ISBN456", book2);

        // Получение книги по ISBN
        Book retrievedBook = library.get("ISBN123");
        if (retrievedBook != null) {
            System.out.println("Title: " + retrievedBook.getTitle());
            System.out.println("Author: " + retrievedBook.getAuthor());
            System.out.println("Copies: " + retrievedBook.getCopies());
        } else {
            System.out.println("Книга не найдена.");
        }

        // Удаление книги по ISBN
        library.remove("ISBN123");

        // Проверка размера и пустоты библиотеки
        System.out.println("Размер библиотеки: " + library.size());
        System.out.println("Библиотека пуста: " + library.isEmpty());
    }
}