package daimonix.algorithms.sorting;

import daimonix.utils.ArrayUtils;
import java.util.Comparator;

public class SelectionSorter extends Sorter {

    @Override
    public <T> void Sort(T[] array, Comparator<? super T> comparator) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++)
                if (comparator.compare(array[i], array[j]) > 0)
                    min = j;
            ArrayUtils.Swap(array, i, min);
        }
    }
}
