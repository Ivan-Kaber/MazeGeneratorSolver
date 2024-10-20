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
        assertEquals(3, maze.height());
        assertEquals(3, maze.width());
        assertTrue(maze.isPassage(new Coordinate(1, 1)));
    }

    @Test
    void testGenerateMazeWithEvenDimensions() {
        Maze maze = generator.generate(4, 4);
        assertEquals(4, maze.height());
    }

    @Test
    void testGenerateMazeWithOddDimensions() {
        Maze maze = generator.generate(5, 5);
        assertEquals(5, maze.height());
        assertEquals(5, maze.width());
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
        assertEquals(height, maze.height());
        assertEquals(width, maze.width());
        assertEquals(Cell.Type.PASSAGE, maze.getCell(1, 1).type());
    }

    @Test
    void testMazeHasCorrectWallAndPassageCounts() {
        Maze maze = generator.generate(7, 7);
        int passageCount = 0;
        int wallCount = 0;

        for (int row = 0; row < maze.height(); row++) {
            for (int col = 0; col < maze.width(); col++) {
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
