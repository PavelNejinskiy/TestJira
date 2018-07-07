package Robert.utils.skyfence;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

import static Robert.gwSanity.TestData.*;


public class SSHVerification {
    private static String username = userNameConsole;
    private static String password = passwordConsole;
    private static String hostname = hostName;
    private static String logPath = "/var/log/automationLog/tempApiTestlog.tmp";
    private static ChannelExec channel;
    private static Session session;

    // Indicates if it is the first time current test failed

    public static Session getSession() {
        if (session == null || !session.isConnected()) {
            System.out.println("Session started");
            session = connect(hostname, username, password);
        }
        return session;
    }

    private static Session connect(String hostname, String username, String password) {
        JSch jSch = new JSch();
        try {
            session = jSch.getSession(username, hostname, 22);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword(password);
            session.connect(60000);
            System.out.println("Connected to " + hostname + " with username: " + username);
        } catch (Exception e) {
            System.out.println("An error occurred while connecting to " + hostname + ": " + e);
        }
        return session;
    }

    public static void sendCommand(String command) {
        try {
            channel = (ChannelExec) SSHVerification.getSession().openChannel("exec");
            System.out.println("Sending command:\n" + command);
            channel.setCommand(command);
            channel.connect(60000); // Max connection time
            System.out.println("execChannel connected");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startDebugMod() {

        sendCommand("/usr/skyfence/gateway/bin/gatewayctl log ServiceParser");
    }

    // run before each test
    public static void startActivityLogging() {
        String getLogCommand = "tail -f -n0 /var/log/gateway.log";

        String command = getLogCommand + " > " + logPath;
        sendCommand(command);
    }

    public static void deleteTempLogs() {
        String command = "rm " + logPath;
        sendCommand(command);
    }


    public static String[] getActivityLog() {
        stopLogging(3);
        return getLog();
    }

    private static void stopLogging(int delay) {
        System.out.println("Disconnecting from reading channel after " + delay + " seconds");

        try {
            Thread.sleep(delay * 1000);
            OutputStream out = channel.getOutputStream();
            // Sending CTRL + C
            out.write(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        channel.disconnect();
        System.out.println("execChannel disconnected");
    }

    private static String[] getLog() {
        ArrayList<String> logLines = new ArrayList<>();
        BufferedReader reader;
        String line;
        try {
            ChannelSftp sftpChannel = (ChannelSftp) getSession().openChannel("sftp");
            sftpChannel.connect(60000);
            System.out.println("sftpChannel connected");
            reader = new BufferedReader(new InputStreamReader(sftpChannel.get(logPath)));
            while ((line = reader.readLine()) != null) {
                logLines.add(line);
            }
            reader.close();
            sftpChannel.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("sftpChannel disconnected");
        SSHVerification.getSession().disconnect();
        System.out.println("Session dropped");
        return logLines.toArray(new String[0]);
    }
}
