package oy.tol.tra;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@DisplayName("Testing the Generic algorithms.")
public class GenericTests {

    private Integer[] numbersArray;
    private String[] stringsArray;

    @BeforeEach
    void setup() {
        numbersArray = getArrayWithNumbers();
        stringsArray = getArrayWithStrings();
    }

    @Test
    @Timeout(value = 10)
    @DisplayName("Testing the Generic reverse() with integers")
    void reverseTestInteger() {
        Integer[] expectedReversedArray = reverseArray(numbersArray);
        Algorithms.reverse(numbersArray);
        assertArrayEquals(expectedReversedArray, numbersArray, "Reversed array is not correct!");
    }

    @Test
    @Timeout(value = 10)
    @DisplayName("Testing the Generic reverse() with strings")
    void reverseTestString() {
        String[] expectedReversedArray = reverseArray(stringsArray);
        Algorithms.reverse(stringsArray);
        assertArrayEquals(expectedReversedArray, stringsArray, "Reversed array is not correct!");
    }

    @Test
    @DisplayName("Testing the Generic sort() with integers")
    void sortTestInteger() {
        Integer[] correctlySortedArray = sortArray(numbersArray);
        Algorithms.sort(numbersArray);
        assertArrayEquals(correctlySortedArray, numbersArray, "Sorted array is not correct!");
    }

    @Test
    @DisplayName("Testing the Generic sort() with strings")
    void sortTestString() {
        String[] correctlySortedArray = sortArray(stringsArray);
        Algorithms.sort(stringsArray);
        assertArrayEquals(correctlySortedArray, stringsArray, "Sorted array is not correct!");
    }

    private Integer[] getArrayWithNumbers() {
        return new Random().ints(5, 10, 20).boxed().toArray(Integer[]::new);
    }

    private String[] getArrayWithStrings() {
        Random random = new Random();
        return IntStream.range(0, 5 + random.nextInt(5))
                .mapToObj(i -> random.ints('0', 'z' + 1)
                        .filter(num -> (num <= '9' || num >= 'A') && (num <= 'Z' || num >= 'a'))
                        .limit(5)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString())
                .toArray(String[]::new);
    }

    private <T> T[] reverseArray(T[] array) {
        List<T> list = Arrays.asList(array.clone());
        Collections.reverse(list);
        return list.toArray(array.clone());
    }

    private <T extends Comparable<? super T>> T[] sortArray(T[] array) {
        return Arrays.stream(array.clone()).sorted().toArray(size -> array.clone());
    }
}