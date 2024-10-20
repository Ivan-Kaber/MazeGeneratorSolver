package backend.academy.project2.interaction;

import backend.academy.project2.maze.Coordinate;
import backend.academy.project2.maze.Maze;
import java.util.Arrays;
import java.util.stream.Collectors;

public enum Place {
    UP_LEFT(new Coordinate(1, 1), "Слева сверху"),
    UP_RIGHT(new Coordinate(1, Maze.width() - 2), "Справа сверху"),
    DOWN_LEFT(new Coordinate(Maze.height() - 2, 1), "Слева снизу"),
    DOWN_RIGHT(new Coordinate(Maze.height() - 2, Maze.width() - 2), "Справа снизу");

    private final int id;
    private final Coordinate coordinate;
    private final String description;

    Place(Coordinate coordinate, String description) {
        this.id = ordinal() + 1;
        this.coordinate = coordinate;
        this.description = description;
    }

    public static String getOrderView() {
        return Arrays.stream(values())
            .map(c -> String.format("%d) %s", c.id, c.description))
            .collect(Collectors.joining(System.lineSeparator()));
    }

    public static Coordinate getById(int id) {
        return Arrays.stream(values())
            .filter(c -> c.id == id)
            .map(c -> c.coordinate)
            .findFirst()
            .orElse(null);
    }

    public static boolean contains(String id) {
        return Arrays.stream(values())
            .anyMatch(c -> String.valueOf(c.id).equals(id));
    }
}
