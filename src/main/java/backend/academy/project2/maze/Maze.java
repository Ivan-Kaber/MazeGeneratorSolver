package backend.academy.project2.maze;

import lombok.Getter;

public class Maze {
    public static final int MIN_SIZE = 5;
    public static final int MAX_SIZE = 47;
    @Getter
    private static int height;
    @Getter
    private static int width;
    private final Cell[][] grid;

    public Maze(int height, int width) {
        Maze.height = height;
        Maze.width = width;
        this.grid = new Cell[height][width];

        setWallsInMaze();
    }


    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public void setCellType(Coordinate coordinate, Cell.Type type) {
        grid[coordinate.row()][coordinate.col()] = new Cell(coordinate.row(), coordinate.col(), type);
    }

    public boolean isWithinBounds(Coordinate coordinate) {
        return coordinate.row() > 0 && coordinate.row() < this.height - 1 &&
               coordinate.col() > 0 && coordinate.col() < this.width - 1;
    }

    public boolean isWall(Coordinate coordinate) {
        return (getCell(coordinate.row(), coordinate.col()).type() == Cell.Type.WALL);
    }

    public boolean isPassage(Coordinate coordinate) {
        return (getCell(coordinate.row(), coordinate.col()).type() == Cell.Type.PASSAGE);
    }

    public void addStartAndGoalPoint(Coordinate start, Coordinate goal) {
        setCellType(start, Cell.Type.START);
        setCellType(goal, Cell.Type.GOAL);
    }

    private void setWallsInMaze() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(row, col, Cell.Type.WALL);
            }
        }
    }
}
