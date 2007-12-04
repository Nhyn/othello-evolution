package org.kuhn.oe;
import org.kuhn.oe.game.PlayExecutor;
import org.kuhn.oe.game.Player;
import org.kuhn.oe.sim.plugable.PlugableSimulation;
import org.kuhn.oe.sim.plugable.breeder.Breeder;
import org.kuhn.oe.sim.plugable.breeder.MassMutationBreeder;
import org.kuhn.oe.sim.plugable.breeder.RegularMutationBreeder;
import org.kuhn.oe.sim.plugable.breeder.SingleMutationBreeder;
import org.kuhn.oe.sim.plugable.breeder.mutator.SimpleMutator;
import org.kuhn.oe.sim.plugable.init.PrototypePlayerInitializer;
import org.kuhn.oe.sim.plugable.printer.ConsolePrinter;
import org.kuhn.oe.sim.plugable.printer.Printer;
import org.kuhn.oe.sim.plugable.selector.RouletteWheelSelector;
import org.kuhn.oe.sim.plugable.selector.Selector;
import org.kuhn.oe.sim.plugable.selector.TopThirdSelector;
import org.kuhn.oe.sim.plugable.test.SimpleTester;
import org.kuhn.oe.strategy.CaptureLastTurnStrategy;
import org.kuhn.oe.strategy.CornerStrategy;
import org.kuhn.oe.strategy.DecisiveWinStrategy;
import org.kuhn.oe.strategy.DecreaseOpponentMobilityStrategy;
import org.kuhn.oe.strategy.EdgeStrategy;
import org.kuhn.oe.strategy.HighestTurnoverStrategy;
import org.kuhn.oe.strategy.IncreaseMobilityStrategy;
import org.kuhn.oe.strategy.NearCornerStrategy;
import org.kuhn.oe.strategy.NearEdgeStrategy;
import org.kuhn.oe.strategy.PreventOpponentTurnStrategy;
import org.kuhn.oe.strategy.RandomizationStrategy;
import org.kuhn.oe.strategy.Strategy;


public class OthelloEvolution {
	public static void main(String[] args) throws Exception {
		// initialization
		Strategy[] strategies = new Strategy[] {
		new CornerStrategy(),
		new NearCornerStrategy(),
		new EdgeStrategy(),
		new NearEdgeStrategy(),
		new HighestTurnoverStrategy(),
		new PreventOpponentTurnStrategy(),
		new CaptureLastTurnStrategy(),
		new DecisiveWinStrategy(),
		new IncreaseMobilityStrategy(),
		new DecreaseOpponentMobilityStrategy(),
		new RandomizationStrategy()
		};
		PrototypePlayerInitializer initializer = new PrototypePlayerInitializer();
		initializer.setPrototypePlayer(new Player(new PlayExecutor(strategies)));
		initializer.setBreeder(new MassMutationBreeder(new SimpleMutator()));
		
		// fitness test
		SimpleTester tester = new SimpleTester();
		tester.setGameCountPerGeneration(250);
		
		// selection
//		Selector selector = new TopThirdSelector();
		Selector selector = new RouletteWheelSelector();
		
		// breeder
		Breeder breeder = new RegularMutationBreeder(new SingleMutationBreeder(new SimpleMutator()), 10);
		
		// output
		Printer printer = new ConsolePrinter();
		
		// build simulation and execute
		PlugableSimulation simulation = new PlugableSimulation();
		simulation.setPopulationSize(100);
		simulation.setGenerationCount(1000);
		simulation.setInitializer(initializer);
		simulation.setTester(tester);
		simulation.setSelector(selector);
		simulation.setBreeder(breeder);
		simulation.setPrinter(printer);
		simulation.execute();
	}
}