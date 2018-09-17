package no.driftfun.javatracer.utils;

import no.driftfun.javatracer.utils.Vec3;

public class HitRecord {
	private double t;
	private Vec3 p;
	private Vec3 normal;

	public HitRecord(double t, Vec3 p, Vec3 normal) {
		this.t = t;
		this.p = p;
		this.normal = normal;
	}

	public double getT() {
		return t;
	}

	public void setT(double t) {
		this.t = t;
	}

	public Vec3 getP() {
		return p;
	}

	public void setP(Vec3 p) {
		this.p = p;
	}

	public Vec3 getNormal() {
		return normal;
	}

	public void setNormal(Vec3 normal) {
		this.normal = normal;
	}
}
