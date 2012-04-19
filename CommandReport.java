package pattern;

public class CommandReport {
    private boolean success;
    private String output;

    public CommandReport(boolean success) {
        this.success = success;
    }

    public boolean success() {
        return success;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getOutput() {
        return output;
    }
}
