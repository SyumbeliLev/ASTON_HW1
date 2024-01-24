package src;

import java.util.Arrays;

/**
 * CustomArrayList - простая реализация динамического массива на Java с возможностью сортировки слиянием.
 *
 * @param <T> тип элементов в списке, должен реализовывать интерфейс Comparable.
 */
public class CustomArrayList<T extends Comparable<T>> {

    /**
     * Размер массива по умолчанию.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Массив элементов.
     */
    private Object[] elements;

    /**
     * Текущий размер списка.
     */
    private int size;

    /**
     * Конструктор класса. Инициализирует массив и устанавливает начальный размер списка.
     */
    public CustomArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element добавляемый элемент.
     */
    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    /**
     * Добавляет элемент по указанному индексу.
     *
     * @param index   индекс, по которому следует вставить элемент.
     * @param element добавляемый элемент.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона.
     */
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента.
     * @return элемент по указанному индексу.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона.
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) elements[index];
    }

    /**
     * Удаляет первое вхождение указанного элемента.
     *
     * @param element элемент, который следует удалить.
     */
    public void remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                removeAt(i);
                return;
            }
        }
    }

    /**
     * Очищает весь список.
     */
    public void clear() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index индекс элемента, который следует удалить.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона.
     */
    private void removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
    }

    /**
     * Обеспечивает достаточную емкость массива.
     */
    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    /**
     * Возвращает текущий размер списка.
     *
     * @return текущий размер списка.
     */
    public int size() {
        return size;
    }

    /**
     * Выполняет сортировку слиянием для элементов списка.
     * Если список содержит более одного элемента, он будет отсортирован в порядке возрастания.
     */
    public void mergeSort() {
        if (size > 1) {
            T[] tempArray = (T[]) new Comparable[size];
            mergeSortHelper(0, size - 1, tempArray);
        }
    }

    /**
     * Рекурсивно выполняет сортировку слиянием.
     *
     * @param low       нижняя граница диапазона.
     * @param high      верхняя граница диапазона.
     * @param tempArray временный массив для хранения элементов в процессе слияния.
     */
    private void mergeSortHelper(int low, int high, T[] tempArray) {
        if (low < high) {
            int middle = (low + high) / 2;

            mergeSortHelper(low, middle, tempArray);
            mergeSortHelper(middle + 1, high, tempArray);

            merge(low, middle, high, tempArray);
        }
    }

    /**
     * Сливает две упорядоченные части массива в порядке возрастания.
     *
     * @param low       начальный индекс первой части.
     * @param middle    конечный индекс первой части.
     * @param high      конечный индекс второй части.
     * @param tempArray временный массив для хранения элементов в процессе слияния.
     */
    private void merge(int low, int middle, int high, T[] tempArray) {
        for (int i = low; i <= high; i++) {
            tempArray[i] = get(i);
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        while (i <= middle && j <= high) {
            if (tempArray[i].compareTo(tempArray[j]) <= 0) {
                elements[k] = tempArray[i];
                i++;
            } else {
                elements[k] = tempArray[j];
                j++;
            }
            k++;
        }

        while (i <= middle) {
            elements[k] = tempArray[i];
            i++;
            k++;
        }
    }
}
