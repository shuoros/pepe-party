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
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(FileLoader.loadMusic(path));

            final AudioFormat format = audioIn.getFormat();
            if ((format.getEncoding() == AudioFormat.Encoding.ULAW) || (format.getEncoding() == AudioFormat.Encoding.ALAW)) {
                audioIn = convertToPCMSigned(audioIn);
            }

            final Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts the audio input stream to PCM signed format.
     *
     * @param audioIn the audio input stream to convert.
     * @return the converted audio input stream.
     * @throws IOException if an I/O error occurs.
     */
    private static AudioInputStream convertToPCMSigned(AudioInputStream audioIn) throws IOException {
        AudioFormat format = audioIn.getFormat();
        AudioFormat pcmFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                format.getSampleRate(),
                format.getSampleSizeInBits() * 2,
                format.getChannels(),
                format.getFrameSize() * 2,
                format.getFrameRate(), true);

        return AudioSystem.getAudioInputStream(pcmFormat, audioIn);
    }
}
