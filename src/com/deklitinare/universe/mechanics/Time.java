package com.deklitinare.universe.mechanics;

import com.deklitinare.universe.Settings;

public class Time {

	public double time = 0;
	public double step = Settings.TIME_STEP;

	public Time() {

	}

	public Time(double step, double time) {

		this.time = time;
		this.step = step;

	}

	public Time(double step) {

		this.step = step;

	}

	public double step() {
		return (time += step);
	}

}
