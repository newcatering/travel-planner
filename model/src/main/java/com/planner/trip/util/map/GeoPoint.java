package com.planner.trip.util.map;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class GeoPoint {
	double x;
	double y;
	double z;

	public GeoPoint() {
		super();
	}

	public GeoPoint(double x, double y) {
		super();
		this.x = x;
		this.y = y;
		this.z = 0;
	}

	public GeoPoint(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
