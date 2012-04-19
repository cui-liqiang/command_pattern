import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShellCommand implements Command{
    private String shellCommand;

    public ShellCommand(String shellCommand) {
        this.shellCommand = shellCommand;
    }

    public CommandReport execute(Agent agent) throws IOException, InterruptedException {
        CommandReport commandReport;
        try{
            Process exec = Runtime.getRuntime().exec(shellCommand);
            int returnValue = exec.waitFor();
            commandReport = new CommandReport(returnValue == 0);
            setExecutionOutput(commandReport, exec);
        } catch (Exception e) {
            commandReport = new CommandReport(false);
            commandReport.setOutput(e.getMessage());
        }
        return commandReport;
    }

    private void setExecutionOutput(CommandReport commandReport, Process exec) throws IOException {
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(exec.getInputStream()));
        String whole = "";
        String line;
        while((line = stdInput.readLine()) != null) {
            whole += line + "\n";
        }
        commandReport.setOutput(whole);
    }
}
