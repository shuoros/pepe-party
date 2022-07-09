package com.github.shuoros.pepeParty;

import com.github.shuoros.pepeParty.model.Frame;
import com.github.shuoros.pepeParty.util.EntityLoader;
import com.github.shuoros.pepeParty.util.FrameLoader;
import io.github.shuoros.jterminal.JTerminal;
import io.github.shuoros.jterminal.util.TextEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * If you are tired of life, invite Pepe to throw a party in your terminal.
 *
 * @author Soroush Shemshadi
 * @version 0.1.0
 * @since 0.1.0
 */
public class PepeParty {

    private static List<Frame> frames = new ArrayList<>();
    private static List<List<TextEntity>> entities = new ArrayList<>();
    private static int frameIndex = 0;
    private static String currentFrame;
    private static List<TextEntity> currentEntity;

    /**
     * Application runner. First it loads the frames by
     * {@link com.github.shuoros.pepeParty.util.FrameLoader} and then call the
     * {@code run()} to run the party.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        frames = FrameLoader.load("frames.files");
        entities = EntityLoader.load("entities.files");

        run();
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
