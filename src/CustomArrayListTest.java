package src;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CustomArrayListTest {

    @Test
    public void testAddAndGet() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void testAddAtIndex() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("orange");

        list.add(1, "kiwi");

        assertEquals(4, list.size());
        assertEquals("apple", list.get(0));
        assertEquals("kiwi", list.get(1));
        assertEquals("banana", list.get(2));
        assertEquals("orange", list.get(3));
    }

    @Test
    public void testRemove() {
        CustomArrayList<Double> list = new CustomArrayList<>();
        list.add(1.0);
        list.add(2.0);
        list.add(3.0);

        list.remove(2.0);

        assertEquals(2, list.size());
        assertEquals(1.0, list.get(0));
        assertEquals(3.0, list.get(1));
    }

    @Test
    public void testRemoveFirstOccurrence() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("kiwi");
        list.add("banana");

        list.remove("banana");

        assertEquals(3, list.size());
        assertEquals("apple", list.get(0));
        assertEquals("kiwi", list.get(1));
        assertEquals("banana", list.get(2));
    }

    @Test
    public void testClear() {
        CustomArrayList<Character> list = new CustomArrayList<>();
        list.add('a');
        list.add('b');
        list.add('c');

        list.clear();

        assertEquals(0, list.size());
    }

    @Test
    public void testMergeSort() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(5);
        list.add(3);
        list.add(8);
        list.add(1);

        list.mergeSort();

        assertEquals(1, list.get(0)); // когда несколько ассертов используй assertAll
        assertEquals(3, list.get(1));
        assertEquals(5, list.get(2));
        assertEquals(8, list.get(3));
    }

    @Test
    public void testMergeSortEmptyList() {
        CustomArrayList<Integer> emptyList = new CustomArrayList<>();
        emptyList.mergeSort();

        assertEquals(0, emptyList.size());
    }

    @Test
    public void testMergeSortSingleElement() {
        CustomArrayList<Integer> singleElementList = new CustomArrayList<>();
        singleElementList.add(42);
        singleElementList.mergeSort();

        assertEquals(1, singleElementList.size());
        assertEquals(42, singleElementList.get(0));
    }
}
