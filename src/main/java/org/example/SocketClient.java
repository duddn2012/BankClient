package org.example;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

    public SocketClient(String hostName, Integer portNumber) {
        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                ObjectOutputStream out = new ObjectOutputStream(echoSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(echoSocket.getInputStream()); //서버 입력을 받는다
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)) //사용자의 입력
        ) {
            User user = new User("WR", 1002857822015L, 24, "YWK");

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host" + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}