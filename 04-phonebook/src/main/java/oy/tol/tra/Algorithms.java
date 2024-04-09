import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class AdvancedAlgorithms {

    public static <T extends Comparable<T>> void mergeSort(T[] array) {
        if (array != null && array.length > 1) {
            T[] tempArray = Arrays.copyOf(array, array.length);
            mergeSort(array, tempArray, 0, array.length - 1);
        }
    }

    private static <T extends Comparable<T>> void mergeSort(T[] array, T[] tempArray, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(array, tempArray, start, mid);
            mergeSort(array, tempArray, mid + 1, end);
            merge(array, tempArray, start, mid, end);
        }
    }

    private static <T extends Comparable<T>> void merge(T[] array, T[] tempArray, int start, int mid, int end) {
        int leftLength = mid - start + 1;
        int rightLength = end - mid;
        T[] leftArray = Arrays.copyOfRange(array, start, mid + 1);
        T[] rightArray = Arrays.copyOfRange(array, mid + 1, end + 1);

        int i = 0, j = 0, k = start;
        while (i < leftLength && j < rightLength) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }
        while (i < leftLength) {
            array[k++] = leftArray[i++];
        }
        while (j < rightLength) {
            array[k++] = rightArray[j++];
        }
    }

    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        T max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }

    public static <T> void filterAndPrint(T[] array, Predicate<T> predicate) {
        for (T item : array) {
            if (predicate.test(item)) {
                System.out.println(item);
            }
        }
    }
}