package org.kuhn.oe.sim;

public interface Simulation {
	void setPopulationSize(int populationSize);
	void setGenerationCount(int generationCount);
	void execute();
}
