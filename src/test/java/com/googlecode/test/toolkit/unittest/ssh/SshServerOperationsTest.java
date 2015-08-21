package com.googlecode.test.toolkit.unittest.ssh;

import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.junit.Assert;
import org.junit.Test;

import com.googlecode.test.toolkit.server.common.exception.UncheckedServerOperationException;
import com.googlecode.test.toolkit.server.common.user.SshUser;
import com.googlecode.test.toolkit.server.ssh.ServerOperations;
import com.googlecode.test.toolkit.server.ssh.SshServerOperations;
import com.googlecode.test.toolkit.server.ssh.command.Ls;

public class SshServerOperationsTest {

    @Test
    public void testExecuteInnerCommandSingleServer() throws UncheckedServerOperationException, TimeoutException {
        SshUser sshUserOne = new SshUser("10.224.95.207", 333, "root", "pass");

        ServerOperations serverOperations = new SshServerOperations(sshUserOne);
        Map<String, String> lsResult = serverOperations.ls("/usr");
        System.out.println(lsResult);
        Assert.assertNotNull(lsResult);
    }

    @Test
    public void testTimeRelated() throws UncheckedServerOperationException, TimeoutException {
        SshUser sshUser = new SshUser("10.224.10.224", "root", "pass");
        SshServerOperations sshServerOperations = new SshServerOperations(sshUser);
        Map<String, Long> currentTimeSeconds = sshServerOperations.getCurrentTimeSeconds();
        System.out.println(currentTimeSeconds);

        Map<String, String> currentTime = sshServerOperations.getCurrentTime();
        System.out.println(currentTime);

        Map<String, String> currentTime2 = sshServerOperations.getCurrentTime("%y/%m/%d_%H:%M:%S");
        System.out.println(currentTime2);

        sshServerOperations.modifyCurrentTime(-300);

        Map<String, String> currentTime3 = sshServerOperations.getCurrentTime();
        System.out.println(currentTime3);
    }

    @Test
    public void testExecuteInnerCommand() throws UncheckedServerOperationException, TimeoutException {
        SshUser sshUserOne = new SshUser("10.224.95.207", 333, "root", "pass");
        SshUser sshUserTwo = new SshUser("10.224.95.209", 333, "root", "pass");
        ServerOperations serverOperations = new SshServerOperations(sshUserOne, sshUserTwo);
        Map<String, String> lsResult = serverOperations.ls("/usr");
        System.out.println(lsResult);
        Assert.assertNotNull(lsResult);
    }

    @Test
    public void testDirectlyExecuteCommandWithResult() throws UncheckedServerOperationException, TimeoutException {
        SshUser sshUserOne = new SshUser("10.224.95.207", 333, "root", "pass");
        SshUser sshUserTwo = new SshUser("10.224.95.209", 333, "root", "pass");
        ServerOperations serverOperations = new SshServerOperations(sshUserOne, sshUserTwo);

        Map<String, String> executeCommandWithResult = serverOperations.executeCommandWithResult(Ls
                .newInstanceLsPath("/usr"));
        System.out.println(executeCommandWithResult);
        Assert.assertNotNull(executeCommandWithResult);

    }

    @Test
    public void testDirectlyExecuteCommandWithoutResult() throws UncheckedServerOperationException, TimeoutException {
        SshUser sshUserOne = new SshUser("10.224.95.207", 333, "root", "pass");
        SshUser sshUserTwo = new SshUser("10.224.95.209", 333, "root", "pass");
        ServerOperations serverOperations = new SshServerOperations(sshUserOne, sshUserTwo);

        serverOperations.executeCommandWithoutResult(Ls.newInstanceLsPath("/usr"));
    }

    @Test
    public void testDirectlyExecuteCommandWithError() throws UncheckedServerOperationException, TimeoutException {
        SshUser sshUserOne = new SshUser("10.224.95.207", 333, "root", "pass");

        ServerOperations serverOperations = new SshServerOperations(sshUserOne);
        Exception e = null;
        try {
            Map<String, String> executeCommandWithResult = serverOperations.executeCommandWithResult(Ls
                    .newInstanceLsPath("/"));
            System.out.println(executeCommandWithResult);
            Assert.assertNotNull(executeCommandWithResult);
        } catch (Exception e1) {
            e = e1;
        } finally {
            serverOperations.disconnect();
        }
        Assert.assertNotNull(e);
    }

    @Test
    public void testExecuteInnerGetLineCommand() throws UncheckedServerOperationException, TimeoutException {
        SshUser sshUserOne = new SshUser("10.224.57.207", 333, "root", "Aa1234");
        ServerOperations serverOperations = new SshServerOperations(sshUserOne);
        Map<String, Long> lsResult = serverOperations.getLineNumberForFile("/usr/local/nginx/conf/idmap.conf");
        System.out.println(lsResult);
        Assert.assertNotNull(lsResult);
    }

    @Test
    public void testExecuteInnerGrepCommand() throws UncheckedServerOperationException, TimeoutException {
        SshUser sshUserOne = new SshUser("10.224.57.207", 333, "root", "Aa1234");
        ServerOperations serverOperations = new SshServerOperations(sshUserOne);
        Map<String, String> lsResult = serverOperations.getContentWithKeywords("/usr/local/nginx/conf/idmap.conf",
                "Timeou", "5");
        System.out.println(lsResult);
        Assert.assertNotNull(lsResult);
    }

}
