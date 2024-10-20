package backend.academy.project2.generate;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum GeneratorType {
    PRIM("Алгоритм Прима для построения идеального лабиринта," +
         " где существует единственный путь из каждой в каждую ячейку лабиринта"),
    RECURSIVE_BACKTRACK("Алгоритм Recursive Backtrack для построения лабиринта с типами поверхностей, " +
                        "где может быть несколько путей из каждой в каждую ячейку лабиринта");

    private final int id;
    private final String description;

    GeneratorType(String description) {
        this.id = ordinal() + 1;
        this.description = description;
    }

    public static String getOrderView() {
        return Arrays.stream(values())
            .map(c -> String.format("%d) %s", c.id, c.description))
            .collect(Collectors.joining(System.lineSeparator()));
    }

    public static GeneratorType getById(int id) {
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
