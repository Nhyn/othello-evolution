This project provides a sandbox for experimenting with genetic algorithms. The genetic factors are the weights associated with various strategies in Othello. The fitness of each individual is measured by playing a game against other individuals in the generation. Individuals are selected based on fitness for breeding the next generation. Breeding can introduce a small amount of mutation.

The object model and program structure is designed to be simple and easily extensible. This enables the casual programmer to experiment with different Othello strategies, selection and mutation algorithms, etc.

For example, Othello strategies are implemented using the following interface, which allows the strategy to tell the system which plays it prefers:

`boolean ratePlay(Color color, int col, int row, Board board);`

Here is the graph from a sample run:

![http://othello-evolution.googlecode.com/svn/trunk/sample.png](http://othello-evolution.googlecode.com/svn/trunk/sample.png)

The raw data can be download here: http://othello-evolution.googlecode.com/svn/trunk/sample.csv

To get started, download the source code, compile with Java 5 or above, and run `java org.kuhn.oe.OthelloEvolution`. This class should also be your starting point for exploring the code. Enjoy!