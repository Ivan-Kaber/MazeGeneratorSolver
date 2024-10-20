package backend.academy.project2.generate;

public class GeneratorFactory {
    public static Generator createGenerator(GeneratorType type) {
        return switch (type) {
            case PRIM -> new PrimGenerator();
            case RECURSIVE_BACKTRACK -> new RecursiveBacktrackGenerator();
        };
    }

    private GeneratorFactory() {}
}
