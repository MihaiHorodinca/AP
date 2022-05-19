package com.pa.homework;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Scanner;

public class MyClient {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public MyClient (int PORT) {
        String serverAddress = "127.0.0.1"; // The server's IP address
        try {
           socket = new Socket(serverAddress, PORT);
           bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
            closeEverything();
        } catch (IOException e){
            System.err.println("IOException... " + e);
            closeEverything();
        }
    }

    public void startClient(){
        // nou thread pentru a citi raspunsurile de la server
        Thread reader = new Thread(new Runnable() {
            @Override
            public void run() {
                String serverMessage;

                while (socket.isConnected()){
                    try{
                        serverMessage = bufferedReader.readLine();
                        System.out.println(serverMessage);
                    } catch (IOException e){
                        System.err.println(e);
                        closeEverything();
                    }
                }
            }
        });
        reader.start();

        // sectiunea pentru trimis mesaje catre server
        Scanner scanner = new Scanner(System.in);

        try {
            while (socket.isConnected()) {
                String clientMessage = scanner.nextLine();

                bufferedWriter.write(clientMessage);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                if (Objects.equals(clientMessage.split(" ")[0], "stop")) {
                    reader.stop();
                    break;
                }
            }
        } catch (IOException e){
            System.err.println(e);
            closeEverything();
            //System.exit(0);
        }
    }

    public void closeEverything(){
        try{
            if (bufferedWriter != null){
                bufferedWriter.close();
            }
            if (bufferedReader != null){
                bufferedReader.close();
            }
            if (socket != null){
                socket.close();
            }
        } catch (IOException e){
            System.err.println(e);
        }
    }
}
