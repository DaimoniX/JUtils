package daimonix.algorithms.sorting;

import java.util.Comparator;
import daimonix.utils.ArrayUtils;

public class InsertionSorter extends Sorter {

    @Override
    public <T> void Sort(T[] array, Comparator<? super T> comparator) {
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
}
