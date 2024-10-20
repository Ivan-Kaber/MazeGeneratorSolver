package backend.academy.project2;

import backend.academy.project2.interaction.ConsoleInterface;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.PrintStream;
import java.util.Scanner;

@SuppressFBWarnings("DM_DEFAULT_ENCODING")
public class Main {
    public static void main(String[] args) {

        PrintStream out = new PrintStream(System.out);
        Scanner scanner = new Scanner(System.in);
        ConsoleInterface consoleInterface = new ConsoleInterface(out, scanner);
        consoleInterface.processUserInteraction();
    }
}
