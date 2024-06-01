import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.ArrayList;


class cStack{
	int tos;
	int arr[];
	int size;
cStack(int s){
	tos=0;
	size=s;
	arr=new int[size];
}

boolean isFull() {
	if(tos>=size) {
		return true;
	}
	else {
		return false;
	}
}

int search(int x) {
	for(int i=0;i<=size-1;i++) {
		if(arr[i]==x) {
			return i;
		}
	}
	return -1;
}

void replace(int oldPage, int newPage) {
	int s=search(oldPage);
	arr[s]=newPage;
}

void	push(int x){
		if(!isFull()) {
			arr[tos]=x;
			tos++;
			}
		}
	
void display() {
	System.out.print("Pages in Stack: ");
	for(int i=0;i<=size-1;i++) {
		System.out.print(arr[i]+" ");
	}
	System.out.println();
}

}

public class memButtons {
	static JFrame window=new JFrame("Frames");

	static JPanel frame=new JPanel(new GridBagLayout());
	static GridBagConstraints gbc=new GridBagConstraints();
	static JFrame FrameSetter(JFrame F) {
		F.setVisible(true);
		F.setBackground(new Color(0,0,0));
	//	F.setPreferredSize(new Dimension(100,100));
		F.setSize(500,500);
		return F;
	}
	
	static  int[] refString;
	static int refStringSize=0;
	static int frameSize=3;
	static cStack stack;
	static ArrayList<Integer> backtrack=new ArrayList<Integer>();
	static int s=-1;
	static boolean Hit, Miss=false;

	 public static class Button1Listener implements ActionListener {
	    	//Frame Size Setter
	        public void actionPerformed(ActionEvent e) {
	        	boolean size=false;
	        	String inputValue="";
	        	do {
	            inputValue =  JOptionPane.showInputDialog("Please enter Frame Size: ");
	            if( Integer.parseInt(inputValue)  > 1 ) {
	              	size=true;
	            }
	            	System.out.println("Size Valid: "+size);
	        }
	        	while(size==false);
	        	frameSize=Integer.parseInt(inputValue);
	        	stack=new cStack(frameSize);

	        }
	   }
	    
	    public static class Button2Listener implements ActionListener {
	//Create Pages
	        public void actionPerformed(ActionEvent e) {
	        	while(refStringSize==0) {
	        	 String refSize =  JOptionPane.showInputDialog("Please"
	        		 		+ " enter Ref String Size: ");
	        	 if( Integer.parseInt(refSize)  > 0 && Integer.parseInt(refSize)<11 ) {
	 	              	refStringSize=Integer.parseInt(refSize);
	 	              	refString=new int[refStringSize];
	 	            }
	        	}

	        	for(int i=0;i<refStringSize;i++) {
	        		 String inputValue =  JOptionPane.showInputDialog("Please"
	        		 		+ " enter Page Number: ");
	 	 if( Integer.parseInt(inputValue)  > 0) {
	 	              	refString[i]=Integer.parseInt(inputValue);
	 	            }
	 	            else {
	 	            	JOptionPane.showInputDialog("Not a Valid Page Number ");
	 	            	i--;
	 	        }	
	     }
	   }
	}
	 static int x,y=0;   
	    static void displayStack(cStack s, JPanel buttonPanel) {
	    	buttonPanel.setVisible(true);
	    //	buttonPanel.removeAll();
	     //	buttonPanel.removeAll();
	     	for(int i=frameSize-1;i>=0;i--) {
	     	JButton b=new JButton();
	     	if(y>=frameSize) {
	     		y=0;
	     		x++;
	     	}
	     	
	     	if(x>=refStringSize) {
	     		x=0;
	     		buttonPanel.removeAll();
	     	}
	     	gbc.gridx=x;
	     	gbc.gridy=y;
	     	y++;
	     	b.setBackground(new Color(255,200,0));
	     	String str=Integer.toString(s.arr[i]);
	     	b.setText(str);
	     	b.setFont(new Font("Arial", Font.BOLD, 15));
	     	b.setSize(200,200);
	     	buttonPanel.add(b, gbc);
	     	}
	     	window.add(buttonPanel);
	     	buttonPanel.setVisible(true);
	     	window.repaint();
	     	window.revalidate();
		}
	    
	    
	    public static class Button3Listener implements ActionListener {
	//Cpu Demandz
	        public void actionPerformed(ActionEvent e) {
	        	window=FrameSetter(window);

	        	for(int i=0;i<refString.length;i++) {
	        		 if(!stack.isFull()) {
	        			 s=stack.search(refString[i]);
	        			if(s==-1) { //Until its Not FULL
	        				stack.push(refString[i]);
	        				stack.display();
	        			displayStack(stack, frame);
	        			}
	        			else {
	        				stack.display();
	        				displayStack(stack, frame);
	        			}
	        		 }
	        		 //Now the List is FULL so BackTracking Needed now
	        		 else {
	        			// System.out.println("Stack is now Full");
	        			 int k=stack.search(refString[i]);
	        			 
	        			 if(k==-1) { //Miss
	        		//		 System.out.print("Missed");
	        				 int lru=-1;
	        				 //Now comes back_tracking
	        				 backtrack.clear();
	        				 for(int j=i-1;j>=0;j--) {
	        					 if(!backtrack.contains(refString[j])) {
	        						 backtrack.add(refString[j]);
	        						 lru++;
	        				//		 int bk=backtrack.get(lru);
	        				//		 System.out.println("b: "+bk);
	        					 }
	        					 if(lru>=frameSize-1) {
	        				//		 System.out.print("Breaking");
	        						 break;	//Break the ForLoop
	        					 }
	        				 }
	        				 stack.replace(backtrack.get(backtrack.size()-1), refString[i]);
	        				 stack.display();
	        				displayStack(stack, frame);
	        			 }
	        			 else {
	        					displayStack(stack, frame);
	        					stack.display();
	        			 }
	        				 
	        		 }
	        		
	        	}
	        }
	    }
	  };

	 
