package com.github.shuoros.pepeParty;

import com.github.shuoros.pepeParty.domain.Frame;
import com.github.shuoros.pepeParty.service.EntityService;
import com.github.shuoros.pepeParty.service.FrameService;
import com.github.shuoros.pepeParty.util.MusicPlayer;
import io.github.shuoros.jterminal.JTerminal;
import io.github.shuoros.jterminal.util.TextEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * If you are tired of life, invite Pepe to throw a party in your terminal.
 *
 * @author Soroush Shemshadi
 * @version 1.2.0
 * @since 1.2.0
 */
public class PepeParty {

    private static final String SHOOTING_STARS_WAV = "shooting_stars.wav";
    private static List<Frame> frames = new ArrayList<>();
    private static List<List<TextEntity>> entities = new ArrayList<>();
    private static int frameIndex = 0;
    private static String currentFrame;
    private static List<TextEntity> currentEntity;

    /**
     * Application runner.
     * Initializes the configurations and then hits run.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        init(Arrays.asList(args));
        run();
    }

    /**
     * Loads the frames and entities by {@link FrameService} and {@link EntityService}.
     * Configs the args.
     *
     * @param args Command line arguments.
     */
    private static void init(List<String> args) {
        frames = FrameService.load("frames.files");
        entities = EntityService.load("entities.files");

        applyArguments(args);
    }

    /**
     * Gives the args and apply them to the app.
     *
     * @param args Command line arguments.
     */
    private static void applyArguments(List<String> args) {
        if (!args.contains("-m")) MusicPlayer.play(SHOOTING_STARS_WAV);
    }

    /**
     * First clear the terminal then in a infinite while loop calls
     * {@code create()}, {@code render()} and {@code dispose()}.
     */
    private static void run() {
        JTerminal.clear();

        while (true) {
            create(frameIndex);

            render();

            dispose(100);
        }
    }

    /**
     * Set the frame content from {@code frames} list by {@code frameIndex} to
     * {@code currentFrame}.
     *
     * @param frameIndex Is the index of a frame in {@code frames} which should be
     *                   set to {@code currentFrame}.
     */
    private static void create(int frameIndex) {
        currentFrame = frames.get(frameIndex).getContent();
        currentEntity = entities.get(frameIndex);
    }

    /**
     * Render the {@code currentFrame} on terminal.
     */
    private static void render() {
        JTerminal.println(currentFrame, currentEntity);
    }

    /**
     * Clear the current frame in terminal and increase {@code frameIndex} by one.
     * If {@code frameIndex} was grater than {@code frames} size then
     * {@code frameIndex} will be reassigned to zero. If {@code shutdown} was
     * {@code true} the terminal will be cleared and application closed.
     *
     * @param wait Time duration between two frames.
     */
    private static void dispose(int wait) {
        frameIndex++;

        if (frameIndex >= frames.size()) {
            frameIndex = 0;
        }

        JTerminal.clear(wait);
    }

}
