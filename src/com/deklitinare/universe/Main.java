package com.deklitinare.universe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.deklitinare.universe.mechanics.Force;
import com.deklitinare.universe.mechanics.Universe;

public class Main {

	public static void main(String[] args) {

		// test();

		init();
		run();

	}

	public static void init() {

		Settings.DIRECTORY.mkdirs();

	}

	private static void test() {

		Force f = new Force(4, 6, 22, 13, 1);

		System.out.println(f.x + "\t" + f.y + "\t" + Math.sqrt(f.x * f.x + f.y * f.y));

	}

	public static boolean saveImage(BufferedImage img) {

		try {
			ImageIO.write(img, Settings.IMAGE_FORMAT,
					new File(Settings.DIRECTORY, System.currentTimeMillis() + "." + Settings.IMAGE_FORMAT));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	public static String simulationName = "simulation_" + System.currentTimeMillis();

	public static Universe universe;

	private static void run() {
		universe = new Universe(1000);

		BufferedImage img = new BufferedImage(Settings.FRAME_WIDTH, Settings.FRAME_HEIGHT,
				BufferedImage.TYPE_3BYTE_BGR);

		int x = 0;

		while (true) {

			universe.paint(img.getGraphics());
			System.out.println("Iteration [" + x++ + "]");

			if (x % 1 == 0) {

				boolean saved = saveImage(img);

				System.out.println(saved ? "Saved image." : "Failed to save image.");

			}

			universe.step();

		}
	}

	public static Random rnd = new Random(483933);

}
