import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//import newAttempt.innerPanel;
//import newAttempt.myPanel;
//Server (Basically Client will Give Processes to THIS INSTANCE

public class Main {

	 static JFrame frame = new JFrame("OS Project");
	 static JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
	 static JPanel processPanel = new JPanel(new GridLayout(3, 4));
	 static JPanel memoryPanel = new JPanel(new GridLayout(3, 1));
	 static processButton[] pbutton;	 
	 static    int click=0;


    public static void main(String[] args) throws IOException {
   //  final   JFrame frame = new JFrame("Border Layout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(255,200,0));
        processPanel.setBackground(new Color(123,0,200));
        memoryPanel.setBackground(new Color(123,0,200));
       buttonPanel.setBackground(new Color(123,0,200));
        // Create a panel with GridLayout to hold the buttons
        //JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
       
       String str = "Process_Management Memory_Management "
       		+ "I/O_Management Others";
       int menuLength = str.split("\\s+").length;
       menuButton[] button=new menuButton[menuLength];
       int i=0;
       String[] words = str.split("\\s+");
       for (String word : words) {
    	   button[i]=new menuButton(word);
    	   buttonPanel.add(button[i]);
           System.out.println(word);
           i++;
        }
       //Process Management Buttons
       //ChangedPriority Removed because not using it anyways.......
       String process = "Create_Process Destroy_Process Suspend Block"
       		+ " Resume Wakeup Dispatch Change_Priority "
       		+ "Round-Robin_Mode FCFS_Mode Remote_Access Config_Process";
          int processLength = process.split("\\s+").length;
          pbutton=new processButton[processLength];
          int j=0;
          String[] w = process.split("\\s+");
          for (String wor : w) {
       	   pbutton[j]=new processButton(wor);
       	   processPanel.add(pbutton[j]);
              System.out.println(wor);
              j++;
           }
          //Memory Management
          String memory = "Frame_Size Create_Pages CPU_Demand";
                int memoryLength = memory.split("\\s+").length;
                JButton[] mbutton=new processButton[memoryLength];
                int k=0;
                String[] word = memory.split("\\s+");
                for (String w_space : word) {
             	   mbutton[k]=new processButton(w_space);
             	   mbutton[k].setBackground(new Color(240,100,124));
             	   memoryPanel.add(mbutton[k]);
                    System.out.println(w_space);
                    k++;
                 }
        // Create a panel with GridLayout to hold the buttons for the new panel
        //final JPanel newPanel = new JPanel(new GridLayout(4, 2));
       
        processPanel.setVisible(false);
        memoryPanel.setVisible(false);
        // Add the button panel to the frame using BorderLayout
        frame.add(buttonPanel, BorderLayout.SOUTH);
        JButton pages=new JButton("Send Pages To Memory");
      //  pages.setBackground(new Color(0,255,150));
        //To Send Process Created Pages to Memory Management For LRU
        pages.setBackground(new Color(255,200,0));
        frame.add(pages, BorderLayout.CENTER);
        
        //Just checking right now one thing:
//  //   JButton startbutton=new JButton();
//     JButton startbutton1=new JButton();
//     startbutton.setBackground(new Color(255,252,255));
//     startbutton1.setBackground(new Color(255,200,0));
     
//     frame.add(startbutton, BorderLayout.CENTER);
//       frame.add(startbutton1, BorderLayout.CENTER);
       
        
        
        // Add an ActionListener to Button 1 to add the new panel to the North region of the JFrame
        //Process Button Clicked
        button[0].addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                processPanel.setVisible(true);
                frame.add(createBackButton(), BorderLayout.EAST);
              //  frame.add(newPanel, BorderLayout.NORTH);
                frame.revalidate();
            }
        });      
        
        //Memory Button Clicked
  button[1].addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                memoryPanel.setVisible(true);
                frame.add(createBackButton(), BorderLayout.EAST);
              //  frame.add(newPanel, BorderLayout.NORTH);
                frame.revalidate();
            }
        });      
        
        // Add the new panel to the North region of the JFrame
        frame.add(processPanel, BorderLayout.NORTH);
        frame.add(memoryPanel, BorderLayout.WEST);

    pbutton[0].addActionListener(new pcbButtons.Button1Listener());	
    pbutton[1].addActionListener(new pcbButtons.Button2Listener());
    pbutton[2].addActionListener(new pcbButtons.Button3Listener());
    pbutton[3].addActionListener(new pcbButtons.Button3Listener());
    pbutton[4].addActionListener(new pcbButtons.Button4Listener());
    pbutton[5].addActionListener(new pcbButtons.Button4Listener());
    pbutton[6].addActionListener(new pcbButtons.Button5Listener());
    pbutton[7].addActionListener(new pcbButtons.Button6Listener());
    pbutton[8].addActionListener(new pcbButtons.Button7Listener());
    pbutton[9].addActionListener(new pcbButtons.Button8Listener());
    pbutton[10].addActionListener(new pcbButtons.Button9Listener());
    pbutton[11].addActionListener(new pcbButtons.Button10Listener());

    
    //Remote Connection Button
    
    //MemoryButtons
    mbutton[0].addActionListener(new memButtons.Button1Listener());
    mbutton[1].addActionListener(new memButtons.Button2Listener());
    mbutton[2].addActionListener(new memButtons.Button3Listener());
    
    pages.addActionListener(new ActionListener() {
        
        public void actionPerformed(ActionEvent e) {
        	new pcbButtons();
        	 new memButtons();
        	 if(!pcbButtons.readyQueue.isEmpty()) {
        		 int Size=pcbButtons.readyQueue.size();
        	memButtons.refStringSize =	Size;
        	memButtons.refString=new int[Size];
        	memButtons.frameSize =	pcbButtons.readyQueue.peek().memoryRequired;
        	int count=0;
        	for(pcb temp: pcbButtons.readyQueue) {
        		memButtons.refString[count]=temp.allocatedMemory;
        		count++;
        	}
        }
      }
    });     


        frame.setVisible(true);
        System.out.println("Hello I'm at the end of Main");
     
        //Continuous Server Starting Here
   	  ServerSocket serverSocket;
   	
   	 
   	 
   //     BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter 's' to act as the server, or 'c' to act as the client: ");
        String mode = "s";

        if (mode.equals("s")) {
            // This program is acting as the server
            serverSocket = new ServerSocket(2005);
            System.out.println("Waiting for a connection...");

            // Wait for a connection from the client program
            Socket socket = serverSocket.accept();
            System.out.println("Connection established.");

            // Get input and output streams for the socket
            int click=0;
            do {
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);

            // Read a message from the client program and print it to the console
            String message = socketReader.readLine();
            System.out.println("Received message from client: " + message); 	
            String s="Create_Process";
            if(message!=null) {
            		simulateButtonClick(pbutton[0]);
            }
            		System.out.println("Button "+s+" clicked");     
            		message="";
            // Send a response back to the client program
            //Scanner s=new Scanner(System.in);
      //      String m=s.nextLine();
            socketWriter.println("Server Said: ok");
            }while(click==0);
            // Close the socket and server socket
            socket.close();
            serverSocket.close();

        }  else {
            System.out.println("Invalid mode. Please enter 's' or 'c'.");
        }
        
        //Server Ended
        
        
        
        System.out.print("Hello im out");
    }

    // Helper method to create the back button
    private static JButton createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
    
            public void actionPerformed(ActionEvent e) {
                // Hide the new panel and remove the back button
                processPanel.setVisible(false);
               memoryPanel.setVisible(false);
                ((JButton) e.getSource()).getParent().remove(((JButton) e.getSource()));
                frame.revalidate();
            }
        });
        return backButton;
    }
    public static void simulateButtonClick(JButton button) {
        ActionEvent event = new ActionEvent(button, ActionEvent.ACTION_PERFORMED, "Simulated click");
        ActionListener[] listeners = button.getActionListeners();
        for (ActionListener listener : listeners) {
            listener.actionPerformed(event);
        }
    }
}