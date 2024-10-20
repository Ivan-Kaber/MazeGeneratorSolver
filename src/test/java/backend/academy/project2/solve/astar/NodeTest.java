package backend.academy.project2.solve.astar;

import backend.academy.project2.maze.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class NodeTest {

    private Node node;

    @BeforeEach
    void setUp() {
        Coordinate coordinate = new Coordinate(1, 1);
        node = new Node(coordinate);
    }

    @Test
    void testNodeInitialization() {
        assertNotNull(node);
        assertEquals(Double.POSITIVE_INFINITY, node.g());
        assertEquals(Double.POSITIVE_INFINITY, node.h());
        assertEquals(Double.POSITIVE_INFINITY, node.f());
        assertEquals(new Coordinate(1, 1), node.coordinate());
        assertNull(node.parent());
    }

    @Test
    void testSetG() {
        node.setG(5.0);
        assertEquals(5.0, node.g());
        assertEquals(5.0 + node.h(), node.f(), 0.0001); // f = g + h
    }

    @Test
    void testSetH() {
        node.setH(3.0);
        assertEquals(3.0, node.h());
        assertEquals(node.g() + 3.0, node.f(), 0.0001); // f = g + h
    }

    @Test
    void testSetGAndHTogether() {
        node.setG(5.0);
        node.setH(3.0);
        assertEquals(5.0, node.g());
        assertEquals(3.0, node.h());
        assertEquals(8.0, node.f(), 0.0001); // f = g + h
    }

    @Test
    void testSetHBeforeSetG() {
        node.setH(2.0);
        node.setG(4.0);
        assertEquals(4.0, node.g());
        assertEquals(2.0, node.h());
        assertEquals(6.0, node.f(), 0.0001); // f = g + h
    }

    @Test
    void testParentSetter() {
        Coordinate parentCoordinate = new Coordinate(0, 1);
        Node parentNode = new Node(parentCoordinate);
        node.parent(parentNode);

        assertEquals(parentNode, node.parent());
        assertEquals(parentCoordinate, node.parent().coordinate());
    }

    @Test
    void testParentSetterNull() {
        node.parent(null);
        assertNull(node.parent());
    }

    @Test
    void testNotEqualDifferentCoordinates() {
        Node differentNode = new Node(new Coordinate(1, 2));
        assertNotEquals(node, differentNode);
    }
}
