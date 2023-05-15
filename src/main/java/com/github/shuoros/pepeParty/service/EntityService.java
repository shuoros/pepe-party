package com.github.shuoros.pepeParty.service;

import com.github.shuoros.pepeParty.util.FileLoader;
import io.github.shuoros.jterminal.ansi.Color;
import io.github.shuoros.jterminal.util.TextEntity;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityService {

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

    private static String[] loadEntity(String file) {
        return new BufferedReader(FileLoader.load(file))
                .lines()
                .collect(Collectors.joining("\n"))
                .split("\n");
    }

    private static List<String> loadEntityList(String path) {
        return new BufferedReader(FileLoader.load(path))
                .lines()
                .collect(Collectors.toList());
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
