package backend.academy.project2.generate;

import backend.academy.project2.maze.Cell;
import backend.academy.project2.maze.Coordinate;
import backend.academy.project2.maze.Maze;
import java.util.Random;

public class MazeDecorator {
    private static final Random RANDOM = new Random();
    private static final int BARRIER_CHANCE = 10;
    private static final int BOOST_CHANCE = 10;

    static void addRandomSurfacesToPassages(Maze maze) {
        for (int row = 0; row < Maze.height(); row++) {
            for (int col = 0; col < Maze.width(); col++) {
                if (maze.getCell(row, col).type() == Cell.Type.PASSAGE) {
                    maze.setCellType(new Coordinate(row, col), randomSurface());
                }
            }
        }
    }

    private static Cell.Type randomSurface() {
        int chance = RANDOM.nextInt(100);
        if (chance < BARRIER_CHANCE) {
            return Cell.Type.BARRIER;
        }
        if (chance < (BOOST_CHANCE + BARRIER_CHANCE)) {
            return Cell.Type.BOOST;
        }
        return Cell.Type.PASSAGE;
    }
}
