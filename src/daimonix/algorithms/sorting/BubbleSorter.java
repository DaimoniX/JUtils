package daimonix.algorithms.sorting;

import java.util.Comparator;

import daimonix.utils.ArrayUtils;

public class BubbleSorter extends Sorter {

    @Override
    public <T> void Sort(T[] array, Comparator<? super T> comparator) {
        for (int i = 0; i < array.length; i++)
            for (int j = 1; j < array.length - i; j++)
                if (comparator.compare(array[j - 1], array[j]) > 0)
                    ArrayUtils.Swap(array, j, j - 1);
    }
}
