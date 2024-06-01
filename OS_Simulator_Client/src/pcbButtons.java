import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class pcbButtons {
	static  Queue<pcb> readyQueue = new LinkedList<pcb>();
	static Queue<pcb> gantChart=new LinkedList<pcb>();
	static Queue<pcb> blockedQueue=new LinkedList<pcb>();

	static JFrame gant=new JFrame("Gant Chart");
	static JFrame ready=new JFrame("Ready Queue");
	static JFrame blocked=new JFrame("Blocked Queue");
	static JFrame states=new JFrame("States Display");
	
	static JPanel gantPanel = new JPanel(new GridLayout(1, 5));
	static JPanel readyPanel = new JPanel(new GridLayout(1, 5));
	static JPanel blockedPanel = new JPanel(new GridLayout(1, 5));
	static JPanel statesPanel=new JPanel(new GridLayout(5,1));
	static boolean FCFS=true;
	static int qtime;
	
	static boolean remoteConnection=false;	
	static Socket socket = new Socket();
	
	
	static BufferedReader socketReader;
    static PrintWriter socketWriter;
	
	static void displayStates(Queue<pcb> p) {
		statesPanel.removeAll();
		for(pcb s: p) {
		JButton b=new JButton(s.state);
		statesPanel.add(b);
     	states.add(statesPanel);
		}
		states.setSize(100,500);
		states.setVisible(true);
		states.repaint();
     	states.revalidate();
	}
	
	static JFrame FrameSetter(JFrame F) {
		F.setVisible(true);
		F.setBackground(new Color(0,0,0));
		F.setSize(1300,100);
		return F;
	}
	

	static void displayQueue(Queue<pcb> queue, String Text, JPanel buttonPanel) {
		buttonPanel.setVisible(true);
     	buttonPanel.removeAll();
     	for(pcb temp: queue) {
     	JButton b=new JButton();
     	if(Text=="gant") {b.setBackground(new Color(255,255,255));}
     	else if(Text=="ready") {b.setBackground(new Color(0,110,155));}
     	else {b.setBackground(new Color(255,110,0));}
     	String s="P";
     	s+=Integer.toString(temp.processID);
     	s=s+"    bt-";
     	s=s+Integer.toString(temp.btime);
     	b.setText(s);
     	b.setFont(new Font("Arial", Font.BOLD, 15));
     	buttonPanel.add(b);
     		System.out.print(temp.processID+" ");
     	}   	
     	System.out.println();
     	if(Text=="gant") {
     	gant.add(buttonPanel);
     	gant.setVisible(true);
     	buttonPanel.setVisible(true);
     	gant.repaint();
     	gant.revalidate();
     	displayStates(queue);
     	}
     	else if(Text=="ready") {
     		ready.add(buttonPanel);
         	ready.setVisible(true);
         	buttonPanel.setVisible(true);
         	ready.repaint();
         	ready.revalidate();
        // 	displayStates(queue);
     	}
     	else {
    		blocked.add(buttonPanel);
         	blocked.setVisible(true);
         	buttonPanel.setVisible(true);
         	blocked.repaint();
         	blocked.revalidate();
         	displayStates(queue);
     	}
	}
    public static class Button1Listener implements ActionListener {
    	//Create
    //	Create_Process Destroy_Process Suspend Block"
   	//	+ " Resume Wakeup Dispatch Change_Priority "
   	//	+ "Round-Robin_Mode FCFS_Mode Remote_Access Config_Process";
        public void actionPerformed(ActionEvent e) {
        	if(remoteConnection) {
        	try {
				send("Create_Process");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.print("Could not send");
			}
        	}
        	else {
        	String inputValue="";
            inputValue =  JOptionPane.showInputDialog("Please enter Memory: ");
            if( Integer.parseInt(inputValue)  < 64 ) {
            	readyQueue.add(new pcb(Integer.parseInt(inputValue)));
            }
            else {
            	readyQueue.add(new pcb());
            }
            	System.out.println(readyQueue.size());
            	ready=FrameSetter(ready);
            	displayQueue(readyQueue, "ready", readyPanel);
            }
        
        }
    }
    
    public static class Button2Listener implements ActionListener {
//Destroy
        public void actionPerformed(ActionEvent e) { 
          	if(remoteConnection) {
            	try {
    				send("Destroy_Process");
    			} catch (IOException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    				System.out.print("Could not send");
    			}
            	}
            	else {
         pcb p=readyQueue.poll();
         if(p!=null) {
        	 p.state="destroyed";
        	 ready=FrameSetter(ready);
         	displayQueue(readyQueue, "ready", readyPanel);
        	 System.out.println("Process Destroyed");
         }
        }
    }
    }
    public static class Button3Listener implements ActionListener {
//Suspend
        public void actionPerformed(ActionEvent e) {
          	if(remoteConnection) {
            	try {
    				send("Suspend");
    			} catch (IOException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    				System.out.print("Could not send");
    			}
            	}
            	else {
        	pcb p=readyQueue.poll();
        	if(p!=null) {
        	p.state="suspend";
        	blockedQueue.add(p);
        	System.out.print(blockedQueue.size());
        	displayQueue(blockedQueue, "blocked", blockedPanel);
         	displayQueue(gantChart, "gant", gantPanel);
         	displayQueue(readyQueue, "ready", readyPanel);
        	System.out.print("Blocked Queue: ");
        	for(pcb temp: blockedQueue) {
        		System.out.print(temp.processID);
        	}
        }
        }
        }
    };
    
    public static class Button4Listener implements ActionListener {
//Resume
        public void actionPerformed(ActionEvent e) {
          	if(remoteConnection) {
            	try {
    				send("Resume");
    			} catch (IOException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    				System.out.print("Could not send");
    			}
            	}
            	else {
        	pcb p=blockedQueue.poll();
        	if(p!=null) {
        	p.state="resume";
        	readyQueue.add(p);
        	System.out.print(blockedQueue.size());
        	System.out.print("Ready Queue: ");
        	for(pcb temp: readyQueue) {
        		System.out.print(temp.processID+" ");
        	}
        }
      }
     }
   };
    public static class Button5Listener implements ActionListener {
//Dispatch
    	 public void actionPerformed(ActionEvent e) {
    		  	if(remoteConnection) {
    	        	try {
    					send("Dispatch");
    				} catch (IOException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    					System.out.print("Could not send");
    				}
    	        	}
    	        	else {
         	pcb p=readyQueue.poll();
         	if(p!=null) {
         	gantChart.add(p);
         	if(FCFS==false) {
         		System.out.print("Burst Time: "+p.btime);
         		if(p.btime>qtime) {
         			p.state="ready";
         			p.btime=p.btime-qtime;
         			System.out.print(" After: "+p.btime);
         			readyQueue.add(p);
         		}
         		else {
         			p.state="destroyed";
         			p.btime=0;
         		}
         	}
         	System.out.print("Gant Chart: ");
         	gant=FrameSetter(gant);
         	//ready=FrameSetter(ready);
         	blocked=FrameSetter(blocked);
         	displayQueue(blockedQueue, "blocked", blockedPanel);
         	displayQueue(gantChart, "gant", gantPanel);
         	displayQueue(readyQueue, "ready", readyPanel);

       
         }
       }
    	 }
    };
    public static class Button6Listener implements ActionListener {
//Change Priority
        public void actionPerformed(ActionEvent e) {
            System.out.println("Priority is Ignored because I'm doing FCFS and"
            		+ "Round-Robin so its just for showcasing....");
        }
    }
    public static class Button7Listener implements ActionListener {
//Round-Robin
        public void actionPerformed(ActionEvent e) {
          	if(remoteConnection) {
            	try {
    				send("RoundRobin");
    			} catch (IOException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    				System.out.print("Could not send");
    			}
            	}
            	else {
        	boolean i=true;
           FCFS=false; String inputValue="";
           while(i==true) {
           inputValue =  JOptionPane.showInputDialog("Please enter "
           		+ "Quantum time: ");
           if( Integer.parseInt(inputValue)  < 5 && Integer.parseInt(inputValue)>0) {
           i=false;
           }
          }
           qtime=Integer.parseInt(inputValue);
            System.out.println("Round-Robin Scheduling Activated");
        }
    }
  }
    public static class Button8Listener implements ActionListener {
//FCFS
        public void actionPerformed(ActionEvent e) {
          	if(remoteConnection) {
            	try {
    				send("FCFS");
    			} catch (IOException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    				System.out.print("Could not send");
    			}
            	}
            	else {
           FCFS=true;
            System.out.println("FirstComeFirstServe Scheduling Activated");
        }
    }
   }
    
    public static class ButtonRListener implements ActionListener {
//Remote-Connection
        public void actionPerformed(ActionEvent e) {
            try {
				Connection();
				 System.out.println("Remote Connection Activated");
				 remoteConnection=true;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.print("Connection not built");
			}
            
        }
    }
    public static class ButtonROFFListener implements ActionListener {
    	//Remote-Connection
    	        public void actionPerformed(ActionEvent e) {        
    					try {
							socket.close();
							send("Close");
							System.out.print("Connection closed");
							remoteConnection=false;
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							System.out.print("Connection could not close");
						}
    				}
    	            
   }
    	    
    
    
    static void Connection() throws IOException {
        // Determine whether this program should act as the client or server
            int i=4;
            socket.connect(new InetSocketAddress("127.0.0.1", 2005));
            System.out.print("Connection Build");
            // Get input and output streams for the socket
            send("Connection Received");
    }
    
    static void send(String m) throws IOException {
    	socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        socketWriter = new PrintWriter(socket.getOutputStream(), true);
     // Send a message to the server program
        Scanner s=new Scanner(System.in);
        socketWriter.println(m);
        String response = socketReader.readLine();
        System.out.println("Received message from server: " + response);
    }
    
    
}