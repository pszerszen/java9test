package com.osa.java9test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TryWithResourcesTest {

    @Test
    @DisplayName("More elegant try with resources.")
    void testNewTryWithResources() throws IOException {
        FileReader fileReader = new FileReader(getClass().getResource("some_shit.txt").getFile());
        BufferedReader reader = new BufferedReader(fileReader);
        try (fileReader; reader) {
            System.out.println(reader.readLine());
        }

        assertThrows(IOException.class, reader::read);
    }

    @Test
    @DisplayName("Native quick copy from input to output stream.")
    void testTransferToJava9Syntax() throws IOException {
        byte[] inBytes = "Hello Java 9".getBytes();
        ByteArrayInputStream bis = new ByteArrayInputStream(inBytes);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try (bis; bos) {
            bis.transferTo(bos);

            byte[] outBytes = bos.toByteArray();
            assertTrue(Arrays.equals(inBytes, outBytes));
        }
    }
}
