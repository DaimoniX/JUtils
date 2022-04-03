package daimonix.utils;

import java.util.Comparator;

public class SortUtils {
    public static <T extends Comparable<T>> void BubbleSort(T[] array) {
        BubbleSort(array, Comparator.naturalOrder());
    }

    public static <T> void BubbleSort(T[] array, Comparator<? super T> comparator) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (comparator.compare(array[j], array[i]) > 0)
                    ArrayUtils.Swap(array, i, j);
            }
        }
    }

    public static <T extends Comparable<T>> void InsertionSort(T[] array) {
        InsertionSort(array, Comparator.naturalOrder());
    }

    public static <T> void InsertionSort(T[] array, Comparator<? super T> comparator) {
        for (int i = 1; i < array.length; i++) {
            T el = array[i];
            int j = i - 1;

            while (j >= 0 && comparator.compare(array[j], el) > 0) {
                ArrayUtils.Swap(array, j, j + 1);
                j--;
            }
            array[j + 1] = el;
        }
    }

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
}
