package backend.academy.project2.generate;

import backend.academy.project2.maze.Cell;
import backend.academy.project2.maze.Coordinate;
import backend.academy.project2.maze.Direction;
import backend.academy.project2.maze.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimGenerator implements Generator {
    private final Random random = new Random();
    private final List<Coordinate> walls = new ArrayList<>();

    @Override
    public Maze generate(int height, int width) {
        Maze maze = new Maze(height, width);
        Coordinate startCoordinate = new Coordinate(random.nextInt(height - 2) + 1,
            random.nextInt(width - 2) + 1);
        maze.setCellType(startCoordinate, Cell.Type.PASSAGE);

        addWalls(maze, walls, startCoordinate);
        while (!walls.isEmpty()) {
            Coordinate wall = walls.remove(random.nextInt(walls.size()));

            Coordinate adjacentCell = getAdjacentPassage(maze, wall);
            if (adjacentCell != null) {
                maze.setCellType(wall, Cell.Type.PASSAGE);
                maze.setCellType(adjacentCell, Cell.Type.PASSAGE);
                addWalls(maze, walls, wall);
            }
        }
        cleanCorners(maze);
        return maze;
    }

    private void addWalls(Maze maze, List<Coordinate> walls, Coordinate wall) {
        for (Direction direction : Direction.values()) {
            Coordinate newCoordinate = new Coordinate(
                wall.row() + direction.rowOffset(),
                wall.col() + direction.colOffset());

            if (maze.isWithinBounds(newCoordinate) && maze.isWall(newCoordinate) && !walls.contains(newCoordinate)) {
                walls.add(newCoordinate);
            }
        }
    }


    private Coordinate getAdjacentPassage(Maze maze, Coordinate wall) {
        int passageCount = 0;
        Coordinate passage = null;

        for (Direction direction : Direction.values()) {
            Coordinate newCoordinate = new Coordinate(
                wall.row() + direction.rowOffset(),
                wall.col() + direction.colOffset());

            if (maze.isWithinBounds(newCoordinate) && maze.isPassage(newCoordinate)) {
                passageCount++;
                passage = newCoordinate;
            }
        }
        return (passageCount == 1) ? passage : null;
    }

    private void cleanCorners(Maze maze) {
        maze.setCellType(new Coordinate(1, 1), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(1, maze.width() - 2), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(maze.height() - 2, 1), Cell.Type.PASSAGE);
        maze.setCellType(new Coordinate(maze.height() - 2, maze.width() - 2), Cell.Type.PASSAGE);
    }
}
