package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

public class MiscTest {
    @Test
    public void random(){
        System.out.println(ThreadLocalRandom.current().nextInt(0, 10));
        System.out.println(ThreadLocalRandom.current().nextInt(0, 10));
        System.out.println(ThreadLocalRandom.current().nextInt(0, 10));
        System.out.println(ThreadLocalRandom.current().nextInt(0, 10));
        System.out.println(ThreadLocalRandom.current().nextInt(0, 10));
        System.out.println(ThreadLocalRandom.current().nextInt(0, 10));
        System.out.println(ThreadLocalRandom.current().nextInt(0, 10));
        System.out.println(ThreadLocalRandom.current().nextInt(0, 10));
        System.out.println(ThreadLocalRandom.current().nextInt(0, 10));

    }

    @Test
    public void getFile(){



    }
}
