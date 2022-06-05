package daimonix.algorithms.sorting;

import java.util.Comparator;

public class ShellSorter extends Sorter {

    @Override
    public <T> void sort(T[] array, Comparator<? super T> comparator) {
        int distance = array.length / 2;
        while (distance > 0) {
            for (int i = distance; i < array.length; i++) {
                T temp = array[i];
                int j = i;
                while (j >= distance && comparator.compare(array[j - distance], temp) > 0) {
                    array[j] = array[j - distance];
                    j -= distance;
                }
                array[j] = temp;
            }
            distance /= 2;
        }
    }
}
