package MyUtils;

import java.util.Comparator;

public class SortUtils {
    public static <T extends Comparable<T>> void BubbleSort(T[] array)
    {
        for(int i = 0; i < array.length; i++)
        {
            for(int j = 0; j < array.length; j++)
            {
                if(array[i].compareTo(array[j]) > 0)
                {
                    ArrayUtils.Swap(array, i, j);
                }
            }
        }
    }

    public static <T> void BubbleSort(T[] array, Comparator<? super T> comparator)
    {
        for(int i = 0; i < array.length; i++)
        {
            for(int j = 0; j < array.length; j++)
            {
                if(comparator.compare(array[i], array[j]) > 0)
                {
                    ArrayUtils.Swap(array, i, j);
                }
            }
        }
    }

    public static <T extends Comparable<T>> void InsertionSort(T[] array)
    {
        for(int i = 1; i < array.length; i++)
        {
            T el = array[i];
            int j = i - 1;

            while(j >= 0 && array[j].compareTo(el) > 0)
            {
                ArrayUtils.Swap(array, j, j + 1);
                j++;
            }
            array[j + 1] = el;
        }
    }

    public static <T extends Comparable<T>> void InsertionSort(T[] array, Comparator<T> comparator)
    {
        for(int i = 1; i < array.length; i++)
        {
            T el = array[i];
            int j = i - 1;

            while(j >= 0 && comparator.compare(array[j], el) > 0)
            {
                ArrayUtils.Swap(array, j, j + 1);
                j++;
            }
            array[j + 1] = el;
        }
    }
}
