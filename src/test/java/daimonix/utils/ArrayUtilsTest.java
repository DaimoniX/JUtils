package daimonix.utils;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class ArrayUtilsTest {
    Integer[] array;

    @Before
    public void setUp() {
        array = new Integer[0];
    }

    @Test
    public void arrayCreation() {
        array = ArrayUtils.createSortedArray(4);
        assertArrayEquals(new Integer[] { 1, 2, 3, 4 }, array);
        array = ArrayUtils.createReversedArray(4);
        assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, array);
        array = ArrayUtils.createSortedArray(0);
        assertArrayEquals(new Integer[0], array);
        assertThrows(NegativeArraySizeException.class, () -> ArrayUtils.createSortedArray(-1));
    }

    @Test
    public void arrayToString() {
        assertEquals("[]", ArrayUtils.toString(array));
        array = null;
        assertEquals("null", ArrayUtils.toString(array));
        array = ArrayUtils.createSortedArray(8);
        assertEquals(Arrays.toString(array), ArrayUtils.toString(array));
        array = ArrayUtils.createSortedArray(3);
        assertEquals("[1::2::3]", ArrayUtils.toString(array, "::"));
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.toString(array, null));
    }
}
