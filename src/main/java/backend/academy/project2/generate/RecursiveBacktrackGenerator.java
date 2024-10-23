package backend.academy.project2.generate;

import backend.academy.project2.maze.Cell;
import backend.academy.project2.maze.Coordinate;
import backend.academy.project2.maze.Direction;
import backend.academy.project2.maze.Maze;
import java.security.SecureRandom;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class RecursiveBacktrackGenerator implements Generator {
    private final SecureRandom random = new SecureRandom();
    private final Deque<Coordinate> coordinates = new ArrayDeque<>();
    private static final double PROBABILITY = 0.2;

    @Override
    public Maze generate(int height, int width) {
        int newHeight = height;
        if (newHeight % 2 == 0) {
            newHeight++;
        }
        int newWidth = width;
        if (newWidth % 2 == 0) {
            newWidth++;
        }
        Maze maze = new Maze(newHeight, newWidth);

        Coordinate startCoordinate = new Coordinate(random.nextInt(height / 2) * 2 + 1,
                random.nextInt(width / 2) * 2 + 1);

        coordinates.push(startCoordinate);
        while (!coordinates.isEmpty()) {
            Coordinate currentCell = coordinates.pop();

            List<Direction> unvisitedNeighbors = getUnvisitedNeighbors(maze, currentCell);
            if (!unvisitedNeighbors.isEmpty()) {
                coordinates.push(currentCell);

                Direction randomUnvisitedNeighbor = unvisitedNeighbors.get(random.nextInt(unvisitedNeighbors.size()));
                Coordinate neighbor = getNeighbor(currentCell, randomUnvisitedNeighbor);

                // Удаляем стену между текущей ячейкой и выбранной
                maze.setCellType(new Coordinate(currentCell.row() + randomUnvisitedNeighbor.rowOffset(),
                        currentCell.col() + randomUnvisitedNeighbor.colOffset()), Cell.Type.PASSAGE);

                maze.setCellType(neighbor, Cell.Type.PASSAGE);

                coordinates.push(neighbor);
            }
        }
        MazeDecorator.addRandomCycles(maze, PROBABILITY);
        MazeDecorator.addRandomSurfacesToPassages(maze);

        return maze;
    }

    private List<Direction> getUnvisitedNeighbors(Maze maze, Coordinate cell) {
        List<Direction> unvisitedNeighbors = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            Coordinate newCoordinate = new Coordinate(
                    cell.row() + direction.rowOffset() * 2,
                    cell.col() + direction.colOffset() * 2
            );

            if (maze.isWithinBounds(newCoordinate) && maze.isWall(newCoordinate)) {
                unvisitedNeighbors.add(direction);
            }
        }
        return unvisitedNeighbors;
    }

    private Coordinate getNeighbor(Coordinate cell, Direction direction) {
        return new Coordinate(cell.row() + direction.rowOffset() * 2,
                cell.col() + direction.colOffset() * 2);
    }
}

