package oy.tol.tra;

public class Algorithms {
    public static <T extends Comparable<T>> void sort(T[] array) {
        if (array != null && array.length > 1) {
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j].compareTo(array[j + 1]) > 0) {
                        T tmp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = tmp;
                    }
                }
            }
        }
    }

    public static <T> void reverse(T[] array) {
        if (array != null && array.length > 1) {
            for (int i = 0; i < array.length / 2; i++) {
                T temp = array[i];
                array[i] = array[array.length - i - 1];
                array[array.length - i - 1] = temp;
            }
        }
    }

    public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
        while (fromIndex <= toIndex) {
            int middle = fromIndex + (toIndex - fromIndex) / 2;
            if (aValue.compareTo(fromArray[middle]) > 0) {
                fromIndex = middle + 1;
            } else if (aValue.compareTo(fromArray[middle]) < 0) {
                toIndex = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    private static <E extends Comparable<E>> int partition(E[] array, int begin, int end) {
        E benchmark = array[begin];
        int left = begin;
        int right = end;

        while (left < right) {
            while (left < right && array[right].compareTo(benchmark) >= 0) {
                right--;
            }
            if (left < right) {
                array[left] = array[right];
            }

            while (left < right && array[left].compareTo(benchmark) <= 0) {
                left++;
            }
            if (left < right) {
                array[right] = array[left];
            }
        }

        array[left] = benchmark;
        return left;
    }

    public static <E extends Comparable<E>> void quickSort(E[] array, int begin, int end) {
        if (begin < end) {
            int pivot = partition(array, begin, end);
            quickSort(array, begin, pivot - 1);
            quickSort(array, pivot + 1, end);
        }
    }

    public static <E extends Comparable<E>> void fastSort(E[] array) {
        quickSort(array, 0, array.length - 1);
    }
}