import java.util.LinkedList;

// Класс, представляющий информацию о книге
class Book {
    private String title;
    private String author;
    private int copies;

    public Book(String title, String author, int copies) {
        this.title = title;
        this.author = author;
        this.copies = copies;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }
}

// Класс, реализующий хэш-таблицу с методом цепочек
public class HashTable {
    private static final int TABLE_SIZE = 1000;
    private LinkedList<Book>[] table;

    public HashTable() {
        table = new LinkedList[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    // Метод для вычисления хэша
    private int hash(String key) {
        int hash = key.hashCode();
        return Math.abs(hash % TABLE_SIZE);
    }

    // Метод для вставки (добавления) книги в таблицу
    public void put(String isbn, Book book) {
        int index = hash(isbn);
        LinkedList<Book> bucket = table[index];

        // Проверяем, есть ли уже книга с таким ISBN в списке
        for (Book b : bucket) {
            if (isbn.equals(b.getTitle())) {
                // Книга уже существует, обновляем количество копий
                b.setCopies(book.getCopies());
                return;
            }
        }

        // Если книги с таким ISBN нет в списке, добавляем её
        bucket.add(book);
    }

    // Метод для получения книги по ISBN
    public Book get(String isbn) {
        int index = hash(isbn);
        LinkedList<Book> bucket = table[index];

        // Ищем книгу по ISBN
        for (Book book : bucket) {
            if (isbn.equals(book.getTitle())) {
                return book;
            }
        }

        return null; // Книга не найдена
    }

    // Метод для удаления книги по ISBN
    public void remove(String isbn) {
        int index = hash(isbn);
        LinkedList<Book> bucket = table[index];

        // Ищем книгу по ISBN и удаляем её
        for (Book book : bucket) {
            if (isbn.equals(book.getTitle())) {
                bucket.remove(book);
                return;
            }
        }
    }

    // Метод для получения количества элементов в таблице
    public int size() {
        int count = 0;
        for (LinkedList<Book> bucket : table) {
            count += bucket.size();
        }
        return count;
    }

    // Метод для проверки, пуста ли таблица
    public boolean isEmpty() {
        return size() == 0;
    }
}
