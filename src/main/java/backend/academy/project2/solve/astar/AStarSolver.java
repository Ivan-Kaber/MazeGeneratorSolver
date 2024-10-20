package backend.academy.project2.solve.astar;

import backend.academy.project2.maze.Coordinate;
import backend.academy.project2.maze.Maze;
import backend.academy.project2.solve.AbstractSolver;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class AStarSolver extends AbstractSolver {

    public AStarSolver(Maze maze, Coordinate start, Coordinate goal) {
        super(maze, start, goal);
    }

    @Override
    public List<Coordinate> solve() {
        PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<>(Comparator.comparingDouble(Node::f));
        Map<Coordinate, Node> allNodes = new HashMap<>();

        Node startNode = new Node(start, null, 0, heuristic(start, goal));
        nodePriorityQueue.add(startNode);
        allNodes.put(start, startNode);

        while (!nodePriorityQueue.isEmpty()) {
            Node currentNode = nodePriorityQueue.poll();
            if (currentNode.coordinate().equals(goal)) {
                maze.addStartAndGoalPoint(start, goal);
                return constructPath(currentNode);
            }

            for (Coordinate neighbor : getValidNeighbors(currentNode.coordinate())) {
                if (maze.isWall(neighbor)) {
                    continue;
                }

                double tentativeG = currentNode.g() + maze.getCell(neighbor.row(), neighbor.col()).type().cost();
                Node neighborNode = allNodes.getOrDefault(neighbor, new Node(neighbor));
                if (tentativeG < neighborNode.g()) {
                    neighborNode.setG(tentativeG);
                    neighborNode.setH(heuristic(neighbor, goal));
                    neighborNode.setParent(currentNode);

                    if (!nodePriorityQueue.contains(neighborNode)) {
                        nodePriorityQueue.add(neighborNode);
                    }
                    allNodes.put(neighbor, neighborNode);
                }
            }
        }

        return Collections.emptyList();
    }

    private double heuristic(Coordinate a, Coordinate b) {
        return Math.abs(a.row() - b.row()) + Math.abs(a.col() - b.col());
    }
}
