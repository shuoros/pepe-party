package com.github.shuoros.pepeParty.util;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FileLoader {

    public static InputStreamReader load(String file) {
        return new InputStreamReader(
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream(file)),
                StandardCharsets.UTF_8);
    }
}
