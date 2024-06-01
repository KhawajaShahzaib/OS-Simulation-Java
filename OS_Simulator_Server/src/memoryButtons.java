import java.awt.Color;
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
import java.util.ArrayList;
import java.util.Queue;

class cQueue{
	int[] a;
	int lru=0;
	int front, rear;
	cQueue(int x){
		a=new int[x];
		front=0;
	}
	void push(int x){
		if(!isFull()) {
			lru++;
			a[front]=x;
			front++;
		}
		else {
			front=0;
			a[front]=x;
			front++;
		}
	}
	boolean isFull(){
		if(front==3) {
			return true;
		}
		return false;
	}
	
	boolean search(int x) {
		for(int i=0;i<lru;i++) {
			if(a[i]==x) {
				return true;
			}
		}
		return false;
	}
	void hit() {
		if(!isFull()) {
			front++;
		}
		else {
			front=0;
		}
	}
}


public class memoryButtons {
	
	static  Queue<Integer> refString = new LinkedList<Integer>();
	static int frameSize=0;
	static cQueue fn=new cQueue(frameSize);
	 public static class Button1Listener implements ActionListener {
	    	//Frame Size Setter
	        public void actionPerformed(ActionEvent e) {
	        	boolean size=false;
	        	String inputValue="";
	        	do {
	            inputValue =  JOptionPane.showInputDialog("Please enter Frame Size: ");
	            if( Integer.parseInt(inputValue)  > 0 ) {
	              	size=true;
	            }
	            	System.out.println("Size Valid: "+size);
	        }
	        	while(size==false);
	        	frameSize=Integer.parseInt(inputValue);
	        }
	   }
	    
	    public static class Button2Listener implements ActionListener {
	//Create Pages
	        public void actionPerformed(ActionEvent e) {
	        if(frameSize>0) {
	        	for(int i=0;i<frameSize;i++) {
	        		 String inputValue =  JOptionPane.showInputDialog("Please"
	        		 		+ " enter Page Number: ");
	 	 if( Integer.parseInt(inputValue)  > 0 && Integer.parseInt(inputValue)<20 ) {
	 	              	refString.add(Integer.parseInt(inputValue));
	 	            }
	 	            else {
	 	            	JOptionPane.showInputDialog("Not a Valid Page Number ");
	 	            	i--;
	 	        }
	       } 	
	     }
	   }
	}
	    
	    public static class Button3Listener implements ActionListener {
	//Cpu Demandz
	        public void actionPerformed(ActionEvent e) {
	        	         
	        }
	    };

}
