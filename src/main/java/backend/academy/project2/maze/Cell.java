package backend.academy.project2.maze;

import lombok.Getter;

public record Cell(int row, int col, Type type) {

    @Getter
    public enum Type {
        WALL(Integer.MAX_VALUE, "⬜️"),
        BARRIER(4, "\uD83D\uDC94"),
        PASSAGE(2, "⬛️"),
        BOOST(0, "\uD83D\uDC8E");

        private final int cost;
        private final String symbol;

        Type(int cost, String symbol) {
            this.cost = cost;
            this.symbol = symbol;
        }

    }
}
