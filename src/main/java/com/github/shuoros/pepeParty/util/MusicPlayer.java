package com.github.shuoros.pepeParty.util;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Objects;

public class MusicPlayer {

    public static void play(String music) {
        try {
            final AudioInputStream audioIn = AudioSystem.getAudioInputStream(FileLoader.loadMusic(music));
            final Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
