package daimonix.utils;

import java.util.Random;

public class ArrayUtils {
    private ArrayUtils() {
    }

    public static <T> void Swap(T[] array, int indexA, int indexB) {
        T temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
    }

    public static <T> String toString(T[] array) {
        return toString(array, ',');
    }

    public static <T> String toString(T[] array, char separator) {
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sBuilder.append(array[i].toString()).append(separator);
        }
        return sBuilder.toString();
    }

    public static Integer[] CreateSortedArray(int length) {
        Integer[] arr = new Integer[length];
        for (int i = 0; i < length; i++)
            arr[i] = i + 1;
        return arr;
    }

    public static Integer[] CreateRandomArray(int length) {
        return CreateRandomArray(length, System.nanoTime());
    }

    public static Integer[] CreateRandomArray(int length, long seed) {
        Integer[] arr = new Integer[length];
        Random rand = new Random(seed);
        for (int i = 0; i < length; i++)
            arr[i] = rand.nextInt(length * 10) + 1;
        return arr;
    }

    public static Integer[] CreateReversedArray(int length) {
        Integer[] arr = new Integer[length];
        for (int i = 0; i < length; i++)
            arr[i] = length - i;
        return arr;
    }
}
