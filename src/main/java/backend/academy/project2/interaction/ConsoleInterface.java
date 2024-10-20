package backend.academy.project2.interaction;

import backend.academy.project2.generate.GeneratorType;
import backend.academy.project2.maze.Maze;
import backend.academy.project2.solve.SolverType;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleInterface {
    private final Scanner scanner;
    private final PrintStream out;
    private final ConsoleLogic consoleLogic;

    public ConsoleInterface(PrintStream out, Scanner scanner) {
        this.scanner = scanner;
        this.out = out;
        consoleLogic = new ConsoleLogic(out, scanner);
    }

    public void processUserInteraction() {
        selectGenerator();
        selectMazeSize();
        consoleLogic.drawMaze();
        selectStartAndGoalPoints();
        selectSolver();
    }

    private void selectGenerator() {
        out.printf("""
            %nВыберите алгоритм для построения лабиринта%n\
            %s%n\
            Введите одну цифру - желаемый алгоритм:\s""", GeneratorType.getOrderView());
        consoleLogic.selectAlgorithm(scanner.nextLine());
    }

    private void selectMazeSize() {
        out.printf("""
            %nВыберите размерность лабиринта в диапазоне от %d до %d.%n\
            Введите значение высоты лабиринта:\s""", Maze.MIN_SIZE, Maze.MAX_SIZE);
        consoleLogic.selectSize(scanner.nextLine(), true); //Второй параметр - true, т.к. вводится высота

        out.printf("""
            %nВведите значение ширины лабиринта в диапазоне от %d до %d:\s""", Maze.MIN_SIZE, Maze.MAX_SIZE);
        consoleLogic.selectSize(scanner.nextLine(), false); //Второй параметр - false, т.к. вводится ширина
    }

    private void selectStartAndGoalPoints() {
        selectPoint("начала", true);
        selectPoint("цели", false);
    }

    private void selectPoint(String pointType, boolean isStart) {
        out.printf("""
            %nВыберите точку %s построения пути:%n\
            %s%n\
            Введите одну цифру - желаемую точку %s построения пути:\s""", pointType, Place.getOrderView(), pointType);
        consoleLogic.selectStartOrGoalPoint(scanner.nextLine(), isStart);
    }

    private void selectSolver() {
        out.printf("""
            %nВыберите алгоритм поиска пути:%n\
            %s%n\
            Введите одну цифру - желаемый алгоритм поиска пути:\s""", SolverType.getOrderView());
        consoleLogic.selectSolver(scanner.nextLine());
    }
}
