package pattern;

import java.io.IOException;

public class Agent {
    private Server server;

    public Agent(Server server) {
        this.server = server;
    }

    public void execute() throws IOException, InterruptedException {
        Command command = server.getCommand();
        server.setStatus(command, command.execute(this));
    }
}
