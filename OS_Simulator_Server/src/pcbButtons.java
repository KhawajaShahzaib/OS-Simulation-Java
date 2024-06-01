import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
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
	
	static JPanel gantPanel = new JPanel(new FlowLayout());
	static JPanel readyPanel = new JPanel(new FlowLayout());
	static JPanel blockedPanel = new JPanel(new FlowLayout());
	static JPanel statesPanel=new JPanel(new GridLayout(5,1));
	static boolean FCFS=true;
	static int qtime;
	
	static void displayStates(Queue<pcb> p) {
		statesPanel.removeAll();
		for(pcb s: p) {
		JButton b=new JButton(s.state+s.processID);
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
     	else if(Text=="ready") {b.setBackground(new Color(19,136,8));}
     	else {b.setBackground(new Color(255,110,0));}
     	String s="P";
     	s+=Integer.toString(temp.processID);
     	s=s+"    bt-";
     	s=s+Integer.toString(temp.btime);
     	s=s+" "+temp.state;
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
    public static class Button1Listener implements ActionListener{
    	//Create
        public void actionPerformed(ActionEvent e) {
        	String inputValue="";
            inputValue =  JOptionPane.showInputDialog("Please enter Memory: ");
            if( Integer.parseInt(inputValue)  < 64 ) {
            	readyQueue.add(new pcb(Integer.parseInt(inputValue)));
            }
            else {
            	readyQueue.add(new pcb());
            }
            readyQueue.peek().state="Ready";
            	System.out.println(readyQueue.size());
            	ready=FrameSetter(ready);
            	displayQueue(readyQueue, "ready", readyPanel);
            }
        
        }
    
    public static class Button2Listener implements ActionListener {
//Destroy
        public void actionPerformed(ActionEvent e) {
         pcb p=readyQueue.poll();
         if(p!=null) {
        	 p.state="Destroyed";
        	 ready=FrameSetter(ready);
         	displayQueue(readyQueue, "ready", readyPanel);
        	 System.out.println("Process Destroyed");
         }
        }
    }
    
    public static class Button3Listener implements ActionListener {
//Suspend
        public void actionPerformed(ActionEvent e) {
        	pcb p=readyQueue.poll();
        	if(p!=null) {
        	p.state="Suspend";
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
    };
    
    public static class Button4Listener implements ActionListener {
//Resume
        public void actionPerformed(ActionEvent e) {
        	pcb p=blockedQueue.poll();
        	if(p!=null) {
        	p.state="Resume";
        	readyQueue.add(p);
        	System.out.print(blockedQueue.size());
        	System.out.print("Ready Queue: ");
        	for(pcb temp: readyQueue) {
        		System.out.print(temp.processID+" ");
        	}
        }
      }
   };
    public static class Button5Listener implements ActionListener {
//Dispatch
    	 public void actionPerformed(ActionEvent e) {
         	pcb p=readyQueue.poll();
         	if(p!=null) {
         	if(gantChart.peek()!=null) {
         		pcb lastElement = ((LinkedList<pcb>) gantChart).getLast();
         		if(FCFS==false) {
         			if(lastElement.btime>qtime) {
         			lastElement.state="Ready";
         			lastElement.btime=gantChart.peek().btime-qtime;
         			readyQueue.add(lastElement);
         			
         		System.out.print("Burst Time: "+p.btime);
         			}
         		else {
         			lastElement.state="Destroyed";
         		}
         		}
         		else {
         			System.out.println("HELLO ITS TRUE HEREEEEEE");
         			 lastElement.state="Destroyed";
         			System.out.println(lastElement.state+ " ID:" + lastElement.processID);
         		}
         	p.state="Running";
       //  	gantChart.add(lastElement);
         	gantChart.add(p);
         	}
         	else {
             	p.state="Running";
             	gantChart.add(p);
         	}
         	System.out.print("Gant Chart: ");
         	gant=FrameSetter(gant);
         	//ready=FrameSetter(ready);
         	blocked=FrameSetter(blocked);
         	displayQueue(blockedQueue, "blocked", blockedPanel);
         	displayQueue(gantChart, "gant", gantPanel);
         	displayQueue(readyQueue, "ready", readyPanel);
         //GantChart not Empty
         
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
    public static class Button8Listener implements ActionListener {
//FCFS
        public void actionPerformed(ActionEvent e) {
           FCFS=true;
            System.out.println("FirstComeFirstServe Scheduling Activated");
        }
    }
    
    public static class Button9Listener implements ActionListener {
//Remote_Access
        public void actionPerformed(ActionEvent e) {
         pcb p=readyQueue.poll();
         if(p!=null) {
        	 p.state="Destroyed";
        	 ready=FrameSetter(ready);
         	displayQueue(readyQueue, "ready", readyPanel);
        	 System.out.println("Process Destroyed");
         }
        }
    }
    
    
    
    
    public static class Button10Listener implements ActionListener {
//Config
        public void actionPerformed(ActionEvent e) {
         pcb p=readyQueue.poll();
         if(p!=null) {
        	 p.state="Destroyed";
        	 ready=FrameSetter(ready);
         	displayQueue(readyQueue, "ready", readyPanel);
        	 System.out.println("Process Destroyed");
         }
        }
    }
    
}