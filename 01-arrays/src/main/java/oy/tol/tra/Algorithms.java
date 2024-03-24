package oy.tol.tra;

public class Algorithms {
    public static <T extends Comparable<T>> void bubbleSort(T[] array) {
        if (array != null && array.length > 1) {
            boolean swapped = true;
            int n = array.length;
            while (swapped) {
                swapped = false;
                for (int i = 1; i < n; i++) {
                    if (array[i - 1].compareTo(array[i]) > 0) {
                        T temp = array[i - 1];
                        array[i - 1] = array[i];
                        array[i] = temp;
                        swapped = true;
                    }
                }
                n--;
            }
        }
    }

    public static <T> void reverseArrayInGroups(T[] array, int groupSize) {
        if (array != null && array.length > groupSize) {
            for (int i = 0; i < array.length; i += groupSize) {
                int left = i;
                int right = Math.min(i + groupSize - 1, array.length - 1);
                while (left < right) {
                    T temp = array[left];
                    array[left] = array[right];
                    array[right] = temp;
                    left++;
                    right--;
                }
            }
        }
    }
}