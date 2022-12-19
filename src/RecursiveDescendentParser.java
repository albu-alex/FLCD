import java.util.List;
import java.util.Stack;


enum State {
    NORMAL,
    BACK,
    FINAL,
    ERROR
}
public class RecursiveDescendentParser {
    private Grammar grammar ;
    private List sequence;
    private String output_file;
    private Stack<String> index;
    private State state = State.NORMAL;
}
