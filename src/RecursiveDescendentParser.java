import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


enum State {
    NORMAL,
    BACK,
    FINAL,
    ERROR
}
public class RecursiveDescendentParser {
    private Grammar grammar;
    private List sequence;
    private String outputFile;
    private Stack<String> inputStack;
    private Stack<Pair<String,Integer>> workingStack;
    private State state ;
    private Integer index;
    private List<String> tree; // idk
    FileWriter myWriter ;

    //TODO ParserOutput


    public RecursiveDescendentParser(String grammarFile,String sequenceFile,String outputFile) throws IOException {
        grammar = new Grammar(grammarFile);
        sequence = readSequence(sequenceFile); ///Not sure what sequence is
        this.outputFile = outputFile;
        initWritingFile(outputFile);
        inputStack.push(grammar.getStartSymbol());
        state = State.NORMAL;
        index = 0;
        tree = new ArrayList<>();
    }

    private void expand() throws IOException {
          // when the head of the input stack is a non terminal
       // (q, i, alpha, A beta) ⊢ (q, i, alpha A1, gamma1 beta)

        writeOutputFile("Expand",false);
        String nonTerminal = inputStack.pop();
        workingStack.push(new Pair<>(nonTerminal,0)); ///TODO see why Paul pushes a tuple here
        String newProduction = ""; //TODO getProductionsForNonTerminal(nonTerminal);
        //TODO inputStack.push() /// self.input_stack = new_production + self.input_stack
    }
    private void advance() throws IOException {
         // when the head of the input stack is a terminal == current symbol from input
        // (q, i, alpha, a_i beta) ⊢ (q, i+1, alpha a_i, beta)

        writeOutputFile("Advance",false);
        workingStack.push(new Pair(inputStack.pop(), index));
        index += 1;
    }

    private void momentaryInsuccess() throws IOException {
        // when the head of the input stack is a terminal != current symbol from input

        writeOutputFile("Momentary Insuccess",false);
        state = State.BACK;
    }
    private void back() throws IOException {
        // when the head of the working stack is a terminal
        //(b, i, alpha a, beta) ⊢ (b, i-1, alpha, a beta)

        writeOutputFile("Back",false);
        Pair<String,Integer> newElement = workingStack.pop();
        inputStack.push(newElement.getFirst()); //TODO verify self.input_stack = [new_el] + self.input_stack
        index -= 1;
    }

    private void anotherTry() throws IOException {
        writeOutputFile("Another Try",false);
        Pair<String,Integer> last = workingStack.pop(); // (last, productionNr)

        if(last.getSecond() + 1 < grammar.getProductionsForNonTerminal(last.getFirst()).size()){
            state = State.NORMAL;

            //put working next production for the symbol
            Pair<String, Integer> newPair = new Pair<>(last.getFirst(),last.getSecond()+1);
            workingStack.push(newPair);

            //change production top input; /// TODO THIS should be a list<list<string>> or Set<list<string>> ? i guess
            Integer lenght = grammar.getProductionsForNonTerminal(last.getFirst()).size();//.get(last.getSecond());

        }

    }
    private void initWritingFile(String fileName){
        try {
            this.myWriter = new FileWriter(fileName,true);
            myWriter.write("");
            //myWriter.close();
            System.out.println("Successfully initialize the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private List<String> readSequence(String fileName) throws IOException {
        List<String> sequence = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        String line = reader.readLine();
        while (line != null){
            sequence.add(line);
        }
        return sequence;
    }

    private void writeOutputFile(String message, Boolean isFinal) throws IOException {
        if(isFinal){
            myWriter.append("\nResult: \n");
        }
        myWriter.append(message);

    }

    private void writeAllData() throws IOException {
        myWriter.append(state.toString()).append(" ");
        myWriter.append(index.toString());
        myWriter.append(workingStack.toString());
        myWriter.append(inputStack.toString());
    }

    private void printWorkingStack() throws IOException {
        myWriter.append(workingStack.toString());
    }
    private void success() throws IOException {
        writeOutputFile("Success",false);
        state = State.FINAL;
    }

}
