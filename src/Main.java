import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Тестирование MyArrayList
        System.out.println("=== Тестирование MyArrayList ===");

        // Создаем пустой список
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        System.out.println("Начальный размер: " + myArrayList.size()); // Ожидаем 0

        // Добавляем элементы
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        System.out.println("Размер после добавления элементов: " + myArrayList.size()); // Ожидаем 3

        // Добавляем элемент на индекс 1
        myArrayList.add(1, 4);
        System.out.println("Элемент на индексе 1: " + myArrayList.get(1)); // Ожидаем 4

        // Удаляем элемент по индексу
        myArrayList.remove(2);
        System.out.println("Элемент на индексе 2 после удаления: " + myArrayList.get(2)); // Ожидаем 3

        // Получаем подсписок
        MyArrayList<Integer> subArrayList = myArrayList.subList(1, 3);
        System.out.println("Размер подсписка: " + subArrayList.size()); // Ожидаем 2

        // Создаем список на основе другой коллекции
        List<Integer> collection = Arrays.asList(10, 20, 30);
        MyArrayList<Integer> myArrayListFromCollection = new MyArrayList<>(collection);
        System.out.println("Размер списка из коллекции: " + myArrayListFromCollection.size()); // Ожидаем 3

        // Тестирование MyLinkedList
        System.out.println("\n=== Тестирование MyLinkedList ===");

        // Создаем пустой список
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        System.out.println("Начальный размер: " + myLinkedList.size()); // Ожидаем 0

        // Добавляем элементы
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");
        System.out.println("Размер после добавления элементов: " + myLinkedList.size()); // Ожидаем 3

        // Добавляем элемент на индекс 1
        myLinkedList.add(1, "d");
        System.out.println("Элемент на индексе 1: " + myLinkedList.get(1)); // Ожидаем "d"

        // Удаляем элемент по значению
        myLinkedList.remove("b");
        System.out.println("Элемент на индексе 2 после удаления: " + myLinkedList.get(2)); // Ожидаем "c"

        // Удаляем элемент по индексу
        myLinkedList.remove(1);
        System.out.println("Размер после удаления по индексу: " + myLinkedList.size()); // Ожидаем 2

        // Получаем подсписок
        MyLinkedList<String> subLinkedList = myLinkedList.subList(0, 1);
        System.out.println("Размер подсписка: " + subLinkedList.size()); // Ожидаем 1

        // Создаем список на основе другой коллекции
        List<String> stringCollection = Arrays.asList("x", "y", "z");
        MyLinkedList<String> myLinkedListFromCollection = new MyLinkedList<>(stringCollection);
        System.out.println("Размер списка из коллекции: " + myLinkedListFromCollection.size()); // Ожидаем 3
    }
}