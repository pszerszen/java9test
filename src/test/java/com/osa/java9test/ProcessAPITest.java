package com.osa.java9test;

import org.junit.jupiter.api.Test;

class ProcessAPITest {

    @Test
    void testCurrent() {
        ProcessHandle processHandle = ProcessHandle.current();
        System.out.println("Current process Id: " + processHandle.pid());
        System.out.println("Current process info: " + processHandle.info());
    }

    @Test
    void testAllProcesses() {
        ProcessHandle.allProcesses()
                .map(ProcessHandle::info)
                .filter(info -> info.startInstant().isPresent())
                .map(Object::toString)
                .forEachOrdered(System.out::println);
    }
}
