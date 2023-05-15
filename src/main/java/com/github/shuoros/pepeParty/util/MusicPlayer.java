package com.github.shuoros.pepeParty.util;

import javax.sound.sampled.*;
import java.io.IOException;

/**
 * @author Soroush Shemshadi
 * @version 1.2.0
 * @since 1.2.0
 */
public class MusicPlayer {

    /**
     * Playes a wav audio file in the background of app.
     *
     * @param path of audio file.
     */
    public static void play(String path) {
        try {
            final AudioInputStream audioIn = AudioSystem.getAudioInputStream(FileLoader.loadMusic(path));
            final Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
