package com.deklitinare.universe.mechanics;

import com.deklitinare.universe.Main;

public class Particle {

	public Force nextForce;
	public double mass;
	public Force velocity;

	public double x;
	public double y;

	public double charge_freq = Main.rnd.nextDouble() + 1;
	public double charge_offset = Main.rnd.nextDouble() * Math.PI;

	public double charge = Main.rnd.nextInt(2) * 2 - 1;

	public Particle() {

		x = 0;
		y = 0;

		mass = 1;

		velocity = new Force();

	}

	public Particle(double x, double y) {

		this.x = x;
		this.y = y;

		mass = 1;

		velocity = new Force();

	}

	public Particle(double x, double y, double mass) {

		this.x = x;
		this.y = y;

		this.mass = mass;

		velocity = new Force();

	}

	public Particle(double x, double y, Force velocity) {

		this.x = x;
		this.y = y;

		mass = 1;

		this.velocity = velocity;

	}

	public Particle(double x, double y, double mass, Force velocity) {

		this.x = x;
		this.y = y;

		this.mass = mass;

		this.velocity = velocity;

	}

	public void setNextForce(Force f) {

		nextForce = f;

	}

	@Override
	public String toString() {

		return "[x=" + x + ", y=" + y + "]";

	}

	public void applyNextForce() {

		applyForce(nextForce);

	}

	public void step() {

		applyNextForce();

		double prx = x + velocity.x;
		double pry = y + velocity.y;

		int border = 100000;

		if (prx > border || prx < -border) {
			delete();
		}

		if (pry > border || pry < -border) {
			delete();
		}

		x += velocity.x;
		y += velocity.y;

	}

	public void delete() {
		Main.universe.delete.add(this);
		System.out.println("Removed Particle" + this + "\tThere are " + Main.universe.particles.size()
				+ " particles left in the universe.");
	}

	public void applyForce(Force f) {

		velocity.add(f, 1 / (mass + 5));

	}

	public double distance(Particle p2) {

		return Math.sqrt(distanceSquared(p2));

	}

	public double distanceSquared(Particle p2) {

		double dx = x - p2.x;
		double dy = y - p2.y;

		return dx * dx + dy * dy;

	}

}
