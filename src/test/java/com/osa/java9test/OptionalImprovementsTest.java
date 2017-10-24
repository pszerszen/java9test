package com.osa.java9test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OptionalImprovementsTest {

    @Test
    @DisplayName("Demonstrate Optional::ifPresentOrElse (for consuming)")
    void testOptionalIfPresentOrElse() {

        List<Optional<String>> days = List.of(
                Optional.of("Monday"),
                Optional.of("Tuesday"),
                Optional.empty(),
                Optional.ofNullable(null),
                Optional.of("Friday"),
                Optional.of("Saturday"),
                Optional.of("Sunday"));

        days.forEach(p -> p.ifPresentOrElse(System.out::println, () -> System.out.println("Day not available")));
    }

    @Test
    @DisplayName("Demonstrate Optional::or (for mapping)")
    void testOptionalOr() {
        List<Optional<Integer>> studentAges = List.of(
                Optional.of(20),
                Optional.of(21),
                Optional.empty(),
                Optional.ofNullable(null),
                Optional.of(22),
                Optional.of(18),
                Optional.of(19)
        );
        long count = studentAges.stream()
                .map(p -> p.or(() -> Optional.of(20)))
                .map(Optional::get)
                .filter(age -> age == 20)
                .count();

        assertEquals(3, count);
    }

    @Test
    void testOptionalStream() {
        Optional<String> maleOpt = Optional.of("Male");
        Optional<String> feMaleOpt = Optional.of("FeMale");
        Optional<String> otherOpt = Optional.empty();

        assertAll(
                () -> assertEquals("Male", maleOpt.stream().findFirst().get()),
                () -> assertEquals("FeMale", feMaleOpt.stream().findFirst().get()),

                () -> assertTrue(maleOpt.stream().count() == 1),
                () -> assertTrue(feMaleOpt.stream().count() == 1),

                () -> assertTrue(otherOpt.stream().count() == 0));

    }
}
