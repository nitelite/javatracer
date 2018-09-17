package no.driftfun.javatracer;

import no.driftfun.javatracer.objects.Camera;
import no.driftfun.javatracer.objects.Ray;
import no.driftfun.javatracer.objects.Sphere;
import no.driftfun.javatracer.objects.World;
import no.driftfun.javatracer.utils.Constants;
import no.driftfun.javatracer.utils.HitRecord;
import no.driftfun.javatracer.utils.HitResult;
import no.driftfun.javatracer.utils.Vec3;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static no.driftfun.javatracer.utils.Vec3.vec3;

public class PathTrace {
	private Random random = new Random();
	private double MAX_DISTANCE = 1.7976931348623157e+307;

	private void writePng(BufferedImage img) throws IOException {
		File out = new File("render/out.png");
		ImageIO.write(img, "png", out);
	}

	private Vec3 randomInUnitSphere() {
		double u = random.nextDouble();
		double v = random.nextDouble();
		double theta = 2 * Math.PI * u;
		double phi = Math.acos(2 * v - 1);

		double x = (Math.sin(phi) * Math.cos(theta));
		double y = (Math.sin(phi) * Math.sin(theta));
		double z = (Math.cos(phi));

		return new Vec3(x, y, z);
	}

	private Vec3 color(Ray ray, World world) {
		HitRecord startRecord = new HitRecord(1.0, Constants.VEC_ZERO, Constants.VEC_ONE);
		HitResult hitResult = world.hit(ray, 0.001, MAX_DISTANCE, startRecord);

		if(hitResult.didWeHit()) {
			// Get object color if we did hit something by sampling in a random direction
			HitRecord rec = hitResult.getHitRecord();
			Vec3 target = rec.getP().add(rec.getNormal()).add(randomInUnitSphere());
			return color(new Ray(rec.getP(), target.sub(rec.getP())), world).mul(0.5);
		} else {
			// Colorize the background as a gradient if we don't hit
			Vec3 unit_direction = ray.getDirection().normalize();
			double t = (unit_direction.getY() + 1.0) * 0.5;
			return Constants.VEC_ONE.mul(1.0 - t).add(vec3(0.5, 0.7, 1.0).mul(t));
		}
	}

	private Vec3 sampleRay(int i, int j, int width, int height, Camera cam, World world) {
		double u = (i + random.nextDouble()) / (double)width;
		double v = (j + random.nextDouble()) / (double)height;

		Ray r = cam.getRay(u, v);
		return color(r, world);
		//col = col.add();
	}

	public void pixels(int width, int height) throws IOException {
		int numSamples = 100;

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		World world = new World();
		world.add(new Sphere(vec3(0, -100.5, -1), 100.0));
		world.add(new Sphere(vec3(0, 0, -1), 0.5));

		Camera cam = new Camera();

    	for(int j = height; j > 0; j--) {
			for (int i = 0; i < width; i++) {
				final int jj = j;
				final int ii = i;

				Vec3 col = IntStream.range(0, numSamples)
					.unordered()
					.parallel()
					.mapToObj(index -> sampleRay(ii, jj, width, height, cam, world))
					.reduce(Constants.VEC_ZERO, Vec3::add)
					.divide(numSamples);

				// gamma-correct color by squaring it
				col = vec3(Math.sqrt(col.getR()), Math.sqrt(col.getG()), Math.sqrt(col.getB()));

				img.setRGB(i, height - j, col.asColor().getRGB());
			}
		}

		writePng(img);
	}
}
