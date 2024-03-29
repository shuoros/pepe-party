package com.github.shuoros.pepeParty.service;

import com.github.shuoros.pepeParty.domain.Frame;
import com.github.shuoros.pepeParty.util.FileLoader;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A service class to load and process the frame files.
 *
 * @author Soroush Shemshadi
 * @version 1.2.0
 * @since 1.2.0
 */
public class FrameService {

    /**
     * Load all .txt files as frames from the given path.
     *
     * @param path It's a .files file which has paths to all the frames.
     * @return A list contains all loaded frames.
     */
    public static List<Frame> load(final String path) {
        List<Frame> frames = new ArrayList<>();
        for (String file : loadFrameList(path)) {
            frames.add(buildFrame(file));
        }
        return frames;
    }

    private static List<String> loadFrameList(String path) {
        return new BufferedReader(FileLoader.loadFile(path))
                .lines()
                .collect(Collectors.toList());
    }

    private static String loadFrame(String file) {
        return new BufferedReader(FileLoader.loadFile(file))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    private static Frame buildFrame(String file) {
        return new Frame(loadFrame(file));
    }
}
