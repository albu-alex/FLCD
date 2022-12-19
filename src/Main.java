import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Grammar grammar = new Grammar("src/utils/g2.txt");

        System.out.println();
        if (grammar.checkContextFreeGrammar()) {
            System.out.println("Context Free Grammar!");
        } else {
            System.out.println("Not a Context Free Grammar!");
        }
        System.out.println();
        System.out.println(grammar.printNonTerminals());
        System.out.println(grammar.printTerminals());
        System.out.println(grammar.printProductions());
    }
}