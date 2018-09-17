package no.driftfun.javatracer.objects;

import no.driftfun.javatracer.utils.Constants;
import no.driftfun.javatracer.utils.HitRecord;
import no.driftfun.javatracer.utils.HitResult;

import static no.driftfun.javatracer.utils.Vec3.vec3;
import java.util.ArrayList;
import java.util.List;

public class World {
	List<Hittable> world = new ArrayList<>();

	public void add(Hittable hittableObject) {
		this.world.add(hittableObject);
	}

	public HitResult hit(Ray ray, double t_min, double t_max, HitRecord rec) {
		HitRecord temp_rec = new HitRecord(1.0, vec3(20.0,1.0,1.0), Constants.VEC_ONE);
		boolean hit_anything = false;
		double closest_so_far = t_max;

		for(Hittable object : world) {
			if(object.hit(ray, t_min, closest_so_far, temp_rec)) {
				hit_anything = true;
				closest_so_far = temp_rec.getT();
				rec = temp_rec;
			}
		}

		return new HitResult(hit_anything, rec);
	}
}
