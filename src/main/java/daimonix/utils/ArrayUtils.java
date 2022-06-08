package daimonix.utils;

import java.util.Random;

public class ArrayUtils {
    private ArrayUtils() {
    }

    public static <T> void swap(T[] array, int indexA, int indexB) {
        T temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
    }

    public static <T> String toString(T[] array) {
        return toString(array, ", ");
    }

    public static <T> String toString(T[] array, String separator) {
        if (array == null)
            return "null";
        if (array.length == 0)
            return "[]";
        StringBuilder sBuilder = new StringBuilder().append('[');
        for (int i = 0; i < array.length; i++) {
            sBuilder.append(array[i].toString()).append(separator);
        }
        sBuilder.setLength(sBuilder.length() - separator.length());
        return sBuilder.append(']').toString();
    }

    public static Integer[] createSortedArray(int length) {
        Integer[] arr = new Integer[length];
        for (int i = 0; i < length; i++)
            arr[i] = i + 1;
        return arr;
    }

    public static Integer[] createRandomArray(int length) {
        return createRandomArray(length, System.nanoTime());
    }

    public static Integer[] createRandomArray(int length, long seed) {
        Integer[] arr = new Integer[length];
        Random rand = new Random(seed);
        for (int i = 0; i < length; i++)
            arr[i] = rand.nextInt(length * 10) + 1;
        return arr;
    }

    public static Integer[] createReversedArray(int length) {
        Integer[] arr = new Integer[length];
        for (int i = 0; i < length; i++)
            arr[i] = length - i;
        return arr;
    }
}
