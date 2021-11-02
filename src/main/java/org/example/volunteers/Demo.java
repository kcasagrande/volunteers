package org.example.volunteers;

import java.util.function.Function;

public class Demo {
    private final Function<String, Integer> service;

    public Demo() {
        this(String::length);
    }

    public Demo(Function<String, Integer> service) {
        this.service = service;
    }

    public int run(String input) {
        return service.apply(input);
    }
}
