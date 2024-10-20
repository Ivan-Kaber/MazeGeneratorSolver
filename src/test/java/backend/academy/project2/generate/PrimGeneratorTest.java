package backend.academy.project2.generate;

import backend.academy.project2.maze.Cell;
import backend.academy.project2.maze.Coordinate;
import backend.academy.project2.maze.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrimGeneratorTest {

    private PrimGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new PrimGenerator();
    }

    @Test
    void testGenerateMazeWithMinimumSize() {
        Maze maze = generator.generate(3, 3);
        assertEquals(3, Maze.height());
        assertEquals(3, Maze.width());
        assertTrue(maze.isPassage(new Coordinate(1, 1)));
    }

    @Test
    void testGenerateMazeWithEvenDimensions() {
        Maze maze = generator.generate(4, 4);
        assertEquals(4, Maze.height());
    }

    @Test
    void testGenerateMazeWithOddDimensions() {
        Maze maze = generator.generate(5, 5);
        assertEquals(5, Maze.height());
        assertEquals(5, Maze.width());
    }

    @Test
    void testStartCoordinateIsPassage() {
        Maze maze = generator.generate(5, 5);
        assertTrue(maze.isPassage(new Coordinate(1, 1)));
    }

    @Test
    void testMazeGeneratedWithSpecificHeightAndWidth() {
        int height = 5;
        int width = 5;
        Maze maze = generator.generate(height, width);
        assertEquals(height, Maze.height());
        assertEquals(width, Maze.width());
        assertEquals(Cell.Type.PASSAGE, maze.getCell(1, 1).type());
    }

    @Test
    void testMazeHasCorrectWallAndPassageCounts() {
        Maze maze = generator.generate(7, 7);
        int passageCount = 0;
        int wallCount = 0;

        for (int row = 0; row < Maze.height(); row++) {
            for (int col = 0; col < Maze.width(); col++) {
                if (maze.getCell(row, col).type() == Cell.Type.PASSAGE) {
                    passageCount++;
                } else {
                    wallCount++;
                }
            }
        }

        assertTrue(passageCount < wallCount);
    }
}
