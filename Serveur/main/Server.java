package Serveur.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Serveur.client.HandleClient;


public class Server {
    private static ServerSocket serveurSocket;
    private Socket clientSock;

    /**
     * Create a server that listens on the port defined
     *
     * @param port
     */
    public Server(int port) {
        try {
            //Create the ServerSocket
            serveurSocket = new ServerSocket(port);
            System.out.println("Listening to port " + port);
        } catch (IOException e) {
            //If the serveur can't open a socket, display an error message
            System.out.println("[ERROR] Couldn't open a socket on port " + port + ".");
            System.exit(-2);
        }
    }

    /**
     * Starts the server
     */
    public void startServer() {
        //The server runs continuously
        while (true) {
            try {
                //Accept the connection from the client
                clientSock = serveurSocket.accept();
                new HandleClient (clientSock).run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
