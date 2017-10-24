package com.osa.java9test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.summarizingInt;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CollectionFactoriesTest {

    private static final String SPACE = " ";
    private static final String EMPTY = "";
    private static final String DOT = ".";

    @Test
    @DisplayName("Factory of immutable list.")
    void testLists() {
        List<String> list = List.of("This", "is", "an", "immutable", "list", "of", "Strings");
        String fullString = list.stream().collect(joining(SPACE, EMPTY, DOT));

        assertEquals("This is an immutable list of Strings.", fullString);
        assertAll(
                () -> assertThrows(UnsupportedOperationException.class, () -> list.add("more text"), this::getImmutabilityMessage),
                () -> assertThrows(UnsupportedOperationException.class, () -> list.remove(0), this::getImmutabilityMessage),
                () -> assertThrows(UnsupportedOperationException.class, () -> list.sort(String::compareTo), this::getImmutabilityMessage));
    }

    @Test
    @DisplayName("Factory of immutable set.")
    void testSets() {
        Set<Integer> set = Set.of(2, 3, 5, 10);

        long sum = set.stream().collect(summarizingInt(value -> value)).getSum();
        assertEquals(20L, sum);

        assertAll(
                () -> assertThrows(UnsupportedOperationException.class, () -> set.add(7), this::getImmutabilityMessage),
                () -> assertThrows(UnsupportedOperationException.class, () -> set.remove(0), this::getImmutabilityMessage),
                () -> assertThrows(UnsupportedOperationException.class, set::clear, this::getImmutabilityMessage));

        assertThrows(IllegalArgumentException.class, () -> Set.of(1, 2, 1), "Can't create a set with non-unique elements.");
    }

    @Test
    @DisplayName("Factory of immutable map.")
    void testMaps() {
        Map<Integer, String> map = Map.of(
                0, "zero",
                1, "one",
                2, "two",
                3, "three");

        Map<Integer, String> map2 = Map.ofEntries(
                Map.entry(0, "zero"),
                Map.entry(1, "one"),
                Map.entry(2, "two"),
                Map.entry(3, "three"));

        assertAll(
                () -> assertThrows(UnsupportedOperationException.class, () -> map.put(4, "four"), this::getImmutabilityMessage),
                () -> assertThrows(UnsupportedOperationException.class, () -> map.remove(0), this::getImmutabilityMessage),
                () -> assertThrows(UnsupportedOperationException.class, map::clear, this::getImmutabilityMessage));

        assertAll(
                () -> assertThrows(UnsupportedOperationException.class, () -> map2.put(4, "four"), this::getImmutabilityMessage),
                () -> assertThrows(UnsupportedOperationException.class, () -> map2.remove(0), this::getImmutabilityMessage),
                () -> assertThrows(UnsupportedOperationException.class, map2::clear, this::getImmutabilityMessage));
    }

    private String getImmutabilityMessage() {
        return "Collection should be immutable.";
    }
}
