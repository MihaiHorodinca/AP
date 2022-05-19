package com.pa.homework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    private ServerSocket serverSocket = null;
    private boolean stopFlag = false;
    private volatile int noConnections = 0;

    public MyServer(int PORT) {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void stop(){
        stopFlag = true;
        noConnections -= 1;
        System.out.println(noConnections);
    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed() && !stopFlag){
                Socket socket = serverSocket.accept();
                System.out.println("Got a client.");
                ClientThread clientThread = new ClientThread(socket, this);
                clientThread.start();
                noConnections += 1;
            }
        } catch (IOException e){
            System.err.println(e);
        }

        while (noConnections != 0){}


        System.out.println(noConnections);
        System.exit(0);
    }

    public void closeServer(){
        try{
            if (serverSocket != null){
                serverSocket.close();
            }
        }
        catch (IOException e){
            System.err.println(e);
        }
    }
}