package backend.academy.project2.generate;

import backend.academy.project2.maze.Maze;

public interface Generator {
    Maze generate(int height, int width);
}
