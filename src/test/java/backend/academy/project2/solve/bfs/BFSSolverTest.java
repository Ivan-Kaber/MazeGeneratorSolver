package backend.academy.project2.solve.bfs;

import backend.academy.project2.maze.Cell;
import backend.academy.project2.maze.Coordinate;
import backend.academy.project2.maze.Maze;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BFSSolverTest {

    private Maze maze;

    @BeforeEach
    void setUp() {
        maze = new Maze(5, 5);
        maze.setCellType(new Coordinate(1, 1), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(1, 2), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(1, 3), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(2, 3), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(3, 3), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(3, 4), Cell.Type.PASSAGE);
    }

    @Test
    void testSolveNoPathExists() {
        maze.setCellType(new Coordinate(2, 2), Cell.Type.WALL);

        BFSSolver solver = new BFSSolver(maze, new Coordinate(1, 1), new Coordinate(3, 2));
        List<Coordinate> path = solver.solve();

        assertNotNull(path);
        assertTrue(path.isEmpty());
    }

    @Test
    void testSolveStartEqualsGoal() {
        BFSSolver solver = new BFSSolver(maze, new Coordinate(1, 1), new Coordinate(1, 1));
        List<Coordinate> path = solver.solve();

        assertNotNull(path);
        assertEquals(1, path.size());
        assertEquals(new Coordinate(1, 1), path.get(0));
    }

    @Test
    void testSolveBlockedByWalls() {
        maze.setCellType(new Coordinate(2, 1), Cell.Type.WALL);
        maze.setCellType(new Coordinate(2, 2), Cell.Type.WALL);
        maze.setCellType(new Coordinate(3, 2), Cell.Type.WALL);

        BFSSolver solver = new BFSSolver(maze, new Coordinate(1, 1), new Coordinate(3, 4));
        List<Coordinate> path = solver.solve();

        assertNotNull(path);
        assertTrue(path.isEmpty());
    }
}
