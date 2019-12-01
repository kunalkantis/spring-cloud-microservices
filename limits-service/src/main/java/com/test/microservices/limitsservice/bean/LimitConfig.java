package com.test.microservices.limitsservice.bean;

public class LimitConfig {

	private int max;
	private int min;

	public LimitConfig() {
	}

	public LimitConfig(int max, int min) {
		super();
		this.max = max;
		this.min = min;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

}
