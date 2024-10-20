package backend.academy.project2.solve.astar;

import backend.academy.project2.maze.Cell;
import backend.academy.project2.maze.Coordinate;
import backend.academy.project2.maze.Maze;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AStarSolverTest {

    private Maze maze;

    @BeforeEach
    void setUp() {
        maze = new Maze(5, 5);
        for (int row = 0; row < Maze.height(); row++) {
            for (int col = 0; col < Maze.width(); col++) {
                maze.setCellType(new Coordinate(row, col), Cell.Type.WALL);
            }
        }
    }

    @Test
    void testSolveWithPath() {
        maze.setCellType(new Coordinate(1, 1), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(1, 2), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(1, 3), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(2, 3), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(3, 3), Cell.Type.PASSAGE);

        AStarSolver solver = new AStarSolver(maze, new Coordinate(1, 1), new Coordinate(3, 3));
        List<Coordinate> path = solver.solve();

        List<Coordinate> expectedPath = Arrays.asList(
            new Coordinate(1, 1),
            new Coordinate(1, 2),
            new Coordinate(1, 3),
            new Coordinate(2, 3),
            new Coordinate(3, 3)
        );

        assertEquals(expectedPath, path);
    }

    @Test
    void testSolveWithNoPath() {
        maze.setCellType(new Coordinate(1, 1), Cell.Type.WALL);
        maze.setCellType(new Coordinate(3, 3), Cell.Type.WALL);

        AStarSolver solver = new AStarSolver(maze, new Coordinate(1, 1), new Coordinate(3, 3));
        List<Coordinate> path = solver.solve();

        assertTrue(path.isEmpty());
    }

    @Test
    void testSolveWithMultiplePaths() {
        maze.setCellType(new Coordinate(1, 1), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(1, 2), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(2, 2), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(3, 2), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(3, 3), Cell.Type.PASSAGE);

        AStarSolver solver = new AStarSolver(maze, new Coordinate(1, 1), new Coordinate(3, 3));
        List<Coordinate> path = solver.solve();

        assertFalse(path.isEmpty());
    }

    @Test
    void testSolveWithStartAndGoalSame() {
        AStarSolver solver = new AStarSolver(maze, new Coordinate(1, 1), new Coordinate(1, 1));
        List<Coordinate> path = solver.solve();

        assertEquals(Collections.singletonList(new Coordinate(1, 1)), path);
    }

    @Test
    void testSolveWithOutOfBounds() {
        AStarSolver solver = new AStarSolver(maze, new Coordinate(-1, -1), new Coordinate(5, 5));
        List<Coordinate> path = solver.solve();

        assertTrue(path.isEmpty());
    }

    @Test
    void testSolveWithWallsOnly() {
        AStarSolver solver = new AStarSolver(maze, new Coordinate(1, 1), new Coordinate(3, 3));
        List<Coordinate> path = solver.solve();

        assertTrue(path.isEmpty());
    }
}
