package daimonix.algorithms.sorting;

import java.util.Comparator;

public abstract class Sorter {
    public static <T extends Comparable<T>> boolean IsSorted(T[] array) {
        return IsSorted(array, Comparator.naturalOrder());
    }

    public static <T> boolean IsSorted(T[] array, Comparator<? super T> comparator) {
        for (int i = 0; i < array.length - 1; i++) {
            if (comparator.compare(array[i + 1], array[i]) < 0)
                return false;
        }
        return true;
    }

    public <T extends Comparable<T>> void Sort(T[] array) {
        Sort(array, Comparator.naturalOrder());
    }

    public abstract <T> void Sort(T[] array, Comparator<? super T> comparator);
}
