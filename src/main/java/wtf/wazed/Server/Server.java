package wtf.wazed.Server;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException {
        // don't need to specify a hostname, it will be the current machine
        ServerSocket ss = new ServerSocket(7777);

        System.out.println("ServerSocket awaiting connections...");
        Socket socket = ss.accept();
        System.out.println("Connection from " + socket + "!");

        // get the input stream from the connected socket
        InputStream inputStream = socket.getInputStream();
        // create a DataInputStream so we can read data from it.
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        // read the message from the socket
        String message = dataInputStream.readUTF();
        System.out.println("The message sent from the socket was: " + message);

        System.out.println("Closing sockets.");
        ss.close();
        socket.close();
    }
}
