import java.util.Collection;

/**
 * MyArrayList - это моя реализация класса ArrayList.
 *
 * @param <E> тип элементов в этом списке
 */
public class MyArrayList<E> {
    /**
     * Хранимые элементы.
     */
    private Object[] elements;

    /**
     * Размер списка.
     */
    private int size = 0;

    /**
     * Размер списка по умолчанию.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Конструктор, который создает пустой список.
     */
    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Конструктор, который создает пустой список заданного размера.
     *
     * @param initialCapacity размер списка
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            elements = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            elements = new Object[]{};
        } else {
            throw new IllegalArgumentException("Недопустимый размер: " + initialCapacity);
        }
    }

    /**
     * Конструктор, который создает новый список на основе другой коллекции.
     *
     * @param c коллекция, из которой будут скопированы элементы
     * @throws NullPointerException если коллекция равна null
     */
    public MyArrayList(Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }
        elements = new Object[DEFAULT_CAPACITY];
        for (E o : c)
            add(o);
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param e элемент, который нужно добавить
     * @return true, если элемент добавлен
     * @complexity O(1), worst -> O(n)
     */
    public boolean add(E e) {
        if (size == elements.length) {
            grow();
        }
        elements[size] = e;
        size++;
        return true;
    }

    /**
     * Добавляет элемент в список по указанному индексу.
     *
     * @param index   индекс, на который нужно вставить элемент
     * @param element элемент, который нужно добавить
     * @throws IndexOutOfBoundsException если индекс находится за пределами списка
     * @complexity O(n)
     */
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс за пределами допустимого диапазона");
        }
        if (size == elements.length) {
            grow();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
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
        E oldElement = elementsData(index);
        fastRemove(elements, index);
        return oldElement;
    }

    /**
     * Удаляет первый найденный элемент, равный указанному объекту.
     *
     * @param o объект, который нужно удалить
     * @return true, если элемент был удален
     * @complexity O(n)
     */
    public boolean remove(Object o) {
        for (int index = 0; index < size; index++) {
            if (o.equals(elements[index])) {
                fastRemove(elements, index);
                return true;
            }
        }
        return false;
    }

    /**
     * Вспомогательный метод для быстрого удаления элемента из массива.
     * Не проверяет границы и не возвращает удаляемое значение.
     *
     * @param es массив, из которого нужно удалить элемент
     * @param i  индекс элемента для удаления
     */
    private void fastRemove(Object[] es, int i) {
        int numMoved = size - i - 1;
        if (numMoved > 0) {
            System.arraycopy(es, i + 1, es, i, numMoved);
        }
        es[--size] = null;
    }

    /**
     * Возвращает элемент, находящийся в указанной позиции в этом списке.
     *
     * @param index индекс элемента, который нужно вернуть
     * @return элемент на указанной позиции
     * @throws IndexOutOfBoundsException если индекс находится за пределами списка
     * @complexity O(1)
     */
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс за пределами допустимого диапазона");
        }
        return elementsData(index);
    }

    @SuppressWarnings("unchecked")
    E elementsData(int index) {
        return (E) elements[index];
    }

    /**
     * Заменяет элемент в указанной позиции в этом списке на указанный элемент.
     *
     * @param index   индекс элемента, который нужно заменить
     * @param element элемент, который будет сохранен в указанной позиции
     * @return элемент, ранее находившийся в указанной позиции
     * @throws IndexOutOfBoundsException если индекс находится за пределами списка
     * @complexity O(1)
     */
    public E set(int index, E element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс за пределами допустимого диапазона");
        }
        E oldElement = elementsData(index);
        elements[index] = element;
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
    public MyArrayList<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex || (toIndex - fromIndex) > size) {
            throw new IndexOutOfBoundsException("Индекс за пределами допустимого диапазона");
        }
        MyArrayList<E> subList = new MyArrayList<>(toIndex - fromIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(elementsData(i));
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
     * Вспомогательный метод для увеличения размера внутреннего массива.
     * Увеличивает размер массива по правилу: (старый размер * 1.5) + 1.
     */
    private void grow() {
        int newCapacity = (int) ((elements.length * 1.5) + 1);
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }
}