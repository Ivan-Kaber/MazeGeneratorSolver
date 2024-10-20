package backend.academy.project2.interaction;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ConsoleInterfaceTest {
    private Scanner scanner;
    private ConsoleInterface consoleInterface;

    @BeforeEach
    void setUp() {
        PrintStream out = new PrintStream(new ByteArrayOutputStream());
        scanner = mock(Scanner.class);
        consoleInterface = new ConsoleInterface(out, scanner);
    }

    @Test
    void testInvalidAlgorithmSelection() {
        when(scanner.nextLine())
            .thenReturn("99") // Неверный выбор алгоритма
            .thenReturn("1")  // Правильный выбор
            .thenReturn("10") // Высота
            .thenReturn("10") // Ширина
            .thenReturn("1")  // Начальная точка
            .thenReturn("2")  // Конечная точка
            .thenReturn("1"); // Алгоритм поиска пути

        consoleInterface.processUserInteraction();

        verify(scanner, atLeast(1)).nextLine();
    }

    @Test
    void testValidInputSelection() {
        when(scanner.nextLine())
            .thenReturn("1")  // Алгоритм генерации
            .thenReturn("10") // Высота
            .thenReturn("10") // Ширина
            .thenReturn("1")  // Начальная точка
            .thenReturn("2")  // Конечная точка
            .thenReturn("1"); // Алгоритм поиска пути

        consoleInterface.processUserInteraction();

        verify(scanner, times(6)).nextLine();
    }
}
