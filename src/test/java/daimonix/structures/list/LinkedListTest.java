package daimonix.structures.list;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
    List<Integer> list;

    @Before
    public void setUp() {
        list = new LinkedList<>();
    }

    @Test
    public void toArray() {
        for (int i = 1; i <= 5; i++)
            list.add(i);
        assertArrayEquals(new Integer[] {1, 2, 3, 4, 5 }, list.toArray());
        list.removeAll();
        assertArrayEquals(new Integer[0], list.toArray());
    }

    @Test
    public void remove() {
        for (int i = 1; i <= 5; i++)
            list.add(i);
        list.removeAt(0);
        assertArrayEquals(new Integer[] { 2, 3, 4, 5 }, list.toArray(new Integer[0]));
        list.removeAt(list.size() - 1);
        assertArrayEquals(new Integer[] { 2, 3, 4 }, list.toArray(new Integer[0]));
        list.remove(3);
        assertArrayEquals(new Integer[] { 2, 4 }, list.toArray(new Integer[0]));
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(10));
        list.removeAll();
        assertTrue(list.isEmpty());
        assertThrows(NullPointerException.class, () -> list.removeAt(0));
    }

    @Test
    public void set() {
        for (int i = 1; i <= 10; i++)
            list.add(i);
        Integer expected = -1;
        list.set(expected, 5);
        assertEquals(expected, list.get(5));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, 20));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, -1));
        list.removeAll();
        assertThrows(NullPointerException.class, () -> list.set(0, 0));
    }

    @Test
    public void listToString() {
        assertEquals("LinkedList{}", list.toString());
        for (int i = 1; i <= 10; i++)
            list.add(i);
        assertEquals("LinkedList{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}", list.toString());
        list.set(null, 0);
        assertEquals("LinkedList{null, 2, 3, 4, 5, 6, 7, 8, 9, 10}", list.toString());
    }
}
