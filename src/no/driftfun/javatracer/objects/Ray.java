package no.driftfun.javatracer.objects;

import no.driftfun.javatracer.utils.Vec3;

public class Ray {

	private Vec3 a;
	private Vec3 b;

	public Ray(Vec3 a, Vec3 b) {
		this.a = a;
		this.b = b;
	}

	public Vec3 getOrigin() {
		return this.a;
	}

	public Vec3 getDirection() {
		return this.b;
	}

	public Vec3 pointAtParameter(double t) {
		return this.a.add(b.mul(t));
	}
}
