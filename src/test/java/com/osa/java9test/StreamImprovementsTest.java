package com.osa.java9test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class StreamImprovementsTest {

    @Test
    @DisplayName("Demonstrate Stream::dropWhile (filter until first not matching predicate)")
    void dropWhileTest() {
        List<Integer> firstList = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .dropWhile(s -> s < 5)
                .collect(Collectors.toList());
        assertIterableEquals(List.of(5,6,7,8,9,10), firstList);

        List<Integer> secondList = Stream.of(1, 2, 10, 9, 5, 6, 2, 3)
                .dropWhile(s -> s < 5)
                .collect(Collectors.toList());
        assertIterableEquals(List.of(10, 9, 5, 6, 2, 3), secondList);
    }

    @Test
    @DisplayName("Demonstrate Stream::takeWhile (filter until first not matching predicate)")
    void takeWhileTest() {
        List<Integer> firstList = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .takeWhile(s -> s < 5)
                .collect(Collectors.toList());
        assertIterableEquals(List.of(0, 1, 2, 3, 4), firstList);

        List<Integer> secondList = Stream.of(1, 2, 10, 9, 5, 6, 2, 3)
                .takeWhile(s -> s < 5)
                .collect(Collectors.toList());
        assertIterableEquals(List.of(1, 2), secondList);
    }

    @Test
    @DisplayName("Classic for loop replacement.")
    void streamTestWithPredicate() {
        Stream.iterate(0, n -> n < 100, n -> n + 1)
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("Infinite loop with int index.")
    void streamTest() {
        Stream.iterate(0, n -> n + 1)
                .limit(1000)
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("Demonstrate Stream::ofNullable")
    void ofNullableTest() {
        Stream st = Stream.ofNullable(null);
        assertEquals(0, st.count());

        st = Stream.ofNullable("admin");
        assertEquals(1, st.count());
    }
}
