package org.example;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

    public SocketClient(String hostName, Integer portNumber) {
        try (
                Socket connectedSocket = new Socket(hostName, portNumber);
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connectedSocket.getOutputStream(), "UTF-8"));
                BufferedReader in = new BufferedReader(new InputStreamReader(connectedSocket.getInputStream(), "UTF-8"));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)) //사용자의 입력
        ) {

            // Shutdown Hook 등록
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("프로그램 종료 중...");
                try {
                    // 프로그램 종료 시 작업을 수행
                    out.write("100\n");
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));

            ServerMessageReader serverMessageReader = new ServerMessageReader(connectedSocket);
            serverMessageReader.start();

            System.out.print("아이디를 입력해주세요.\n아이디:");
            out.write("200"+stdIn.readLine()+"\n");
            out.flush();
            System.out.print("이름를 입력해주세요.\n이름:");
            out.write("201"+stdIn.readLine()+"\n");
            out.flush();
            System.out.print("이메일을 입력해주세요.\n이메일:");
            out.write("202"+stdIn.readLine()+"\n");
            out.flush();
            System.out.print("가입할 서비스의 번호를 입력해주세요.\n1. 입출금 2. 예금 3. 적금\n");
            out.write("300"+stdIn.readLine()+"\n");
            out.flush();
            System.out.print("보유 자산을 입력해주세요.\n자산:");
            out.write("301"+stdIn.readLine()+"\n");
            out.flush();

            while(true){
                Thread.sleep(100);
                System.out.print("이용할 서비스의 번호를 입력해주세요.\n1. 입금 2. 출금 3. 계좌이체\n");
                String flag = stdIn.readLine();
                String credit;
                switch (flag){
                    case "1":
                        System.out.print("입금할 금액을 입력해주세요.\n금액 :");
                        credit = stdIn.readLine();
                        out.write("302" + credit + "\n");
                        out.flush();
                        break;
                    case "2":
                        System.out.print("출금할 금액을 입력해주세요.\n금액:");
                        credit = stdIn.readLine();
                        out.write("303" + credit + "\n");
                        out.flush();
                        break;
                    case "3":
                        System.out.print("계좌이체 할 대상과 금액을 입력해주세요.\n대상 & 금액:");
                        credit = stdIn.readLine();
                        out.write("304" + credit + "\n");
                        out.flush();
                        break;
                    default:
                        out.write("100\n");
                        out.flush();
                        System.exit(1);
                        break;
                }
            }

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host" + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}