package backend.academy.project2.generate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GeneratorTypeTest {

    @Test
    void testGetById_validId() {
        assertEquals(GeneratorType.PRIM, GeneratorType.getById(1), "При id = 1 должен возвращаться PRIM.");
        assertEquals(GeneratorType.RECURSIVE_BACKTRACK, GeneratorType.getById(2),
            "При id = 2 должен возвращаться RECURSIVE_BACKTRACK.");
    }

    @Test
    void testGetById_invalidId() {
        assertNull(GeneratorType.getById(3), "При несуществующем id должен возвращаться null.");
        assertNull(GeneratorType.getById(-1), "При отрицательном id должен возвращаться null.");
    }

    @Test
    void testContains_validId() {
        assertTrue(GeneratorType.contains("1"), "Метод должен возвращать true для существующего id = 1.");
        assertTrue(GeneratorType.contains("2"), "Метод должен возвращать true для существующего id = 2.");
    }

    @Test
    void testContains_invalidId() {
        assertFalse(GeneratorType.contains("3"), "Метод должен возвращать false для несуществующего id = 3.");
        assertFalse(GeneratorType.contains("-1"), "Метод должен возвращать false для отрицательного id.");
        assertFalse(GeneratorType.contains("abc"), "Метод должен возвращать false для нечислового id.");
    }
}
