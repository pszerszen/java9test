package com.osa.java9test;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TryWithResourcesTest {

    @Test
    void testNewTryWithResources() throws IOException {
        FileReader fileReader = new FileReader(getClass().getResource("some_shit.txt").getFile());
        BufferedReader reader = new BufferedReader(fileReader);
        try (fileReader; reader) {
            System.out.println(reader.readLine());
        }

        assertThrows(IOException.class, reader::read);
    }
}
