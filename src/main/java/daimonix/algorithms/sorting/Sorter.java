package daimonix.algorithms.sorting;

import java.util.Comparator;

public abstract class Sorter {
    public static <T extends Comparable<T>> boolean isSorted(T[] array) {
        return isSorted(array, Comparator.naturalOrder());
    }

    public static <T> boolean isSorted(T[] array, Comparator<? super T> comparator) {
        if (array == null)
            return true;
        for (int i = 0; i < array.length - 1; i++) {
            if (comparator.compare(array[i + 1], array[i]) < 0)
                return false;
        }
        return true;
    }

    public <T extends Comparable<T>> void sort(T[] array) {
        sort(array, Comparator.naturalOrder());
    }

    public abstract <T> void sort(T[] array, Comparator<? super T> comparator);
}
