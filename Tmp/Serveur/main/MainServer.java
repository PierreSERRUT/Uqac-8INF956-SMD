package main;


public class MainServer {
    public static void main(String[] args) {
        Server server = new Server(2016);
        server.startServer();
    }
}