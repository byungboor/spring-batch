package com.example.demo;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class Asserts {

    public static void assertNotNull(Object obj, String expression) {
        if (Objects.isNull(obj))
            throw new IllegalArgumentException(expression);
    }

    public static void assertNotEmpty(String string, String expression) {
        if (StringUtils.isEmpty(string))
            throw new IllegalArgumentException(expression + " [" + string + "]");
    }

    public static void assertNotTrimEmpty(String string, String expression) {
        Optional.ofNullable(string)
                .map(String::trim)
                .filter(str -> !StringUtils.isEmpty(str))
                .orElseThrow(() -> new IllegalArgumentException(expression + " [" + string + "]"));
    }

    public static void assertNotEmpty(Collection collection, String expression) {
        if (CollectionUtils.isEmpty(collection))
            throw new IllegalArgumentException(expression);
    }
}
