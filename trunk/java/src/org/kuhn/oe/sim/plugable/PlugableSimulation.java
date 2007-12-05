package org.kuhn.oe.sim.plugable;

import java.util.List;
import org.kuhn.oe.game.Player;
import org.kuhn.oe.sim.SimulationTemplate;
import org.kuhn.oe.sim.plugable.breeder.Breeder;
import org.kuhn.oe.sim.plugable.init.Initializer;
import org.kuhn.oe.sim.plugable.printer.Printer;
import org.kuhn.oe.sim.plugable.selector.Selector;
import org.kuhn.oe.sim.plugable.test.Tester;
import org.kuhn.oe.sim.plugable.terminator.Terminator;

public class PlugableSimulation extends SimulationTemplate {
	private Initializer	initializer;
	private Terminator	terminator;
	private Tester		tester;
	private Selector	selector;
	private Breeder		breeder;
	private Printer		printer;

	public void setInitializer(Initializer initializer) {
		this.initializer = initializer;
	}
	public void setTerminator(Terminator terminator) {
		this.terminator = terminator;
	}
	public void setTester(Tester tester) {
		this.tester = tester;
	}
	public void setSelector(Selector selector) {
		this.selector = selector;
	}
	public void setBreeder(Breeder breeder) {
		this.breeder = breeder;
	}
	public void setPrinter(Printer printer) {
		this.printer = printer;
	}

	@Override
	protected List<Player> init() {
		return initializer.initialize(getPopulationSize());
	}
	
	@Override
	protected boolean terminate(List<Player> population) {
		return terminator.terminate(population);
	}

	@Override
	protected void test(List<Player> population) {
		tester.test(population);
	}

	@Override
	protected Player select(List<Player> population) {
		return selector.select(population);
	}

	@Override
	protected Player breed(Player player) {
		return breeder.breed(player);
	}
	
	@Override
	protected void printSimulationBanner(List<Player> population) {
		printer.printSimulationBanner(population);
	}
	
	@Override
	protected void printGenerationHeader(List<Player> population) {
		printer.printGenerationHeader(population);
	}
	
	@Override
	protected void printTestResult(List<Player> population) {
		printer.printTestResult(population);
	}
}