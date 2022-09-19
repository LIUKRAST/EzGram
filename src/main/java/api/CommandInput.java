package api;

public class CommandInput {
    private String input;
    public CommandInput(String input) {
        this.input = input;
    }

    public String[] parseArguments() {
        return input.split(" ");
    }

    public String getArgument(int index) {
        String[] args = input.split(" ");
        if(args.length >= index + 1) {
            return args[index];
        } else {
            return "";
        }
    }

    public boolean canGetArgument(int index) {
        String[] args = input.split(" ");
        return args.length >= index + 1;
    }
}
