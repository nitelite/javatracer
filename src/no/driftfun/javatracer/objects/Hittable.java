package no.driftfun.javatracer.objects;

import no.driftfun.javatracer.utils.HitRecord;

public interface Hittable {
	boolean hit(Ray ray, double t_min, double t_max, HitRecord rec);
}
