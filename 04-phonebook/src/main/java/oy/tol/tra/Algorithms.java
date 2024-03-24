import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class AdvancedAlgorithms {

    public static <T extends Comparable<T>> void mergeSort(T[] array) {
        if (array != null && array.length > 1) {
            int mid = array.length / 2;
            T[] leftArray = Arrays.copyOfRange(array, 0, mid);
            T[] rightArray = Arrays.copyOfRange(array, mid, array.length);
            mergeSort(leftArray);
            mergeSort(rightArray);
            merge(array, leftArray, rightArray);
        }
    }

    private static <T extends Comparable<T>> void merge(T[] array, T[] leftArray, T[] rightArray) {
        int leftLength = leftArray.length;
        int rightLength = rightArray.length;
        int i = 0, j = 0, k = 0;
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

    public static <T> void customSort(T[] array, Comparator<? super T> comparator) {
        Arrays.sort(array, comparator);
    }
}