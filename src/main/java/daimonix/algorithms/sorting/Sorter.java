package daimonix.algorithms.sorting;

import java.util.Comparator;

public interface Sorter {
    /**
     * Checks is array is sorted with {@link Comparator}.naturalOrder().
     * @param <T> type of elements in array
     * @param array array to check
     * @return true if sorted in natural order
     */
    public static <T extends Comparable<T>> boolean isSorted(T[] array) {
        return isSorted(array, Comparator.naturalOrder());
    }

    /**
     * Checks is array is sorted with custom {@link Comparator}.
     * @param <T> type of elements in array
     * @param array array to check
     * @param comparator comparator
     * @return true if array is sorted with specified comparator
     */
    public static <T> boolean isSorted(T[] array, Comparator<? super T> comparator) {
        for (int i = 0; i < array.length - 1; i++) {
            if (comparator.compare(array[i + 1], array[i]) < 0)
                return false;
        }
        return true;
    }

    /**
     * Sorts the array with {@link Comparator}.naturalOrder().
     * @param <T> type of elements in array
     * @param array array to sort
     */
    public default <T extends Comparable<T>> void sort(T[] array) {
        sort(array, Comparator.naturalOrder());
    }

    /**
     * Sorts the array with specified {@link Comparator}.
     * @param <T> type of elements in array
     * @param array array to sort
     * @param comparator comparator for sorting
     */
    public abstract <T> void sort(T[] array, Comparator<? super T> comparator);
}
