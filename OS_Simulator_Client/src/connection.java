import java.net.*;
import java.util.Scanner;
import java.io.*;

public class connection {
    public static void main(String[] args) throws IOException {
        // Determine whether this program should act as the client or server
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter 's' to act as the server, or 'c' to act as the client: ");
        String mode = consoleReader.readLine();

   if (mode.equals("c")) {
            // This program is acting as the client
            Socket socket = new Socket("127.0.0.1", 2005);
            int i=4;
do {
            // Get input and output streams for the socket
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);

            // Send a message to the server program
            Scanner s=new Scanner(System.in);
            String m=s.nextLine();
            socketWriter.println("Cleint Said: "+m);

            // Read a response from the server program and print it to the console
            String response = socketReader.readLine();
            System.out.println("Received message from server: " + response);
            s.close();
} while(i==4);
            // Close the socket
            socket.close();
        } 
    }
}