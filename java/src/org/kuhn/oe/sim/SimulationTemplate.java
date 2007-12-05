package org.kuhn.oe.sim;

import java.util.ArrayList;
import java.util.List;

import org.kuhn.oe.game.Player;

public abstract class SimulationTemplate implements Simulation {
	private int populationSize;

	public int getPopulationSize() {
		return populationSize;
	}
	public final void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	public final void execute() {
		List<Player> population = init();
		printSimulationBanner(population);
		while (!terminate(population)) {
			printGenerationHeader(population);
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
	protected abstract boolean terminate(List<Player> population);
	protected abstract Player select(List<Player> population);
	protected abstract Player breed(Player player);
	
	protected abstract void printSimulationBanner(List<Player> population);
	protected abstract void printGenerationHeader(List<Player> population);
	protected abstract void printTestResult(List<Player> population);
}
