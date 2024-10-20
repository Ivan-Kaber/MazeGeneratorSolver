package backend.academy.project2.solve;

import backend.academy.project2.maze.Coordinate;
import backend.academy.project2.maze.Direction;
import backend.academy.project2.maze.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractSolver implements Solver {
    protected final Maze maze;
    protected final Coordinate start;
    protected final Coordinate goal;

    @Override
    public List<Coordinate> solve() {
        return List.of();
    }

    public AbstractSolver(Maze maze, Coordinate start, Coordinate goal) {
        this.maze = maze;
        this.start = start;
        this.goal = goal;
    }

    protected <T extends AbstractNode<T>> List<Coordinate> constructPath(T node) {
        List<Coordinate> path = new ArrayList<>();
        T currentNode = node;
        while (currentNode != null) {
            path.add(currentNode.coordinate());
            currentNode = currentNode.parent();
        }
        Collections.reverse(path);
        return path;
    }

    protected List<Coordinate> getValidNeighbors(Coordinate coordinate) {
        List<Coordinate> neighbors = new ArrayList<>();

        for (Direction direction : Direction.values()) {
            Coordinate newCoordinate = new Coordinate(
                    coordinate.row() + direction.rowOffset(),
                    coordinate.col() + direction.colOffset());
            if (maze.isWithinBounds(newCoordinate)) {
                neighbors.add(newCoordinate);
            }
        }

        return neighbors;
    }
}
