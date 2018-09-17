package no.driftfun.javatracer.utils;

import java.awt.*;

public class Vec3 {
	private final double x;
	private final double y;
	private final double z;

	public Vec3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public double getR() {
		return x;
	}

	public double getG() {
		return y;
	}

	public double getB() {
		return z;
	}

	public Color asColor() {
		return new Color((float)x, (float)y, (float)z);
	}

	public double length() {
		return Math.sqrt(x*x + y*y + z*z);
	}

	public Vec3 normalize() {
		return this.divide(this.length());
	}

	public Vec3 add(double f) {
		return new Vec3(this.x + f, this.y + f, this.z + f);
	}

	public Vec3 add(Vec3 b) {
		return new Vec3(this.x + b.x, this.y + b.y, this.z + b.z);
	}

	public Vec3 sub(double f) {
		return new Vec3(this.x - f, this.y - f, this.z - f);
	}

	public Vec3 sub(Vec3 b) {
		return new Vec3(this.x - b.x, this.y - b.y, this.z - b.z);
	}

	public Vec3 mul(double f) {
		return new Vec3(this.x * f, this.y * f, this.z * f);
	}

	public Vec3 mul(Vec3 b) {
		return new Vec3(this.x * b.x, this.y * b.y, this.z * b.z);
	}

	public Vec3 divide(double f) {
		return new Vec3(this.x / f, this.y / f, this.z / f);
	}

	public Vec3 divide(Vec3 b) {
		return new Vec3(this.x / b.x, this.y / b.y, this.z / b.z);
	}

	public Vec3 clamp(double min, double max) {
		double cx = Math.min(Math.max(this.x, min), max);
		double cy = Math.min(Math.max(this.y, min), max);
		double cz = Math.min(Math.max(this.z, min), max);
		return new Vec3(cx, cy, cz);
	}

	public static double dot(Vec3 a, Vec3 b) {
		return (a.x * b.x) + (a.y * b.y) + (a.z * b.z);
	}

	public static Vec3 vec3(double x, double y, double z) {
		return new Vec3(x, y, z);
	}
}
