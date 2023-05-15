package com.github.shuoros.pepeParty.service;

import com.github.shuoros.pepeParty.util.FileLoader;
import io.github.shuoros.jterminal.ansi.Color;
import io.github.shuoros.jterminal.util.TextEntity;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A service class to load and process the entity files.
 *
 * @author Soroush Shemshadi
 * @version 1.2.0
 * @since 1.2.0
 */
public class EntityService {

    /**
     * @param path of list of entities.
     * @return A 2d array that each index of it represents a from and how that frame should be drawn.
     */
    public static List<List<TextEntity>> load(final String path) {
        List<List<TextEntity>> frameEntities = new ArrayList<>();
        for (String file : loadEntityList(path)) {
            List<TextEntity> entities = new ArrayList<>();
            Arrays.stream(loadEntity(file)).iterator().forEachRemaining(
                    entity -> {
                        entities.add(buildTextEntity(entity));
                    });
            frameEntities.add(entities);
        }
        return frameEntities;
    }

    private static List<String> loadEntityList(String path) {
        return new BufferedReader(FileLoader.loadFile(path))
                .lines()
                .collect(Collectors.toList());
    }

    private static String[] loadEntity(String file) {
        return new BufferedReader(FileLoader.loadFile(file))
                .lines()
                .collect(Collectors.joining("\n"))
                .split("\n");
    }

    private static TextEntity buildTextEntity(String entity) {
        return new TextEntity(
                getBegin(entity),
                getEnd(entity),
                getForeground(entity),
                getBackground(entity)
        );
    }

    private static Color getBackground(String entity) {
        return "nan".equals(entity.split("-")[3]) ?
                Color.DEFAULT :
                Color.xTerm(Integer.parseInt(entity.split("-")[3]));
    }

    private static Color getForeground(String entity) {
        return "nan".equals(entity.split("-")[2]) ?
                Color.DEFAULT :
                Color.xTerm(Integer.parseInt(entity.split("-")[2]));
    }

    private static int getEnd(String entity) {
        return Integer.parseInt(entity.split("-")[1]);
    }

    private static int getBegin(String entity) {
        return Integer.parseInt(entity.split("-")[0]);
    }

}
