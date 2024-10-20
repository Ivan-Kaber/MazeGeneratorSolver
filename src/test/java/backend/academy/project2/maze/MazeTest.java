package backend.academy.project2.maze;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MazeTest {

    private Maze maze;

    @BeforeEach
    void setUp() {
        maze = new Maze(5, 5); // Создаем лабиринт размером 5x5 для тестов
    }

    @Test
    void testMazeDimensions() {
        assertEquals(5, Maze.height());
        assertEquals(5, Maze.width());
    }

    @Test
    void testSetCellType() {
        Coordinate coordinate = new Coordinate(2, 2);
        maze.setCellType(coordinate, Cell.Type.PASSAGE);
        assertEquals(Cell.Type.PASSAGE, maze.getCell(2, 2).type());
    }

    @Test
    void testGetCell() {
        Coordinate coordinate = new Coordinate(1, 1);
        maze.setCellType(coordinate, Cell.Type.PASSAGE);
        Cell cell = maze.getCell(1, 1);
        assertNotNull(cell);
        assertEquals(1, cell.row());
        assertEquals(1, cell.col());
        assertEquals(Cell.Type.PASSAGE, cell.type());
    }

    @Test
    void testIsWithinBounds() {
        Coordinate validCoordinate = new Coordinate(1, 1);
        Coordinate invalidCoordinate1 = new Coordinate(0, 0);
        Coordinate invalidCoordinate2 = new Coordinate(5, 5);

        assertTrue(maze.isWithinBounds(validCoordinate));
        assertFalse(maze.isWithinBounds(invalidCoordinate1));
        assertFalse(maze.isWithinBounds(invalidCoordinate2));
    }

    @Test
    void testIsWall() {
        Coordinate coordinate = new Coordinate(1, 1);
        assertTrue(maze.isWall(coordinate)); // Начальная ячейка - стена

        maze.setCellType(coordinate, Cell.Type.PASSAGE);
        assertFalse(maze.isWall(coordinate)); // После установки - проход
    }

    @Test
    void testIsPassage() {
        Coordinate coordinate = new Coordinate(1, 1);
        assertFalse(maze.isPassage(coordinate)); // Начальная ячейка - стена

        maze.setCellType(coordinate, Cell.Type.PASSAGE);
        assertTrue(maze.isPassage(coordinate)); // После установки - проход
    }

    @Test
    void testSetWallsInMaze() {
        // Проверяем, что все ячейки инициализировались как стены
        for (int row = 0; row < Maze.height(); row++) {
            for (int col = 0; col < Maze.width(); col++) {
                assertEquals(Cell.Type.WALL, maze.getCell(row, col).type());
            }
        }
    }

    @Test
    void testCellInitialization() {
        // Проверяем, что ячейки инициализируются с правильными координатами и типами
        for (int row = 0; row < Maze.height(); row++) {
            for (int col = 0; col < Maze.width(); col++) {
                Cell cell = maze.getCell(row, col);
                assertEquals(row, cell.row());
                assertEquals(col, cell.col());
                assertEquals(Cell.Type.WALL, cell.type());
            }
        }
    }

    @Test
    void testInvalidCoordinateAccess() {
        // Проверяем, что доступ к ячейкам за пределами границ вызывает исключение
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            maze.getCell(-1, -1);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            maze.getCell(5, 5);
        });
    }
}
