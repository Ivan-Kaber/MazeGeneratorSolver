package backend.academy.project2.interaction;

import backend.academy.project2.maze.Coordinate;
import java.util.Arrays;
import java.util.stream.Collectors;

public enum Place {
    UP_LEFT(new Coordinate(1, 1), "Слева сверху"),
    UP_RIGHT(new Coordinate(1, 1), "Справа сверху"),
    DOWN_LEFT(new Coordinate(1, 1), "Слева снизу"),
    DOWN_RIGHT(new Coordinate(1, 1), "Справа снизу");

    private final int id;
    private final String description;
    private Coordinate coordinate;

    Place(Coordinate coordinate, String description) {
        this.id = ordinal() + 1;
        this.coordinate = coordinate;
        this.description = description;
    }

    public static void setMazeDimensions(int height, int width) {
        UP_LEFT.coordinate = new Coordinate(1, 1);
        UP_RIGHT.coordinate = new Coordinate(1, width - 2);
        DOWN_LEFT.coordinate = new Coordinate(height - 2, 1);
        DOWN_RIGHT.coordinate = new Coordinate(height - 2, width - 2);
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
