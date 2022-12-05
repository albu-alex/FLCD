public class Main {
    public static void main(String[] args) {
        Grammar g = new Grammar("utils/G1.txt");
        System.out.println(g.getNonterminals());
        System.out.println(g.getTerminals());
        System.out.println(g.getStartingSymbol());
        System.out.println(g.getTransitions());
    }
}