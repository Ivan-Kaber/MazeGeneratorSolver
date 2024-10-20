package backend.academy.project2.render;

import backend.academy.project2.maze.Cell;
import backend.academy.project2.maze.Coordinate;
import backend.academy.project2.maze.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MazeRendererTest {
    private MazeRenderer renderer;
    private Maze maze;

    @BeforeEach
    void setUp() {
        renderer = new MazeRenderer();
        maze = new Maze(5, 5); // Создаем лабиринт размером 5x5 для тестов
        for (int row = 0; row < maze.height(); row++) {
            for (int col = 0; col < maze.width(); col++) {
                maze.setCellType(new Coordinate(row, col), Cell.Type.WALL); // Инициализируем стены
            }
        }
    }

    @Test
    void testRenderMazeWithWalls() {
        String expected = "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n";
        assertEquals(expected, renderer.render(maze));
    }

    @Test
    void testRenderMazeWithPassages() {
        // Устанавливаем несколько проходов
        maze.setCellType(new Coordinate(1, 1), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(2, 2), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(3, 3), Cell.Type.PASSAGE);

        String expected = "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬛\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬜\uFE0F⬛\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬛\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n";
        assertEquals(expected, renderer.render(maze));
    }

    @Test
    void testRenderMazeWithPath() {
        // Устанавливаем несколько проходов
        maze.setCellType(new Coordinate(1, 1), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(1, 2), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(1, 3), Cell.Type.PASSAGE);

        // Указываем путь
        List<Coordinate> path = Arrays.asList(
            new Coordinate(1, 1),
            new Coordinate(1, 2),
            new Coordinate(1, 3)
        );

        String expected = "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9⬜\uFE0F\n" +
                          "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n";
        assertEquals(expected, renderer.render(maze, path));
    }

    @Test
    void testRenderMazeWithPathAndWalls() {
        // Устанавливаем проходы и путь
        maze.setCellType(new Coordinate(1, 1), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(1, 2), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(2, 1), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(2, 2), Cell.Type.PASSAGE);

        // Указываем путь
        List<Coordinate> path = Arrays.asList(
            new Coordinate(1, 1),
            new Coordinate(2, 1),
            new Coordinate(2, 2)
        );

        String expected = "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F\uD83D\uDFE9⬛\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F\uD83D\uDFE9\uD83D\uDFE9⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n";
        assertEquals(expected, renderer.render(maze, path));
    }

    @Test
    void testRenderEmptyPath() {
        // Указываем пустой путь
        List<Coordinate> path = Collections.emptyList();
        String expected = "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n";
        assertEquals(expected, renderer.render(maze, path));
    }

    @Test
    void testRenderMazeWithNoPath() {
        maze.setCellType(new Coordinate(1, 1), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(1, 2), Cell.Type.PASSAGE);

        String expected = "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬛\uFE0F⬛\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n";
        assertEquals(expected, renderer.render(maze));
    }

    @Test
    void testRenderMazeWithMixedCells() {
        // Устанавливаем разные типы ячеек
        maze.setCellType(new Coordinate(1, 1), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(1, 2), Cell.Type.BARRIER);
        maze.setCellType(new Coordinate(2, 1), Cell.Type.BOOST);
        maze.setCellType(new Coordinate(3, 3), Cell.Type.PASSAGE);

        String expected = "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬛\uFE0F\uD83D\uDC94⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F\uD83D\uDC8E⬜\uFE0F⬜\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬛\uFE0F⬜\uFE0F\n" +
                          "⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F⬜\uFE0F\n";
        assertEquals(expected, renderer.render(maze));
    }
}
