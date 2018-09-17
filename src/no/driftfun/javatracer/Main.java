package no.driftfun.javatracer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	    PathTrace pt = new PathTrace();
	    long start = System.currentTimeMillis();
	    pt.pixels(400, 200);
	    long stop = System.currentTimeMillis();
	    System.out.println("Time taken: " + (stop - start) + "ms");
    }
}
