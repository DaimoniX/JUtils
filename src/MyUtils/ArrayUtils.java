package MyUtils;

public class ArrayUtils {
    public static <T> void Swap(T[] array, int indexA, int indexB) {
        T temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
    }

    public static <T> String ArrayToString(T[] array) {
        return ArrayToString(array, ',');
    }

    public static <T> String ArrayToString(T[] array, char separator) {
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sBuilder.append(array[i].toString()).append(separator);
        }
        return sBuilder.toString();
    }
}
