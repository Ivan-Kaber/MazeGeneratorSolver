package backend.academy.project2.solve;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum SolverType {
    ASTAR("Алгоритм А*, найдет кратчайший путь, учитывая типы поверхностей."),
    BFS("Алгоритм BFS, найдет путь в лабиринте.");

    private final int id;
    private final String description;

    SolverType(String description) {
        this.id = ordinal() + 1;
        this.description = description;
    }

    public static String getOrderView() {
        return Arrays.stream(values())
            .map(c -> String.format("%d) %s", c.id, c.description))
            .collect(Collectors.joining(System.lineSeparator()));
    }

    public static SolverType getById(int id) {
        return Arrays.stream(values())
            .filter(c -> c.id == id)
            .findFirst()
            .orElse(null);
    }

    public static boolean contains(String id) {
        return Arrays.stream(values())
            .anyMatch(c -> String.valueOf(c.id).equals(id));
    }
}
