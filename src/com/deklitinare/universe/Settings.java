package com.deklitinare.universe;

import java.io.File;

public class Settings {

	public static final double CONSTANT_G = 0.001;
	public static final double FRICTION_DISTANCE = 10;
	public static final double FRICTION_FX_DIST = 0.1;
	public static final double TIME_STEP = 0.01;

	public static final int FRAME_WIDTH = 4096;
	public static final int FRAME_HEIGHT = 2160;
	public static final int FRAME_OFFSET_X = (FRAME_WIDTH - FRAME_HEIGHT) / 2;
	public static final int FRAME_OFFSET_Y = 0;
	public static final double FRAME_MULTIPLIER = 0.0001;
	public static final String DIRECTORY_PATH = "/home/agoston/Desktop/work/java/Universe/universes/"
			+ Main.simulationName;
	public static final File DIRECTORY = new File(DIRECTORY_PATH);
	public static final String IMAGE_FORMAT = "png";
	public static final double CALC_DISTANCE = 50000;
	public static final double CALC_DISTANCE_SQ = Math.pow(CALC_DISTANCE, 2);

}
