package org.kuhn.oe.breeder.mutator;

import java.util.Random;

public class SimpleMutator implements Mutator {
	public double mutate(double weight) {
		return weight + ((random.nextDouble() * 2.0d) - 1.0d);
	}
	
	private static final Random random = new Random();
}