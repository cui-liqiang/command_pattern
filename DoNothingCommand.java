package pattern;

public class DoNothingCommand implements Command{
    public CommandReport execute(Agent agent) {
        return new CommandReport(true);
    }
}
