package backend.academy.project2.solve;

import backend.academy.project2.maze.Coordinate;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class AbstractNode<T extends AbstractNode<T>> {
    protected Coordinate coordinate;
    protected T parent;

    public AbstractNode(Coordinate coordinate, T parent) {
        this.coordinate = coordinate;
        this.parent = parent;
    }

    public void setParent(T parent) {
        this.parent = parent;
    }
}
