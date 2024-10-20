package backend.academy.project2.solve;

import backend.academy.project2.maze.Coordinate;
import backend.academy.project2.maze.Maze;
import backend.academy.project2.solve.astar.AStarSolver;
import backend.academy.project2.solve.bfs.BFSSolver;

public class SolverFactory {
    public static Solver createSolver(SolverType type, Maze maze, Coordinate start, Coordinate goal) {
        return switch (type) {
            case ASTAR -> new AStarSolver(maze, start, goal);
            case BFS -> new BFSSolver(maze, start, goal);
        };
    }
}
