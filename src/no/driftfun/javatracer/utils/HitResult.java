package no.driftfun.javatracer.utils;

public class HitResult {
	private final boolean didWeHit;
	private final HitRecord hitRecord;

	public HitResult(boolean didWeHit, HitRecord hitRecord) {
		this.didWeHit = didWeHit;
		this.hitRecord = hitRecord;
	}

	public boolean didWeHit() {
		return didWeHit;
	}

	public HitRecord getHitRecord() {
		return hitRecord;
	}
}
