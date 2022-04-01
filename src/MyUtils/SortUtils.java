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
}
