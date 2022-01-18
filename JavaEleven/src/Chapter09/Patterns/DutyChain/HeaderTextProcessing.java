package CHAPTER09.Patterns.DutyChain;

public class HeaderTextProcessing extends ProcessingObject<String>{
    @Override
    protected String handleWork(String input) {
        return "From Raoul, Mario and Alan: " + input;
    }
}
