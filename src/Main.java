public class Main {
    public static void main(String[] args) {
        FiniteAutomata finiteAutomata = new FiniteAutomata("/Users/alalbu/IdeaProjects/lab_4_LFTC/src/finiteAutomata.txt");
        System.out.println(finiteAutomata.getStates());
        System.out.println(finiteAutomata.getAlphabet());
        System.out.println(finiteAutomata.getTransitions());
        System.out.println(finiteAutomata.getFinalStates());
        System.out.println(finiteAutomata.acceptsSequence("b"));
    }
}