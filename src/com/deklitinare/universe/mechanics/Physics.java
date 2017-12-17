package com.deklitinare.universe.mechanics;

import java.io.File;
import java.util.List;

import com.deklitinare.universe.Settings;

public class Physics {

	private PhysicsInterface[] interfaces;

	public Physics(PhysicsInterface... interfaces) {

		this.interfaces = interfaces;

	}

	public Force getForce(Particle p1, Particle p2) {

		Force out = new Force();

		for (PhysicsInterface pi : interfaces) {

			out.add(pi.getForce(p1, p2));

		}

		return out;

	}

	public Force getSummedForces(Particle p1, List<Particle> particles) {

		Force out = new Force();

		for (Particle p2 : particles) {

			if (!p1.equals(p2)

			// p1.distanceSquared(p2) <= Settings.CALC_DISTANCE_SQ

			) {

				Force f = getForce(p1, p2);
				out.add(f);

			}

		}

		return out;

	}

	public static final PhysicsInterface GRAVITY = new PhysicsInterface() {

		@Override
		public Force getForce(Particle p1, Particle p2) {

			double size = (p1.mass * p2.mass) * Settings.CONSTANT_G / p1.distanceSquared(p2);

			return new Force(p1, p2, size);

		}
	};

	public static final PhysicsInterface CHARGE = new PhysicsInterface() {

		@Override
		public Force getForce(Particle p1, Particle p2) {

			double charge = -(p1.charge * p2.charge);
			double size = (p1.mass * p2.mass) / p1.distance(p2) * charge;

			return new Force(p1, p2, size);

		}
	};

	public static final PhysicsInterface FRICTION = new PhysicsInterface() {

		@Override
		public Force getForce(Particle p1, Particle p2) {

			double distance = p1.distance(p2);

			if (distance > Settings.FRICTION_DISTANCE) {
				return new Force();
			}

			double size = 5 / (distance + Settings.FRICTION_FX_DIST);

			return new Force(p1, p2, size);

		}

	};

	public static final Physics DEFAULT = new Physics(GRAVITY, FRICTION);

}
