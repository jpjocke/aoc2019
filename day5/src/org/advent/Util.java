package org.advent;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Util {
    public static List<IntCode> toOperations(String string) {
        String[] split = string.split(",");
        return Arrays.stream(split)
                .map(s -> new IntCode(Integer.parseInt(s)))
                .collect(Collectors.toList());
    }
}
