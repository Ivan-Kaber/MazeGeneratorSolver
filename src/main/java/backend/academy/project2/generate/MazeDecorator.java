package backend.academy.project2.generate;

import backend.academy.project2.maze.Cell;
import backend.academy.project2.maze.Coordinate;
import backend.academy.project2.maze.Direction;
import backend.academy.project2.maze.Maze;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class MazeDecorator {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int BARRIER_CHANCE = 10;
    private static final int BOOST_CHANCE = 10;
    private static final int HUNDRED = 100;

    static void addRandomSurfacesToPassages(Maze maze) {
        for (int row = 0; row < maze.height(); row++) {
            for (int col = 0; col < maze.width(); col++) {
                if (maze.getCell(row, col).type() == Cell.Type.PASSAGE) {
                    maze.setCellType(new Coordinate(row, col), randomSurface());
                }
            }
        }
    }

    static void addRandomCycles(Maze maze, double probability) {
        for (int row = 1; row < maze.height() - 1; row++) {
            for (int col = 1; col < maze.width() - 1; col++) {
                if (maze.getCell(row, col).type() == Cell.Type.WALL) {
                    List<Direction> passageNeighbors = getPassageNeighbors(maze, new Coordinate(row, col));
                    if (passageNeighbors.size() > 1 && RANDOM.nextDouble() < probability) {
                        maze.setCellType(new Coordinate(row, col), Cell.Type.PASSAGE);
                    }
                }
            }
        }
    }

    private static List<Direction> getPassageNeighbors(Maze maze, Coordinate cell) {
        List<Direction> passageNeighbors = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            Coordinate newCoordinate = new Coordinate(
                cell.row() + direction.rowOffset(),
                cell.col() + direction.colOffset());
            if (maze.isWithinBounds(newCoordinate) && maze.isPassage(newCoordinate)) {
                passageNeighbors.add(direction);
            }
        }
        return passageNeighbors;
    }



    private static Cell.Type randomSurface() {
        int chance = RANDOM.nextInt(HUNDRED);
        if (chance < BARRIER_CHANCE) {
            return Cell.Type.BARRIER;
        }
        if (chance < (BOOST_CHANCE + BARRIER_CHANCE)) {
            return Cell.Type.BOOST;
        }
        return Cell.Type.PASSAGE;
    }

    private MazeDecorator() {
    }
}
