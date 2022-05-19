package com.pa.homework;

import com.pa.homework.social.Network;
import com.pa.homework.social.User;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Objects;

class ClientThread extends Thread {
    private Socket socket = null;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    private MyServer server;

    public ClientThread (Socket socket, MyServer server) {
        try {
            this.socket = socket;
            socket.setSoTimeout(60000);
            this.server = server;
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e){
            System.err.println(e);
            closeEverything();
        }
    }

    @Override
    public void run () {
        String clientMessage;

        while(socket.isConnected()){
            try {
                clientMessage = bufferedReader.readLine();

                matchCommand(clientMessage);
                if (Objects.equals(clientMessage.split(" ")[0], "stop"))
                    break;

            } catch (IOException e){
                System.err.println(e);
                closeEverything();
                break;
            }
        }
    }

    public void matchCommand(String message){
        String arr[] = message.split(" ", 2);

        switch (arr[0]){
            case "register":
                if (arr.length > 1) {
                    System.out.println("register: " + arr[1].split(" ")[0]);
                    Network.register(arr[1].split(" ")[0]);
                }
                break;
            case "login":
                if (arr.length > 1) {
                    String name = arr[1].split(" ")[0];
                    System.out.println("login: " + name);
                    if (!Network.isUser(name)){
                        Network.register(name);
                    }

                    this.username = name;
                }
                break;
            case "friend":
                if (arr.length > 1){
                    String friends = arr[1];
                    if (username == null){
                        try {
                            bufferedWriter.write("You must first login");
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        } catch (IOException e){
                            System.err.println(e);
                        }
                    }
                    else{
                        System.out.println(friends);
                        Network.addFriends(username, friends);
                    }
                }
                break;
            case "send":
                if (arr.length > 1){
                    String messageToFriends = arr[1];
                    if (username == null){
                        try {
                            bufferedWriter.write("You must first login");
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        } catch (IOException e){
                            System.err.println(e);
                        }
                    }
                    else{
                        Network.sendMessage(username, messageToFriends);
                    }
                }
                break;
            case "read":
                if (username == null){
                    try {
                        bufferedWriter.write("You must first login");
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    } catch (IOException e){
                        System.err.println(e);
                    }
                }
                else{
                    List<String> inbox = Network.getInbox(username);
                    System.out.println("Here is the inbox: ");
                    System.out.println(inbox);
                    for (String messageFromFriend : inbox){
                        try {
                            bufferedWriter.write(messageFromFriend);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        } catch (IOException e){
                            System.err.println(e);
                        }
                    }
                }
                break;
            case "stop":
                server.closeServer();

                break;
            default:
                System.out.println("No known command");

        }
        System.out.println("Done with: " + message);
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