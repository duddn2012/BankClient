package org.example;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

    public SocketClient(String hostName, Integer portNumber) {
        try (
                Socket connectedSocket = new Socket(hostName, portNumber);
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connectedSocket.getOutputStream(), "UTF-8"));
                BufferedReader in = new BufferedReader(new InputStreamReader(connectedSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)) //사용자의 입력
        ) {
            System.out.print("아이디를 입력해주세요.\n아이디:");
            out.write("ID"+stdIn.readLine()+"\n");
            out.flush();
            System.out.print("이름를 입력해주세요.\n이름:");
            out.write("NM"+stdIn.readLine()+"\n");
            out.flush();
            System.out.print("이메일을 입력해주세요.\n이메일:");
            out.write("EM"+stdIn.readLine()+"\n");
            out.flush();
            System.out.print("가입할 서비스의 번호를 입력해주세요.\n1. 입출금 2. 예금 3. 적금\n");
            out.write("SV"+stdIn.readLine()+"\n");
            out.flush();
            System.out.print("보유 자산을 입력해주세요.\n자산:");
            out.write("AC"+stdIn.readLine()+"\n");
            out.flush();

            String message = in.readLine();
            System.out.println(message);

            while(true){

            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host" + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}