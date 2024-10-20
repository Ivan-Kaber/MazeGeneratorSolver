package backend.academy.project2.solve.bfs;

import backend.academy.project2.maze.Coordinate;
import backend.academy.project2.maze.Maze;
import backend.academy.project2.solve.AbstractSolver;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFSSolver extends AbstractSolver {

    public BFSSolver(Maze maze, Coordinate start, Coordinate goal) {
        super(maze, start, goal);
    }

    @Override
    public List<Coordinate> solve() {
        Queue<Node> queue = new LinkedList<>();
        Map<Coordinate, Node> visited = new HashMap<>();

        Node startNode = new Node(start);
        queue.add(startNode);
        visited.put(start, startNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode.coordinate().equals(goal)) {
                maze.addStartAndGoalPoint(start, goal);
                return constructPath(currentNode);
            }

            for(Coordinate neighbor: getValidNeighbors(currentNode.coordinate())) {
                if(!maze.isWall(neighbor) && !visited.containsKey(neighbor)) {
                    Node neighborNode = new Node(neighbor, currentNode);
                    queue.add(neighborNode);
                    visited.put(neighbor, neighborNode);
                }
            }
        }

        return Collections.emptyList();
    }
}

