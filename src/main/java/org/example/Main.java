package org.example;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("<host name> <port number> 순으로 입력해주세요!");
            System.exit(1);
        }

        String hostName = args[0];  //IP
        int portNumber = Integer.parseInt(args[1]); //Port Number

        SocketClient socketClient = new SocketClient(hostName, portNumber); //클라이언트 소켓 생성
    }
}