import java.io.IOException;

public interface Command {
    public CommandReport execute(Agent agent) throws IOException, InterruptedException;
}
