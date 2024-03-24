import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Testing the Generic algorithms.")
public class GenericTests {

    @Test
    @Timeout(value = 10, unit = Timeout.Unit.SECONDS)
    @DisplayName("Testing the Generic reverse() with integers")
    void reverseTestInteger() {
        Integer[] testArray = getArrayWithNumbers();

        Integer[] originalArray = Arrays.copyOf(testArray, testArray.length);
        Arrays.sort(testArray, Collections.reverseOrder());

        Algorithms.reverse(originalArray);

        assertArrayEquals(testArray, originalArray, "Reversed array is not correct!");
    }

    @Test
    @Timeout(value = 10, unit = Timeout.Unit.SECONDS)
    @DisplayName("Testing the Generic reverse() with strings")
    void reverseTestString() {
        String[] testArray = getArrayWithStrings();

        String[] originalArray = Arrays.copyOf(testArray, testArray.length);
        Arrays.sort(testArray, Collections.reverseOrder());

        Algorithms.reverse(originalArray);

        assertArrayEquals(testArray, originalArray, "Reversed array is not correct!");
    }

    @ParameterizedTest
    @MethodSource("provideArrays")
    @Timeout(value = 10, unit = Timeout.Unit.SECONDS)
    @DisplayName("Testing the Generic sort() with different types")
    void sortTest(Object[] testArray) {
        Object[] originalArray = Arrays.copyOf(testArray, testArray.length);
        Arrays.sort(testArray);

        Algorithms.sort(testArray);

        assertArrayEquals(testArray, originalArray, "Sorted array is not correct!");
    }

    static Stream<Object[]> provideArrays() {
        return Stream.of(
                new Object[]{getArrayWithNumbers()},
                new Object[]{getArrayWithStrings()}
                // Add more arrays of different types for testing
        );
    }

    private Integer[] getArrayWithNumbers() {
        int size = ThreadLocalRandom.current().nextInt(5) + 5;
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(10);
        }
        return array;
    }

    private String[] getArrayWithStrings() {
        int size = ThreadLocalRandom.current().nextInt(5) + 5;
        String[] array = new String[size];
        for (int i = 0; i < size; i++) {
            array[i] = generateRandomString();
        }
        return array;
    }

    private String generateRandomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(number -> (number <= 57 || number >= 65) && (number <= 90 || number >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}