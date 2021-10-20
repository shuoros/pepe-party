package com.github.shuoros.pepeParty.util;

import java.io.IOException;

public class Screen {

	private static final String OS = System.getProperty("os.name");

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

	public static void clear(long sleep) {
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clear();
	}
}
