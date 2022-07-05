package com.github.shuoros.pepeParty;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.github.shuoros.pepeParty.util.EntityLoader;
import io.github.shuoros.jterminal.JTerminal;
import io.github.shuoros.jterminal.util.TextEntity;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.github.shuoros.pepeParty.model.Frame;
import com.github.shuoros.pepeParty.util.FrameLoader;

/**
 * If you are tired of life, invite Pepe to throw a party in your terminal.
 *
 * @author Soroush Shemshadi
 * @version 0.1.0
 * @since 0.1.0
 */
public class PepeParty implements NativeKeyListener {

    private static List<Frame> frames = new ArrayList<>();
    private static List<List<TextEntity>> entities = new ArrayList<>();
    private static int frameIndex = 0;
    private static String currentFrame;
    private static List<TextEntity> currentEntity;
    private static boolean shutdown = false;

    /**
     * Application runner. First it load the frames by
     * {@link com.github.shuoros.pepeParty.util.FrameLoader} then register the
     * {@link org.jnativehook.keyboard.NativeKeyListener} and then call the
     * {@code run()} to run the party.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        frames = FrameLoader.load("frames.files");
        entities = EntityLoader.load("entities.files");

        registerKeyListener();

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
        if (shutdown) {
            JTerminal.clear();
            unRegisterKeyListener();
            System.exit(0);
        }

        frameIndex++;

        if (frameIndex >= frames.size()) {
            frameIndex = 0;
        }

        JTerminal.clear(wait);
    }

    /**
     * Register the {@link org.jnativehook.keyboard.NativeKeyListener} to screen and
     * turn off {@link org.jnativehook.keyboard.NativeKeyListener} logs.
     */
    private static void registerKeyListener() {
        LogManager.getLogManager().reset();
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new PepeParty());

    }

    private static void unRegisterKeyListener(){
        try {
            GlobalScreen.unregisterNativeHook();
        }
        catch (NativeHookException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * There is no need to KeyPressed event.
     */
    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
    }

    /**
     * If escape button pressed and released turn shut down to {@code true} and exit
     * the application.
     */
    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            shutdown = true;
        }
    }

    /**
     * There is no need to KeyTyped event.
     */
    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
    }

}
