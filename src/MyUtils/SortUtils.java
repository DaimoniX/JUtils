package MyUtils;

import java.util.Comparator;

public class SortUtils {
    public static void Swap(Comparable[] array, int indexA, int indexB)
    {
        Comparable temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
    }

    public static void BubbleSort(Comparable[] array)
    {
        for(int i = 0; i < array.length; i++)
        {
            for(int j = 0; j < array.length; j++)
            {
                if(array[i].compareTo(array[j]) > 0)
                {
                    Swap(array, i, j);
                }
            }
        }
    }

    public static void BubbleSort(Comparable[] array, Comparator comparator)
    {
        for(int i = 0; i < array.length; i++)
        {
            for(int j = 0; j < array.length; j++)
            {
                if(comparator.compare(array[i], array[j]) > 0)
                {
                    Swap(array, i, j);
                }
            }
        }
    }

    public static void InsertionSort(Comparable[] array)
    {
        for(int i = 1; i < array.length; i++)
        {
            Comparable el = array[i];
            int j = i - 1;

            while(j >= 0 && array[j].compareTo(el) > 0)
            {
                Swap(array, j, j + 1);
                j++;
            }
            array[j + 1] = el;
        }
    }

    public static void InsertionSort(Comparable[] array, Comparator comparator)
    {
        for(int i = 1; i < array.length; i++)
        {
            Comparable el = array[i];
            int j = i - 1;

            while(j >= 0 && comparator.compare(array[j], el) > 0)
            {
                Swap(array, j, j + 1);
                j++;
            }
            array[j + 1] = el;
        }
    }
}
