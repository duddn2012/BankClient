package org.example;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        if(args.length != 2){
            System.err.println("Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];  //IP
        int portNumber = Integer.parseInt(args[1]); //port number

        User user = new User("WR",1002857822015L,24,"YWK");

        try(
                Socket echoSocket = new Socket(hostName, portNumber);
                ObjectOutputStream out = new ObjectOutputStream(echoSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(echoSocket.getInputStream()); //서버 입력을 받는다
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)) //사용자의 입력
                ) {

            out.writeObject(user);
            String response = (String)in.readObject();
            System.out.println(response);

            //String userInput;
            //while((userInput = stdIn.readLine())!= null){
            //    out.println(userInput);
            //    System.out.println("echo: " + in.readLine());
            //}
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host" + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}