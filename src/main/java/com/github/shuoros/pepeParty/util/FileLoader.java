package com.github.shuoros.pepeParty.util;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author Soroush Shemshadi
 * @version 1.2.0
 * @since 1.2.0
 */
public class FileLoader {

    /**
     * @param path of File.
     * @return Loads the file in the given path as an input stream.
     */
    public static InputStreamReader loadFile(String path) {
        return new InputStreamReader(
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream(path)),
                StandardCharsets.UTF_8);
    }

    /**
     * @param path of Audio file.
     * @return Loads the audio file in the given path as an input stream.
     */
    public static BufferedInputStream loadMusic(String path) {
        return new BufferedInputStream(
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream(path))
        );
    }
}
