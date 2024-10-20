package backend.academy.project2.render;

import backend.academy.project2.maze.Cell;
import backend.academy.project2.maze.Coordinate;
import backend.academy.project2.maze.Maze;
import java.util.List;

public class MazeRenderer implements Renderer {
    @Override
    public String render(Maze maze) {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < maze.height(); row++) {
            for (int col = 0; col < maze.width(); col++) {
                sb.append(getSymbol(maze.getCell(row, col).type()));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < maze.height(); row++) {
            for (int col = 0; col < maze.width(); col++) {
                Cell cell = maze.getCell(row, col);
                if (path.contains(new Coordinate(row, col))) {
                    sb.append("🟩");
                } else {
                    sb.append(getSymbol(cell.type()));
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private String getSymbol(Cell.Type type) {
        return type.symbol();
    }
}
