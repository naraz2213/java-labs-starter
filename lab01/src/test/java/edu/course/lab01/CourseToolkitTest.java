package edu.course.lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CourseToolkitTest {

    // ---------- isEven ----------

    @Test
    void returnsTrueForEvenNumber() {
        boolean result = CourseToolkit.isEven(8);

        assertTrue(result);
    }

    @Test
    void returnsFalseForOddNumber() {
        boolean result = CourseToolkit.isEven(7);

        assertFalse(result);
    }

    @Test
    void zeroIsEven() {
        assertTrue(CourseToolkit.isEven(0));
    }

    @Test
    void returnsTrueForNegativeEvenNumber() {
        assertTrue(CourseToolkit.isEven(-8));
    }

    // ---------- isPrime ----------

    @Test
    void isPrimeReturnsFalseForNegativeNumber() {
        assertFalse(CourseToolkit.isPrime(-5));
    }

    @Test
    void isPrimeReturnsFalseForZero() {
        assertFalse(CourseToolkit.isPrime(0));
    }

    @Test
    void isPrimeReturnsFalseForOne() {
        assertFalse(CourseToolkit.isPrime(1));
    }

    @Test
    void isPrimeReturnsTrueForTwo() {
        assertTrue(CourseToolkit.isPrime(2));
    }

    @Test
    void isPrimeReturnsTrueForPrimeNumber() {
        assertTrue(CourseToolkit.isPrime(17));
    }

    @Test
    void isPrimeReturnsFalseForCompositeNumber() {
        assertFalse(CourseToolkit.isPrime(9));
    }

    @Test
    void isPrimeReturnsFalseForSquareOfPrime() {
        assertFalse(CourseToolkit.isPrime(49));
    }

    // ---------- isPalindrome ----------

    @Test
    void isPalindromeReturnsTrueForSimplePalindrome() {
        assertTrue(CourseToolkit.isPalindrome("level"));
    }

    @Test
    void isPalindromeReturnsFalseForNonPalindrome() {
        assertFalse(CourseToolkit.isPalindrome("java"));
    }

    @Test
    void isPalindromeIsCaseSensitive() {
        assertFalse(CourseToolkit.isPalindrome("Level"));
    }

    @Test
    void isPalindromeIsSpaceSensitive() {
        assertFalse(CourseToolkit.isPalindrome("race car"));
    }

    @Test
    void isPalindromeReturnsTrueForSingleCharacter() {
        assertTrue(CourseToolkit.isPalindrome("a"));
    }

    @Test
    void isPalindromeReturnsTrueForEmptyString() {
        assertTrue(CourseToolkit.isPalindrome(""));
    }

    @Test
    void isPalindromeThrowsForNull() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.isPalindrome(null));
    }

    // ---------- average ----------

    @Test
    void averageReturnsFractionalResult() {
        // (1 + 2) / 2 = 1.5 — именно дробный результат, не 1
        assertEquals(1.5, CourseToolkit.average(new int[]{1, 2}));
    }

    @Test
    void averageReturnsSumForSingleElement() {
        assertEquals(7.0, CourseToolkit.average(new int[]{7}));
    }

    @Test
    void averageHandlesNegativeNumbers() {
        // (-2 + 0 + 2 + 4) / 4 = 1.0
        assertEquals(1.0, CourseToolkit.average(new int[]{-2, 0, 2, 4}));
    }

    @Test
    void averageDoesNotModifyInputArray() {
        int[] original = {3, 1, 4, 1, 5};
        int[] copy = {3, 1, 4, 1, 5};

        CourseToolkit.average(original);

        assertArrayEquals(copy, original, "average must not modify the input array");
    }

    @Test
    void averageThrowsForNull() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(null));
    }

    @Test
    void averageThrowsForEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(new int[]{}));
    }
}