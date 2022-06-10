package daimonix.algorithms.sorting;

import java.util.Comparator;

public class MergeSorter implements Sorter {

    @SuppressWarnings("unchecked")
    private <T> void merge(T[] array, Comparator<? super T> comparator, int left, int mid, int right) {
        int lLength = mid - left + 1;
        int rLength = right - mid;

        T[] lArr = (T[]) new Object[lLength];
        T[] rArr = (T[]) new Object[rLength];

        for (int i = 0; i < lLength; i++)
            lArr[i] = array[left + i];
        for (int j = 0; j < rLength; j++)
            rArr[j] = array[mid + j + 1];

        int i = 0;
        int j = 0;
        int k = left;

        while (i < lLength && j < rLength) {
            if (comparator.compare(lArr[i], rArr[j]) <= 0) {
                array[k] = lArr[i];
                i++;
            } else {
                array[k] = rArr[j];
                j++;
            }
            k++;
        }

        while (i < lLength) {
            array[k] = lArr[i];
            i++;
            k++;
        }

        while (j < rLength) {
            array[k] = rArr[j];
            j++;
            k++;
        }
    }

    private <T> void sort(T[] array, Comparator<? super T> comparator, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            sort(array, comparator, left, mid);
            sort(array, comparator, mid + 1, right);

            merge(array, comparator, left, mid, right);
        }
    }

    @Override
    public <T> void sort(T[] array, Comparator<? super T> comparator) {
        sort(array, comparator, 0, array.length - 1);
    }
}
