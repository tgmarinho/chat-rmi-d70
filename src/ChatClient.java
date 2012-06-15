import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.Naming;
import java.rmi.Remote;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChatClient extends javax.swing.JFrame {
   
    /**
	 * 
	 */
	private static final long serialVersionUID = -4528626037753655436L;
	private JFrame frame;
    private JTextArea myText;
    private static JTextArea otherText;
    private JScrollPane myTextScroll;
    private JScrollPane otherTextScroll;
    private String textString = "";
    private boolean firstMessage = true;
    private static String name = null;
    
    private static final int HOR_SIZE = 400;
    private static final int VER_SIZE = 150;
    
    Notify displayChat;
    ChatInterface chatServer;
     
    public ChatClient() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                initComponents();
            }
        });
        try {
            Remote remoteObject = Naming.lookup("rmichat");

			if (remoteObject instanceof ChatInterface) {
				chatServer = (ChatInterface)remoteObject ;
				displayChat = new DisplayMessage(otherText);
			} else {
				System.out.println("Server not a Chat Server.");
				System.exit(0);
			}
        }
        catch(Exception e){
            System.out.println("RMI Lookup Exception");
            System.exit(0);
        };    
    	   	
    	frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
          	  try {
          	  	  if (name != null) {
          	          chatServer.leave(displayChat, name);
          	      }
          	  }
          	  catch (Exception ex) {
          	      otherText.append("Exit failed.");
          	  }
          	  System.exit(0);
            }
          });
    }
   

    private void initComponents() {
    	frame = new JFrame("Chat Client");
        myText = new JTextArea();
        
        myTextScroll = new JScrollPane(myText);			
        myTextScroll.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		myTextScroll.setVerticalScrollBarPolicy(
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		myTextScroll.setMaximumSize(
		    new java.awt.Dimension(HOR_SIZE, VER_SIZE));
		myTextScroll.setMinimumSize(new java.awt.Dimension(HOR_SIZE, VER_SIZE));
		myTextScroll.setPreferredSize(new java.awt.Dimension(
		    HOR_SIZE, VER_SIZE));

        myText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textTyped(evt);
            }
        });

        frame.getContentPane().add(myTextScroll, java.awt.BorderLayout.NORTH);
        
        otherText = new JTextArea();
        
        otherTextScroll = new JScrollPane(otherText);
        otherText.setBackground(new java.awt.Color(200, 200, 200));
        otherTextScroll.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        otherTextScroll.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        otherTextScroll.setMaximumSize(
            new java.awt.Dimension(HOR_SIZE, VER_SIZE));
        otherTextScroll.setMinimumSize(
            new java.awt.Dimension(HOR_SIZE, VER_SIZE));
        otherTextScroll.setPreferredSize(new java.awt.Dimension(
		    HOR_SIZE, VER_SIZE));
        otherText.setEditable(false);
               
        frame.getContentPane().add(otherTextScroll,
            java.awt.BorderLayout.CENTER);
            
        frame.pack();
        frame.setVisible(true);
    }

    private void textTyped(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (c == '\n'){
        	   try {
        		   if (firstMessage) { 
        		   	   name = textString;
        		       chatServer.join(displayChat,name);
        			   firstMessage = false;
        		   } else {
        		       chatServer.talk(name, textString);
        		   }
        	   }
        	   catch (Exception ie) {
        		   otherText.append("Failed to send message.");
        	   }
            textString = "";
        } else {
            textString = textString + c;
        }
    }
    

    public static void main(String args[]) {
    	new ChatClient();
    }

}