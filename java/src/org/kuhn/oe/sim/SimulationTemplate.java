package org.kuhn.oe.sim;

import java.util.ArrayList;
import java.util.List;

import org.kuhn.oe.game.Player;

public abstract class SimulationTemplate implements Simulation {
	private int populationSize;
	private int generationCount;

	public int getPopulationSize() {
		return populationSize;
	}
	public final void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}
	public int getGenerationCount() {
		return generationCount;
	}
	public final void setGenerationCount(int generationCount) {
		this.generationCount = generationCount;
	}

	public final void execute() {
		List<Player> population = init();
		for (int i = 0; i < generationCount; ++i) {
			printGenerationNumber(i);
			test(population);
			printTestResult(population);
			List<Player> nextPopulation = new ArrayList<Player>(populationSize);
			while (nextPopulation.size() < populationSize)
				nextPopulation.add(breed(select(population)));
			population = nextPopulation;
		}
	}
	
	protected abstract List<Player> init();
	protected abstract void test(List<Player> population);
	protected abstract Player select(List<Player> population);
	protected abstract Player breed(Player player);
	
	protected abstract void printGenerationNumber(int generationNumber);
	protected abstract void printTestResult(List<Player> population);
}
