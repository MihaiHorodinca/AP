package com.pa.homework;

public class Main {
    public static void main(String[] args) {
        MyServer server = new MyServer(8801);
        server.startServer();
        return;
    }
}
