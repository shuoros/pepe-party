package com.github.shuoros.pepeParty.util;

import java.io.IOException;

/**
 * Screen utilities class.
 * 
 * @author Soroush Shemshadi
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class Screen {

	private static final String OS = System.getProperty("os.name");

	/**
	 * Clear the screen. If {@code OS} was windows then the {@code cls} command will
	 * be execute and else {@code "\033\143"} will be printed in terminal to clear
	 * the screen.
	 */
	public static void clear() {
		if (OS.contains("Windows")) {
			try {
				Runtime.getRuntime().exec("cls");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.print("\033\143");
		}
	}

	/**
	 * Call the {@code clear()} after sleep for {@code sleep} milliseconds.
	 * 
	 * @param sleep Sleep time in milliseconds.
	 */
	public static void clear(long sleep) {
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clear();
	}
}
