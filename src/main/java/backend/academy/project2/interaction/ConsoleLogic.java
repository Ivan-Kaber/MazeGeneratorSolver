package backend.academy.project2.interaction;

import backend.academy.project2.generate.Generator;
import backend.academy.project2.generate.GeneratorFactory;
import backend.academy.project2.generate.GeneratorType;
import backend.academy.project2.maze.Coordinate;
import backend.academy.project2.maze.Maze;
import backend.academy.project2.render.MazeRenderer;
import backend.academy.project2.render.Renderer;
import backend.academy.project2.solve.SolverFactory;
import backend.academy.project2.solve.SolverType;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

public class ConsoleLogic {
    private final PrintStream out;
    private final Scanner scanner;
    private Generator generator;
    private Maze maze;
    private final Renderer renderer;
    @Getter
    private Coordinate start;
    @Getter
    private Coordinate goal;
    @Getter
    private int heightSize;
    @Getter
    private int widthSize;

    public ConsoleLogic(PrintStream out, Scanner scanner) {
        this.out = out;
        this.scanner = scanner;
        this.renderer = new MazeRenderer();
    }

    public void selectAlgorithm(String choiceAlgorithm) {
        if (GeneratorType.contains(choiceAlgorithm)) {
            generator = GeneratorFactory.createGenerator(GeneratorType.getById(Integer.parseInt(choiceAlgorithm)));
        } else {
            out.print("Вы ввели неверные данные, попробуйте еще раз,"
                      + " введите одну цифру - желаемый алгоритм: ");
            selectAlgorithm(scanner.nextLine());
        }
    }

    public void selectSize(String size, boolean isHeight) {
        if (StringUtils.isNumeric(size) && Integer.parseInt(size) >= Maze.MIN_SIZE
            && Integer.parseInt(size) <= Maze.MAX_SIZE) {
            if (isHeight) {
                heightSize = Integer.parseInt(size);
            } else {
                widthSize = Integer.parseInt(size);
            }
        } else {
            out.printf("""
                Вы ввели неверные данные, попробуйте еще раз, \
                введите значение размерности лабиринта в диапазоне от %d до %d:\s""", Maze.MIN_SIZE, Maze.MAX_SIZE);
            selectSize(scanner.nextLine(), isHeight);
        }
    }

    public void drawMaze() {
        maze = generator.generate(heightSize, widthSize);
        out.println("\n" + renderer.render(maze));
        Place.setMazeDimensions(maze.height(), maze.width());
    }

    public void selectStartOrGoalPoint(String place, boolean isStart) {
        if (Place.contains(place)) {
            if (isStart) {
                start = Place.getById(Integer.parseInt(place));
            } else {
                goal = Place.getById(Integer.parseInt(place));
            }
        } else {
            out.print("Вы ввели неверные данные, попробуйте еще раз, введите одну цифру"
                      + " - желаемую точку в лабиринте: ");
            selectStartOrGoalPoint(scanner.nextLine(), isStart);
        }
    }

    public void selectSolver(String solver) {
        if (SolverType.contains(solver)) {
            List<Coordinate> path =
                SolverFactory.createSolver(SolverType.getById(Integer.parseInt(solver)), maze, start, goal).solve();
            if (!path.isEmpty()) {
                out.println(renderer.render(maze, path));
                out.println("Путь найден!");
            } else {
                out.println("Путь не найден!");
            }
        } else {
            out.print("Вы ввели неверные данные, "
                      + "попробуйте еще раз, введите одну цифру - желаемый алгоритм: ");
            selectSolver(scanner.nextLine());
        }
    }
}
