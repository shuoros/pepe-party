package com.github.shuoros.pepeParty;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.github.shuoros.pepeParty.model.Frame;
import com.github.shuoros.pepeParty.util.FrameLoader;
import com.github.shuoros.pepeParty.util.Screen;

public class PepeParty implements NativeKeyListener {

	private static List<Frame> frames = new ArrayList<>();
	private static int frameIndex = 0;
	private static String currentFrame;
	private static boolean shutdown = false;

	public static void main(String[] args) {
		frames = FrameLoader.load("frames.files");

		registerKeyListener();

		run();
	}

	private static void run() {
		Screen.clear();

		while (true) {
			create(frameIndex);

			render();

			dispose(1000);
		}
	}

	private static void create(int frame) {
		currentFrame = frames.get(frame).getContent();
	}

	private static void render() {
		System.out.println(currentFrame);
	}

	private static void dispose(int wait) {
		if (shutdown) {
			Screen.clear();
			System.exit(0);
		}

		frameIndex++;

		if (frameIndex >= frames.size()) {
			frameIndex = 0;
		}

		Screen.clear(wait);
	}

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

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
			shutdown = true;
		}
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
	}

}
