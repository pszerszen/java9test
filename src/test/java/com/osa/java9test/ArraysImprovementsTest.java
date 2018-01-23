package com.osa.java9test;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

import static java.util.Arrays.compare;
import static java.util.Arrays.mismatch;

class ArraysImprovementsTest {
    @Test
    void arrayEqualsTest() {
        int[] existRows = { 0, 1, 2, 3, 4, 5 };
        int[] newRows =   { 3, 4, 5, 1, 2, 0 };

        assertAll(() -> assertFalse(Arrays.equals(existRows, newRows)),
                () -> assertTrue(Arrays.equals(
                        existRows, 1, 3,
                        newRows, 3, 5)),
                () -> assertTrue(Arrays.equals(
                        existRows, 3, 5,
                        newRows, 0, 2)));
    }

    @Test
    void compareArraysTest() {
        int[] tomMarks =   { 5, 6, 7, 8,  9, 10 };
        int[] aliceMarks = { 5, 6, 7, 8,  9, 10 };
        int[] daisyMarks = { 5, 6, 7, 10, 9, 10 };
        int[] maryMarks =  { 5, 6, 7, 8         };

        assertAll(() -> assertEquals(0, compare(tomMarks, aliceMarks)),
                () -> assertEquals(-1, compare(tomMarks, daisyMarks)),
                () -> assertEquals(2, compare(tomMarks, maryMarks)));
    }

    @Test
    void compareSliceArraysTest() {
        int[] tomMarks =   { 5, 6, 7, 8,  9, 10 };
        int[] daisyMarks = { 5, 6, 7, 10, 9, 10 };
        int[] maryMarks =  { 5, 6, 7, 8 };

        assertAll(
                () -> assertEquals(0, compare(
                        tomMarks, 0, 3,
                        daisyMarks, 0, 3)),
                () -> assertEquals(0, compare(
                        tomMarks, 0, 4,
                        maryMarks, 0, maryMarks.length)),
                () -> assertEquals(1, compare(
                        daisyMarks, 0, 4,
                        maryMarks, 0, maryMarks.length)));

    }

    @Test
    void mismatchArraysTest() {
        int[] a = { 1, 2, 3, 4, 5 };
        int[] b = { 1, 2, 3, 4, 5 };
        int[] c = { 1, 2, 4, 4, 5, 6 };

        assertAll(
                () -> assertEquals(-1, mismatch(a, b)),
                () -> assertEquals(2, mismatch(a, c)),
                () -> assertEquals(-1, mismatch(
                        a, 0, 2,
                        c, 0, 2)),
                () -> assertEquals(2, mismatch(
                        a, 0, 3,
                        c, 0, 3)),
                () -> assertEquals(0, mismatch(
                        a, 2, a.length,
                        c, 2, 5)));
    }
}
