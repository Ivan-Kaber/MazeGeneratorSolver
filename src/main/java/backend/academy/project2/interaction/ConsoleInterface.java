package backend.academy.project2.interaction;

import backend.academy.project2.generate.GeneratorType;
import backend.academy.project2.maze.Maze;
import backend.academy.project2.solve.SolverType;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleInterface {
    private final Scanner scanner;
    private final PrintStream out;
    private ConsoleLogic consoleLogic;

    public ConsoleInterface(PrintStream out, Scanner scanner) {
        this.scanner = scanner;
        this.out = out;
        consoleLogic = new ConsoleLogic(out, scanner);
    }

    public void processUserInteraction() {
        out.printf("""
                %nВыберите алгоритм для построения лабиринта%n\
                %s%n\
                Введите одну цифру - желаемый алгоритм:\s""", GeneratorType.getOrderView());
        consoleLogic.selectAlgorithm(scanner.nextLine());

        out.printf("""
                %nВыберите размерность лабиринта в диапазоне от %d до %d.%n\
                Введите значение высоты лабиринта:\s""", Maze.MIN_SIZE, Maze.MAX_SIZE);
        consoleLogic.selectSize(scanner.nextLine(), true);//Второй параметр - true, т.к. вводится высота

        out.printf("""
                %nВведите значение ширины лабиринта в диапазоне от %d до %d:\s""", Maze.MIN_SIZE, Maze.MAX_SIZE);
        consoleLogic.selectSize(scanner.nextLine(), false);//Второй параметр - false, т.к. вводится ширина

        consoleLogic.drawMaze();

        out.printf("""
            %nВыберите точку начала построения пути:%n\
            %s%n\
            Введите одну цифру - желаемую точку начала построения пути:\s""", Place.getOrderView());
        consoleLogic.selectStartOrGoalPoint(scanner.nextLine(), true);//Второй параметр - true т.к. вводится точка начала

        out.printf("""
            %nВыберите точку цели:%n\
            %s%n\
            Введите одну цифру - желаемую точку цели:\s""", Place.getOrderView());
        consoleLogic.selectStartOrGoalPoint(scanner.nextLine(), false);

        out.printf("""
            %nВыберите алгоритм поиска пути:%n\
            %s%n\
            Введите одну цифру - желаемый алгоритм поиска пути:\s""", SolverType.getOrderView());
        consoleLogic.selectSolver(scanner.nextLine());
    }
}
