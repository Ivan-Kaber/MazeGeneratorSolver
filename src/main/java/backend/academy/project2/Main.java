package backend.academy.project2;

import backend.academy.project2.generate.Generator;
import backend.academy.project2.generate.PrimGenerator;
import backend.academy.project2.generate.RecursiveBacktrackGenerator;
import backend.academy.project2.interaction.ConsoleInterface;
import backend.academy.project2.maze.Coordinate;
import backend.academy.project2.maze.Maze;
import backend.academy.project2.render.MazeRenderer;
import backend.academy.project2.render.Renderer;
import backend.academy.project2.solve.Solver;
import backend.academy.project2.solve.SolverFactory;
import backend.academy.project2.solve.SolverType;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

@SuppressFBWarnings("DM_DEFAULT_ENCODING")
public class Main {
    public static void main(String[] args) {

        PrintStream out = new PrintStream(System.out);
        Scanner scanner = new Scanner(System.in);
        ConsoleInterface consoleInterface = new ConsoleInterface(out, scanner);
        consoleInterface.processUserInteraction();
    }
}
