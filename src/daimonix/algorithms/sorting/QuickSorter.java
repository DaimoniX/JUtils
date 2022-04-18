package daimonix.algorithms.sorting;

import java.util.Comparator;

import daimonix.utils.ArrayUtils;

public class QuickSorter extends Sorter {
    
    private <T> void quickSort(T[] array, Comparator<? super T> comparator, int left, int right) {
        T pivot = array[(left + right) / 2];
        int i = left;
        int j = right;

        while (i <= j) {
            while (comparator.compare(array[i], pivot) < 0) {
                i++;
            }
            while (comparator.compare(array[j], pivot) > 0) {
                j--;
            }
            if (i <= j) {
                ArrayUtils.swap(array, i, j);
                i++;
                j--;
            }
        }

        if (left < j)
            quickSort(array, comparator, left, j);
        if (i < right)
            quickSort(array, comparator, i, right);
    }

    @Override
    public <T> void sort(T[] array, Comparator<? super T> comparator) {
        quickSort(array, comparator, 0, array.length - 1);
    }
}
