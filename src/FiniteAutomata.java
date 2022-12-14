import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FiniteAutomata {
    private final String ELEMENT_SEPARATOR = " ";
    private final String TRANSITION_SEPARATOR = "|";

    private boolean isDeterministic;
    private String initialState;
    private List<String> states;
    private List<String> alphabet;
    private Map<String, Set<Pair<String, String>>> transitions;
    private List<String> finalStates;

    private boolean checkIfDeterministic() {
        for (String state : this.transitions.keySet()) {
            long uniqueSymbols = this.transitions.get(state).stream().map(Pair::getSecond).distinct().count();
            if (uniqueSymbols != this.transitions.get(state).size()) {
                return false;
            }
        }
        return true;
    }

    private void readFromFile(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            this.states = new ArrayList<>(List.of(scanner.nextLine().split(this.ELEMENT_SEPARATOR)));
            this.initialState = this.states.get(0);
            this.alphabet = new ArrayList<>(List.of(scanner.nextLine().split(this.ELEMENT_SEPARATOR)));

            this.transitions = new HashMap<>();
            String[] transitionStrings = scanner.nextLine().split(this.ELEMENT_SEPARATOR);
            for (String transition : transitionStrings) {
                String[] splitTransition = transition.split(this.TRANSITION_SEPARATOR);
                this.transitions.putIfAbsent(splitTransition[0], new HashSet<>());
                this.transitions.get(splitTransition[0]).add(new Pair<>(splitTransition[4], splitTransition[2]));
            }

            this.finalStates = new ArrayList<>(List.of(scanner.nextLine().split(this.ELEMENT_SEPARATOR)));
            this.isDeterministic = this.checkIfDeterministic();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public FiniteAutomata(String filePath) {
        this.readFromFile(filePath);
    }

    public List<String> getStates() {
        return this.states;
    }

    public List<String> getAlphabet() {
        return this.alphabet;
    }

    public Map<String, Set<Pair<String, String>>> getTransitions() {
        return this.transitions;
    }

    public List<String> getFinalStates() {
        return this.finalStates;
    }

    public boolean acceptsSequence(String sequence) {
        String currentState = this.initialState;
        for (int i = 0; i < sequence.length(); i++) {
            String currentSymbol = sequence.substring(i, i + 1);
            Set<Pair<String, String>> nextTransitions = this.transitions.get(currentState);
            if (nextTransitions == null) {
                return false;
            }

            boolean foundNext = false;
            for (Pair<String, String> stateSymbolPair: nextTransitions) {
                if (stateSymbolPair.getSecond().equals(currentSymbol)) {
                    currentState = stateSymbolPair.getFirst();
                    foundNext = true;
                    break;
                }
            }

            if (!foundNext) {
                return false;
            }
        }

        return finalStates.contains(currentState);
    }
}