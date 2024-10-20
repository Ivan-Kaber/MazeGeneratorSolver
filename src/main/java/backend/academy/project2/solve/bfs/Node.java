package backend.academy.project2.solve.bfs;

import backend.academy.project2.maze.Coordinate;
import backend.academy.project2.solve.AbstractNode;

public class Node extends AbstractNode<Node> {
    public Node(Coordinate coordinate) {
        super(coordinate, null);
    }

    public Node(Coordinate coordinate, Node parent) {
        super(coordinate, parent);
    }
}
