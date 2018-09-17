package no.driftfun.javatracer.objects;

import no.driftfun.javatracer.utils.HitRecord;
import no.driftfun.javatracer.utils.Vec3;

public class Sphere implements Hittable {
	private final Vec3 center;
	private final double radius;

	public Sphere(Vec3 center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	@Override
	public boolean hit(Ray ray, double t_min, double t_max, HitRecord rec) {
		Vec3 oc = ray.getOrigin().sub(this.center);
		double a = Vec3.dot(ray.getDirection(), ray.getDirection());
		double b = Vec3.dot(oc, ray.getDirection());
		double c = Vec3.dot(oc, oc) - (this.radius * this.radius);

		double discriminant = (b * b) - (a * c);

		if (discriminant > 0.0) {
			double temp = (-b - Math.sqrt(b * b - a * c)) / a;
			if(temp < t_max && temp > t_min) {
				rec.setT(temp);
				rec.setP(ray.pointAtParameter(rec.getT()));
				rec.setNormal(rec.getP().sub(this.center).divide(this.radius));
				return true;
			}
			temp = (-b + Math.sqrt(b * b - a * c)) / a;
			if(temp < t_max && temp > t_min) {
				rec.setT(temp);
				rec.setP(ray.pointAtParameter(rec.getT()));
				rec.setNormal(rec.getP().sub(this.center).divide(this.radius));
			}
			return true;
		}

		return false;
	}
}
