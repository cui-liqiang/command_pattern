import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class AgentTest {
    Server server;
    Agent agent;
    String projectFolder = "project";

    @Before
    public void setUp() throws IOException, InterruptedException {
        server = new Server();
        agent = new Agent(server);
        Process exec = Runtime.getRuntime().exec("rm -rf " + projectFolder);
        exec.waitFor();
    }


    @Test
    public void should_not_affect_the_project_folder() throws IOException, InterruptedException {
        server.addCommand(new DoNothingCommand());
        File file = new File(projectFolder);
        long original = file.lastModified();

        agent.execute();

        assertEquals(original, file.lastModified());
    }

    @Test
    public void should_check_out_repository() throws IOException, InterruptedException {
        String repoURL = "https://github.com/khu/emptyrepo.git";
        server.addCommand(new ShellCommand("/usr/bin/git clone " + repoURL + " " + projectFolder));

        agent.execute();

        File file = new File(projectFolder);
        File[] files = file.listFiles();
        assertEquals(3, files.length);
        assertEquals(".git", files[0].getName());
        assertEquals("README", files[1].getName());
        assertEquals("README2.txt", files[2].getName());
    }
    
    @Test
    public void should_report_exec_result() throws IOException, InterruptedException {
        Command command = new ShellCommand("wrong");
        server.addCommand(command);
        agent = new Agent(server);
        
        agent.execute();

        assertFalse(server.reportStatus(command).success());
    }

    @Test
    public void should_report_command_output() throws IOException, InterruptedException {
        Command command = new ShellCommand("ls");
        server.addCommand(command);
        agent = new Agent(server);

        agent.execute();

        CommandReport commandReport = server.reportStatus(command);
        assertTrue(commandReport.success());
        assertTrue(commandReport.getOutput().contains("src"));
    }
}
