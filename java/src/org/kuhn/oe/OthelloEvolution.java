package org.kuhn.oe;
import java.io.FileOutputStream;
import org.kuhn.oe.game.Player;
import org.kuhn.oe.sim.plugable.PlugableSimulation;
import org.kuhn.oe.sim.plugable.breeder.Breeder;
import org.kuhn.oe.sim.plugable.breeder.CloneBreeder;
import org.kuhn.oe.sim.plugable.breeder.FreshBloodBreeder;
import org.kuhn.oe.sim.plugable.breeder.MassMutationBreeder;
import org.kuhn.oe.sim.plugable.breeder.RegularMutationBreeder;
import org.kuhn.oe.sim.plugable.breeder.RoundRobinBreeder;
import org.kuhn.oe.sim.plugable.breeder.SingleMutationBreeder;
import org.kuhn.oe.sim.plugable.breeder.mutator.Mutator;
import org.kuhn.oe.sim.plugable.breeder.mutator.SimpleMutator;
import org.kuhn.oe.sim.plugable.init.Initializer;
import org.kuhn.oe.sim.plugable.init.PrototypePlayerInitializer;
import org.kuhn.oe.sim.plugable.terminator.GenerationCountTerminator;
import org.kuhn.oe.sim.plugable.terminator.Terminator;
import org.kuhn.oe.sim.plugable.printer.ConsolePrinter;
import org.kuhn.oe.sim.plugable.printer.CsvPrinter;
import org.kuhn.oe.sim.plugable.printer.MultiPrinter;
import org.kuhn.oe.sim.plugable.printer.Printer;
import org.kuhn.oe.sim.plugable.selector.RouletteWheelSelector;
import org.kuhn.oe.sim.plugable.selector.Selector;
import org.kuhn.oe.sim.plugable.selector.TopThirdSelector;
import org.kuhn.oe.sim.plugable.test.EqualPlayTester;
import org.kuhn.oe.sim.plugable.test.RandomPlayTester;
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
		initializer.setPrototypePlayer(new Player(strategies));
		initializer.setBreeder(new MassMutationBreeder(SimpleMutator.MAJOR_MUTATOR));
		
		// terminator
		Terminator terminator = new GenerationCountTerminator(1000);
		
		// fitness test
//		RandomPlayTester tester = new RandomPlayTester();
//		tester.setGameCountPerGeneration(250);
		EqualPlayTester tester = new EqualPlayTester();
		tester.setGameCountPerPlayer(20);
		
		// selection
//		Selector selector = new TopThirdSelector();
		Selector selector = new RouletteWheelSelector();
		
		// breeder
		Breeder breeder = new RoundRobinBreeder(
				new CloneBreeder(),
				new CloneBreeder(),
				new CloneBreeder(),
				new CloneBreeder(),
				new CloneBreeder(),
				new CloneBreeder(),
				new CloneBreeder(),
				new SingleMutationBreeder(SimpleMutator.MINOR_MUTATOR),
				new MassMutationBreeder(SimpleMutator.MINOR_MUTATOR),
				new FreshBloodBreeder(initializer)
		);
		
		// output
//		Printer printer = new ConsolePrinter();
//		Printer printer = new CsvPrinter();
		Printer printer = new MultiPrinter(
		new ConsolePrinter(),
		new CsvPrinter(new FileOutputStream("oe.csv"))
		);
		
		// build simulation and execute
		PlugableSimulation simulation = new PlugableSimulation();
		simulation.setPopulationSize(25);
		simulation.setInitializer(initializer);
		simulation.setTerminator(terminator);
		simulation.setTester(tester);
		simulation.setSelector(selector);
		simulation.setBreeder(breeder);
		simulation.setPrinter(printer);
		simulation.execute();
	}
}