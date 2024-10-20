package backend.academy.project2.interaction;

import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ConsoleLogicTest {

    private PrintStream out;
    private Scanner scanner;
    private ConsoleLogic consoleLogic;

    @BeforeEach
    void setUp() {
        out = mock(PrintStream.class);
        scanner = mock(Scanner.class);
        consoleLogic = new ConsoleLogic(out, scanner);
    }

    @Test
    void selectAlgorithm_InvalidInput_PromptsAgain() {
        when(scanner.nextLine()).thenReturn("invalid", "1");

        consoleLogic.selectAlgorithm("invalid");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(out, times(2)).print(captor.capture());
        assertTrue(captor.getAllValues().stream().anyMatch(s -> s.contains("Вы ввели неверные данные")));
    }

    @Test
    void selectSize_InvalidInput_PromptsAgain() {
        when(scanner.nextLine()).thenReturn("invalid", "10");

        consoleLogic.selectSize("invalid", true);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(out, atLeastOnce()).printf(captor.capture(), anyInt(), anyInt());
        assertTrue(captor.getAllValues().stream().anyMatch(s -> s.contains("Вы ввели неверные данные")));
    }

    @Test
    void selectStartOrGoalPoint_ValidInput_SetsCoordinates() {
        when(scanner.nextLine()).thenReturn("1");

        consoleLogic.selectStartOrGoalPoint("1", true);

        assertNotNull(consoleLogic.start());
    }

    @Test
    void selectStartOrGoalPoint_InvalidInput_PromptsAgain() {
        when(scanner.nextLine()).thenReturn("invalid", "1");

        consoleLogic.selectStartOrGoalPoint("invalid", true);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(out, times(2)).print(captor.capture());
        assertTrue(captor.getAllValues().stream().anyMatch(s -> s.contains("Вы ввели неверные данные")));
    }
}
