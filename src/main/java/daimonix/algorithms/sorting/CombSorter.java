package daimonix.algorithms.sorting;

import java.util.Comparator;

import daimonix.utils.ArrayUtils;

public class CombSorter implements Sorter {

    @Override
    public <T> void sort(T[] array, Comparator<? super T> comparator) {
        int gap = array.length;

        boolean sorted = false;

        while (gap != 1 || !sorted) {
            gap = gap * 10 / 13;
            if (gap < 1)
                gap = 1;

            sorted = true;

            for (int i = 0; i < array.length - gap; i++) {
                if (comparator.compare(array[i], array[i + gap]) > 0) {
                    sorted = false;

                    ArrayUtils.swap(array, i, i + gap);
                }
            }
        }
    }
}