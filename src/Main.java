import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Main {
    private static void printToFile(String filePath, Object object) {
        try(PrintStream printStream = new PrintStream(filePath)) {
            printStream.println(object);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void run(String filePath) {
        ProgramScanner scanner = new ProgramScanner(filePath);
        scanner.scan();
        printToFile(filePath.replace(".txt", "ST.txt"), scanner.getSymbolTable());
        printToFile(filePath.replace(".txt", "PIF.txt"), scanner.getPif());
    }

    public static void main(String[] args) {
        FiniteAutomata FA1 = new FiniteAutomata("src/finiteAutomata.txt");
        System.out.println(FA1.getStates());
        System.out.println(FA1.getAlphabet());
        System.out.println(FA1.getTransitions());
        System.out.println(FA1.getFinalStates());
        System.out.println(FA1.acceptsSequence("aaaaadcb"));
    }
}