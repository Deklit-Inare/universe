package com.deklitinare.universe.mechanics;

public class Force {

	public double x;
	public double y;

	public Force(double x, double y) {

		this.x = x;
		this.y = y;

	}

	public Force() {

		x = 0;
		y = 0;

	}

	@Override
	public String toString() {

		return "[x=" + x + ", y=" + y + "]";

	}

	// sqrt(x**2 + y**2) = size
	// x2 + y2 = size**2
	//

	public Force(double p1x, double p1y, double p2x, double p2y, double size) {

		/*
		 * double x = p2x - p1x; double y = p2y - p1y;
		 * 
		 * double xy = x + y;
		 * 
		 * this.x = size * size * (x / xy); this.y = size * size * (y / xy);
		 */

		double x = p2x - p1x;
		double y = p2y - p1y;

		this.x = Math.sqrt(Math.abs(x)) * size * Math.signum(x);

		this.y = Math.sqrt(Math.abs(y)) * size * Math.signum(y);

	}

	public Force(Particle p1, Particle p2, double size) {

		Force f = new Force(p1.x, p1.y, p2.x, p2.y, size);
		x = f.x;
		y = f.y;

	}

	public void add(Force f) {

		x += f.x;
		y += f.y;

	}

	public void add(Force f, double d) {

		x = /*(1 - d) * */x + d * f.x;
		y = /*(1 - d) * */y + d * f.y;

	}

}
