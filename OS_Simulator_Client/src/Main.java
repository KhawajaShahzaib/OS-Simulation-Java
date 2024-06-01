import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Queue;
import java.util.*;

//import newAttempt.innerPanel;
//import newAttempt.myPanel;

public class Main {

	 static JFrame frame = new JFrame("OS Project");
	 static JPanel newPanel = new JPanel(new GridLayout(2, 5));
	 static JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
	 static JPanel remotePanel=new JPanel(new GridLayout(4,1));
	 
    public static void main(String[] args) {
   //  final   JFrame frame = new JFrame("Border Layout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(0,0,0));
        newPanel.setBackground(new Color(123,0,200));
       buttonPanel.setBackground(new Color(123,0,200));
       remotePanel.setBackground(new Color(0,0,0));      
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
       
       String process = "Create_Process Destroy_Process Suspend Block"
       		+ " Resume Wakeup Dispatch Change_Priority "
       		+ "Round-Robin_Mode FCFS_Mode";
          int processLength = process.split("\\s+").length;
          processButton[] pbutton=new processButton[processLength];
          int j=0;
          String[] w = process.split("\\s+");
          for (String wor : w) {
       	   pbutton[j]=new processButton(wor);
       	   newPanel.add(pbutton[j]);
              System.out.println(wor);
              j++;
           }

        // Create a panel with GridLayout to hold the buttons for the new panel
        //final JPanel newPanel = new JPanel(new GridLayout(4, 2));
       JButton remote=new JButton("Remote");
       JButton remoteOff=new JButton("No-Remote");
       remote.setBackground(new Color(255,150,123));
       remoteOff.setBackground(new Color(255,150,123));
       remotePanel.add(remote);
       remotePanel.add(remoteOff);
       
       
       
       remotePanel.setVisible(false);
        newPanel.setVisible(false);
        
        // Add the button panel to the frame using BorderLayout
        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add an ActionListener to Button 1 to add the new panel to the North region of the JFrame
        button[0].addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                newPanel.setVisible(true);
                remotePanel.setVisible(true);
                frame.add(createBackButton(), BorderLayout.EAST);
                frame.add(remotePanel, BorderLayout.WEST);

              //  frame.add(newPanel, BorderLayout.NORTH);
                frame.revalidate();
            }
        });      
        // Add the new panel to the North region of the JFrame
        frame.add(newPanel, BorderLayout.NORTH);

    pbutton[0].addActionListener(new pcbButtons.Button1Listener());	
    pbutton[1].addActionListener(new pcbButtons.Button2Listener());
    pbutton[2].addActionListener(new pcbButtons.Button3Listener());
    pbutton[3].addActionListener(new pcbButtons.Button3Listener());
    pbutton[4].addActionListener(new pcbButtons.Button4Listener());
    pbutton[5].addActionListener(new pcbButtons.Button4Listener());
    pbutton[6].addActionListener(new pcbButtons.Button5Listener());
    pbutton[7].addActionListener(new pcbButtons.Button6Listener());
    pbutton[8].addActionListener(new pcbButtons.Button7Listener());
   
    
    remote.addActionListener(new pcbButtons.ButtonRListener());
        frame.setVisible(true);
        System.out.println("Hello I'm at the end of Main");

    }

    // Helper method to create the back button
    private static JButton createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
    
            public void actionPerformed(ActionEvent e) {
                // Hide the new panel and remove the back button
            	remotePanel.setVisible(false);
                newPanel.setVisible(false);
                ((JButton) e.getSource()).getParent().remove(((JButton) e.getSource()));
                frame.revalidate();
            }
        });
        return backButton;
    }
}