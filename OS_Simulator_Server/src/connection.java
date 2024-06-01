import java.net.*;
import java.util.Scanner;
import java.io.*;

class MessageHandlerThread extends Thread {
    private final Socket socket;

    public MessageHandlerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Read messages in a loop
            String message;
            while ((message = socketReader.readLine()) != null) {
                System.out.println("Received message from client: " + message);
                // Handle the message as needed

                // Send a response back to the client program
                PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);
                socketWriter.println("Server Said: ok");
            }

            // Close the socket reader
            socketReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



public class connection {
    public static void main(String[] args) throws IOException {
        // Determine whether this program should act as the client or server
      
        String mode = "s";

        if (mode.equals("s")) {
            // This program is acting as the server
            ServerSocket serverSocket = new ServerSocket(2005);
            System.out.println("Waiting for a connection...");

            // Wait for a connection from the client program
            Socket socket = serverSocket.accept();
            System.out.println("Connection established.");
            MessageHandlerThread messageHandlerThread = new MessageHandlerThread(socket);
            messageHandlerThread.start();

            // Wait for the thread to finish (optional)
            try {
                messageHandlerThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Close the socket and server socket
            socket.close();
            serverSocket.close();

        }  else {
            System.out.println("Invalid mode. Please enter 's' or 'c'.");
        }
    }
}