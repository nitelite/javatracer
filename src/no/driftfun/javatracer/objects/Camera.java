package no.driftfun.javatracer.objects;

import no.driftfun.javatracer.utils.Vec3;

import static no.driftfun.javatracer.utils.Vec3.vec3;

public class Camera {

	private final Vec3 lower_left_corner;
	private final Vec3 horizontal;
	private final Vec3 vertical;
	private final Vec3 origin;

	public Camera() {
		this.lower_left_corner = vec3(-2.0, -1.0, -1.0);
		this.horizontal = vec3(4.0, 0.0, 0.0);
		this.vertical = vec3(0.0, 2.0, 0.0);
		this.origin = vec3(0.0, 0.0, 0.0);
	}

	public Ray getRay(double u, double v) {
		Vec3 a = this.origin;
		Vec3 b = this.lower_left_corner.add(this.horizontal.mul(u)).add(this.vertical.mul(v)).sub(this.origin);
		return new Ray(a, b);
	}
}
