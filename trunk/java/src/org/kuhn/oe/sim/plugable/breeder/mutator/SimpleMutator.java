package org.kuhn.oe.sim.plugable.breeder.mutator;

import java.util.Random;

public class SimpleMutator implements Mutator {
	private double scale;
	public SimpleMutator() {
		this(1.0d);
	}
	public SimpleMutator(double scale) {
		this.scale = scale;
	}
	public double mutate(double weight) {
		return weight + ((random.nextDouble() * 2.0d * scale) - scale);
	}
	private static final Random random = new Random();
	
	public static final Mutator MINOR_MUTATOR = new SimpleMutator(0.1);
	public static final Mutator MAJOR_MUTATOR = new SimpleMutator(1.0);
}