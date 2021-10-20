package com.github.shuoros.pepeParty.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.shuoros.pepeParty.model.Frame;

/**
 * FrameLoader utilities class.
 * 
 * @author Soroush Shemshadi
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FrameLoader {

	/**
	 * Load all .txt files as frames from the given path.
	 * 
	 * @param path Its a .files file which has paths to all of the frames.
	 * @return A list contains all loaded frames.
	 */
	public static List<Frame> load(final String path) {
		List<Frame> frames = new ArrayList<>();
		for (String file : new BufferedReader(//
				new InputStreamReader(//
						Thread.currentThread().getContextClassLoader()//
								.getResourceAsStream(path),
						StandardCharsets.UTF_8))//
								.lines()//
								.collect(Collectors.toList())//
		) {
			frames.add(//
					new Frame(//
							new BufferedReader(//
									new InputStreamReader(//
											Thread.currentThread().getContextClassLoader()//
													.getResourceAsStream(file),
											StandardCharsets.UTF_8)).lines()//
													.collect(Collectors.joining("\n"))//
					));
		}
		return frames;
	}

}
