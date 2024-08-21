import java.util.Collection;

/**
 * MyLinkedList - это пользовательская реализация двусвязного списка (LinkedList).
 *
 * @param <E> тип элементов в этом списке
 */
public class MyLinkedList<E> {
    /**
     * Первый узел списка.
     */
    private Node<E> head;

    /**
     * Последний узел списка.
     */
    private Node<E> tail;

    /**
     * Размер списка.
     */
    private int size = 0;

    /**
     * Внутренний класс для представления узла двусвязного списка.
     *
     * @param <E> тип данных, хранимых в узле
     */
    private static class Node<E> {
        /**
         * Хранимый элемент.
         */
        E data;

        /**
         * Указатель на следующий узел.
         */
        Node<E> next;

        /**
         * Указатель на предыдущий узел.
         */
        Node<E> prev;


        Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * Конструктор, который создает пустой список.
     */
    public MyLinkedList() {
    }

    /**
     * Конструктор, который создает новый список на основе другой коллекции.
     *
     * @param c коллекция, из которой будут скопированы элементы
     * @throws NullPointerException если коллекция равна null
     */
    public MyLinkedList(Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }
        for (E o : c)
            add(o);
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param e элемент, который нужно добавить
     * @return true, если элемент добавлен
     * @complexity O(1)
     */
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    /**
     * Вспомогательный метод для добавления элемента в конец списка.
     *
     * @param e элемент, который нужно добавить
     */
    private void linkLast(E e) {
        Node<E> prevTail = tail;
        Node<E> newNode = new Node<>(prevTail, e, null);
        tail = newNode;
        if (prevTail == null) {
            head = newNode;
        } else {
            prevTail.next = newNode;
        }
        size++;
    }

    /**
     * Добавляет элемент в список по указанному индексу.
     *
     * @param index   индекс, на который нужно вставить элемент
     * @param element элемент, который нужно добавить
     * @throws IndexOutOfBoundsException если индекс находится за пределами списка
     * @complexity O(n), best -> O(1)
     */
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс за пределами допустимого диапазона");
        }
        if (index == size) {
            linkLast(element);
        } else {
            linkBefore(element, getNode(index));
        }
    }

    /**
     * Вспомогательный метод для добавления элемента перед указанным узлом.
     *
     * @param e    элемент, который нужно добавить
     * @param succ узел, перед которым нужно вставить новый узел
     */
    private void linkBefore(E e, Node<E> succ) {
        Node<E> pred = succ.prev;
        Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null) {
            head = newNode;
        } else {
            pred.next = newNode;
        }
        size++;
    }

    /**
     * Удаляет первый найденный элемент, равный указанному объекту.
     *
     * @param o объект, который нужно удалить
     * @return true, если элемент был удален
     * @complexity O(n)
     */
    public boolean remove(Object o) {
        for (Node<E> x = head; x != null; x = x.next) {
            if (o.equals(x.data)) {
                unlink(x);
                return true;
            }
        }
        return false;
    }

    /**
     * Удаляет элемент в указанной позиции в этом списке.
     *
     * @param index индекс элемента, который нужно удалить
     * @return элемент, который был удален
     * @throws IndexOutOfBoundsException если индекс находится за пределами списка
     * @complexity O(n)
     */
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс за пределами допустимого диапазона");
        }
        return unlink(getNode(index));
    }

    /**
     * Вспомогательный метод для удаления узла из списка.
     *
     * @param x узел, который нужно удалить
     * @return данные, хранящиеся в удаляемом узле
     */
    private E unlink(Node<E> x) {
        E element = x.data;
        Node<E> next = x.next;
        Node<E> prev = x.prev;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.data = null;
        size--;
        return element;
    }

    /**
     * Возвращает элемент, находящийся в указанной позиции в этом списке.
     *
     * @param index индекс элемента, который нужно вернуть
     * @return элемент на указанной позиции
     * @throws IndexOutOfBoundsException если индекс находится за пределами списка
     * @complexity O(n)
     */
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс за пределами допустимого диапазона");
        }
        return getNode(index).data;
    }

    /**
     * Заменяет элемент в указанной позиции в этом списке на указанный элемент.
     *
     * @param index   индекс элемента, который нужно заменить
     * @param element элемент, который будет сохранен в указанной позиции
     * @return элемент, ранее находившийся в указанной позиции
     * @throws IndexOutOfBoundsException если индекс находится за пределами списка
     * @complexity O(n)
     */
    public E set(int index, E element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс за пределами допустимого диапазона");
        }
        Node<E> node = getNode(index);
        E oldElement = node.data;
        node.data = element;
        return oldElement;
    }

    /**
     * Возвращает представление части этого списка между указанными fromIndex, включительно, и toIndex, исключительно.
     *
     * @param fromIndex начальная точка subList (включительно)
     * @param toIndex   конечная точка subList (исключительно)
     * @return список, содержащий указанный диапазон элементов
     * @throws IndexOutOfBoundsException если индексы находятся за пределами списка
     * @complexity O(n)
     */
    public MyLinkedList<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex || (toIndex - fromIndex) > size) {
            throw new IndexOutOfBoundsException("Индекс за пределами допустимого диапазона");
        }
        MyLinkedList<E> subList = new MyLinkedList<>();
        Node<E> current = getNode(fromIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(current.data);
            current = current.next;
        }
        return subList;
    }

    /**
     * Возвращает количество элементов в этом списке.
     *
     * @return количество элементов в этом списке
     * @complexity O(1)
     */
    public int size() {
        return size;
    }

    /**
     * Вспомогательный метод для получения узла на определенном индексе.
     *
     * @param index индекс узла, который нужно получить
     * @return узел на указанной позиции
     */
    private Node<E> getNode(int index) {
        Node<E> x;
        if (index < (size >> 1)) {
            x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            x = tail;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
        }
        return x;
    }
}