package daimonix.algorithms.sorting;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import daimonix.utils.ArrayUtils;

public class SortersTest {
    private class ArrayFactory {
        private long seed;

        public ArrayFactory() {
            this(System.nanoTime());
        }

        public ArrayFactory(long seed) {
            this.seed = seed;
        }

        public Integer[] GetArray(String mode, int size) {
            switch (mode) {
                case "sorted":
                    return ArrayUtils.createSortedArray(size);
                case "random":
                    return ArrayUtils.createRandomArray(size, seed);
                case "reversed":
                    return ArrayUtils.createReversedArray(size);
                default:
                    return null;
            }
        }
    }

    private ArrayFactory arrayFactory;

    @Before
    public void setUp() {
        arrayFactory = new ArrayFactory();
    }

    @Test
    public void isSortedTest() {
        assertTrue(Sorter.isSorted(arrayFactory.GetArray("sorted", 10)));
        assertFalse(Sorter.isSorted(arrayFactory.GetArray("reversed", 10)));
        assertTrue(Sorter.isSorted(new Integer[0]));
        assertTrue(Sorter.isSorted(null));
    }

    @Test
    public void fullTest() {
        Integer[] testSizes = new Integer[] { 1024, 2048, 4096, 8192, 16384 };
        Sorter[] sorters = new Sorter[] { new BubbleSorter(), new InsertionSorter(), new SelectionSorter(),
                new CombSorter(), new ShellSorter(), new HeapSorter(), new MergeSorter(), new QuickSorter() };
        String[] modes = new String[] { "sorted", "random", "reversed" };

        for (Sorter sorter : sorters) {
            for (Integer size : testSizes) {
                for (String mode : modes) {
                    Integer[] array = arrayFactory.GetArray(mode, size);
                    sorter.sort(array);
                    assertTrue(Sorter.isSorted(array));
                }
            }
        }
    }
}
