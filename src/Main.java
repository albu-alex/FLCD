import java.util.List;

public class Main {

    public static void main(final String[] args) {
        final Grammar grammar = Grammar.readFromFile("src/utils/g1.txt");

//        System.out.println(grammar.getNonterminals());
//        System.out.println(grammar.getTerminals());
//        grammar.getProductions().forEach(System.out::println);
//        System.out.println(grammar.getProductionsForNonterminal("Program"));

        final ParserOutput parserOutput = Parser.parse(grammar, List.of("a", "a", "c", "b", "c"));

        System.out.println();
        parserOutput.printToScreen();
        parserOutput.printToFile("output.txt");
    }

}