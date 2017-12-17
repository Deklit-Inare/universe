package com.deklitinare.universe.mechanics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.deklitinare.universe.Main;
import com.deklitinare.universe.Settings;

public class Universe {

	Time time = new Time();

	public Universe(int n) {

		physics = Physics.DEFAULT;

		for (int i = 0; i < n / 2; i++) {

			particles.add(i,
					new Particle(Main.rnd.nextDouble() * 500 * (Main.rnd.nextBoolean() ? 1 : -1) - 1000,
							(Main.rnd.nextBoolean() ? 1 : -1) * Main.rnd.nextDouble() * 500 - 1000,
							Main.rnd.nextDouble() * 10 + 1));

		}

		for (int i = 0; i < n / 2; i++) {

			particles.add(i,
					new Particle(Main.rnd.nextDouble() * 500 * (Main.rnd.nextBoolean() ? 1 : -1) + 1000,
							(Main.rnd.nextBoolean() ? 1 : -1) * Main.rnd.nextDouble() * 500 + 1000,
							Main.rnd.nextDouble() * 10 + 1));

		}
		/*
		 * Particle big1 = particles.get(Main.rnd.nextInt(n)); Particle big2 =
		 * particles.get(Main.rnd.nextInt(n));
		 * 
		 * big1.mass = 100; big2.mass = 100;
		 * 
		 * big1.velocity.x = -10; big1.velocity.y = 33;
		 * 
		 * big2.velocity.x = 10; big2.velocity.y = -33;
		 * 
		 * big1.x = 5000; big2.x = -5000;
		 */

	}

	public List<Particle> delete = new ArrayList<Particle>();

	public List<Particle> particles = new ArrayList<Particle>();
	private Physics physics;

	public void step() {

		// System.out.println(particles[0]);

		for (Particle p1 : particles) {

			Force f = physics.getSummedForces(p1, particles);
			p1.setNextForce(f);

		}

		for (Particle p : particles) {

			p.step();

		}

		for (Particle p : delete) {
			particles.remove(p);
		}

		delete.clear();
		System.out.println(particles.size());

		time.step();

		for (Particle p : particles) {

			p.charge = Math.sin(p.charge_offset + (time.time * Math.PI * p.charge_freq));

		}

	}

	public static Graphics g;

	Point befpoint = null;
	BufferedImage track_history = new BufferedImage(Settings.FRAME_WIDTH, Settings.FRAME_HEIGHT,
			BufferedImage.TYPE_4BYTE_ABGR);

	public void paint(Graphics g) {

		int tracked = 483;

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Settings.FRAME_WIDTH, Settings.FRAME_HEIGHT);

		boolean drawTracking = false;

		if (befpoint == null) {
			befpoint = getScreenPosition(particles.get(tracked).x, particles.get(tracked).y);
		} else {
			drawTracking = true;
		}

		for (Particle p : particles) {

			double pos = (p.charge + 1) / 2;
			double neg = 1 - pos;

			g.setColor(new Color((int)(pos * 255), 0, (int)(neg * 255)));

			Point point1 = getScreenPosition(p.x, p.y);

			Point point2 = getScreenPosition(p.x - p.velocity.x, p.y - p.velocity.y);

			int r = (int) Math.max(Math.sqrt(p.mass), 1);

			g.fillRect(point1.x - r, point1.y - r, 2 * r, 2 * r);

			g.setColor(new Color((int)(pos * 223 + 32), 32, (int)(pos * 223 + 32)));
			g.drawLine(point1.x, point1.y, point2.x, point2.y);

		}
		/*
		 * Point after = getScreenPosition(particles.get(tracked).x,
		 * particles.get(tracked).y);
		 * 
		 * g.setColor(Color.WHITE);
		 * 
		 * if(drawTracking) { track_history.getGraphics().drawLine(befpoint.x,
		 * befpoint.y, after.x, after.y); }
		 * 
		 * befpoint = after;
		 * 
		 * g.drawImage(track_history, 0, 0, null);
		 */

	}

	public static Point getScreenPosition(double x, double y) {

		int px = (int) (Settings.FRAME_OFFSET_X + (Settings.FRAME_MULTIPLIER * x + 0.5) * Settings.FRAME_HEIGHT);
		int py = (int) (Settings.FRAME_OFFSET_Y + (Settings.FRAME_MULTIPLIER * y + 0.5) * Settings.FRAME_HEIGHT);

		return new Point(px, py);

	}

}
