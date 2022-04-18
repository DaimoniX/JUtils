package daimonix.algorithms.sorting;

import java.util.Comparator;

import daimonix.utils.ArrayUtils;

public class HeapSorter extends Sorter {

    private <T> void buildHeap(T[] array, Comparator<? super T> comparator, int n, int i) {
        int root = i;
        int l = i * 2 + 1;
        int r = i * 2 + 2;

        if (l < n && comparator.compare(array[l], array[root]) > 0)
            root = l;

        if (r < n && comparator.compare(array[r], array[root]) > 0)
            root = r;

        if (root == i)
            return;

        ArrayUtils.swap(array, i, root);
        buildHeap(array, comparator, n, root);
    }

    @Override
    public <T> void sort(T[] array, Comparator<? super T> comparator) {
        for (int i = array.length / 2 - 1; i >= 0; i--)
            buildHeap(array, comparator, array.length, i);

        for (int i = array.length - 1; i > 0; i--) {
            ArrayUtils.swap(array, 0, i);
            buildHeap(array, comparator, i, 0);
        }
    }

}
