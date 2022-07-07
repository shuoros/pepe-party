package com.github.shuoros.pepeParty.util;

import io.github.shuoros.jterminal.ansi.Color;
import io.github.shuoros.jterminal.util.TextEntity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EntityLoader {

    public static List<List<TextEntity>> load(final String path) {
        List<List<TextEntity>> frameEntities = new ArrayList<>();
        for (String file : new BufferedReader(//
                new InputStreamReader(//
                        Objects.requireNonNull(Thread.currentThread().getContextClassLoader()//
                                .getResourceAsStream(path)),
                        StandardCharsets.UTF_8))//
                .lines()//
                .collect(Collectors.toList())//
        ) {
            List<TextEntity> entities = new ArrayList<>();
            Arrays.stream(new BufferedReader(//
                    new InputStreamReader(//
                            Objects.requireNonNull(Thread.currentThread().getContextClassLoader()//
                                    .getResourceAsStream(file)),
                            StandardCharsets.UTF_8)).lines()//
                    .collect(Collectors.joining("\n")).split("\n")).iterator().forEachRemaining(entity -> {
                entities.add(//
                        new TextEntity(//
                                Integer.parseInt(entity.split("-")[0]),//
                                Integer.parseInt(entity.split("-")[1]),//
                                "nan".equals(entity.split("-")[2]) ?//
                                        Color.DEFAULT : //
                                        Color.xTerm(Integer.parseInt(entity.split("-")[2])),//
                                "nan".equals(entity.split("-")[3]) ?//
                                        Color.DEFAULT ://
                                        Color.xTerm(Integer.parseInt(entity.split("-")[3]))));
            });
            frameEntities.add(entities);
        }
        return frameEntities;
    }

}
