package backend.academy.project2.solve.astar;

import backend.academy.project2.maze.Coordinate;
import backend.academy.project2.solve.AbstractNode;
import lombok.Getter;

@Getter
public class Node extends AbstractNode<Node> {
    private double g; // Стоимость пути от старта до текущего узла
    private double h; // Эвристическая стоимость от текущего узла до цели
    private double f; // Общая стоимость (g + h)

    public Node(Coordinate coordinate) {
        super(coordinate, null);
        this.g = Double.POSITIVE_INFINITY;
        this.h = Double.POSITIVE_INFINITY;
        this.f = g + h;
    }

    public Node(Coordinate coordinate, Node parent, double g, double h) {
        super(coordinate, parent);
        this.g = g;
        this.h = h;
        this.f = g + h;
    }

    public void setG(double g) {
        this.g = g;
        this.f = g + h;
    }

    public void setH(double h) {
        this.h = h;
        this.f = g + h;
    }
}
