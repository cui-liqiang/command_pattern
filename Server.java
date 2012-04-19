package pattern;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Server {
    private LinkedList<Command> commands = new LinkedList<Command>();
    private Map<Command, CommandReport> commandsStatus = new HashMap<Command, CommandReport>();

    public Command getCommand() {
        return commands.getLast();
    }

    public void addCommand(Command command) {
        this.commands.addLast(command);
    }

    public CommandReport reportStatus(Command command) {
        return commandsStatus.get(command);
    }

    public void setStatus(Command command, CommandReport status) {
        commandsStatus.put(command, status);
    }
}
