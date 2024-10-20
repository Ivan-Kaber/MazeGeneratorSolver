package backend.academy.project2.generate;

import backend.academy.project2.maze.Cell;
import backend.academy.project2.maze.Coordinate;
import backend.academy.project2.maze.Maze;
import java.security.SecureRandom;

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
