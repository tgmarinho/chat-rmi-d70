
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DisplayMessage extends UnicastRemoteObject implements Notify {

	private static final long serialVersionUID = -6415220281352521474L;
	private javax.swing.JTextArea textArea;
	private String name;

    public DisplayMessage(javax.swing.JTextArea ta)
        throws RemoteException {
    	    textArea = ta;
    }
    
    public void joinMessage(String name)
        throws RemoteException
    {
        try {
    	    textArea.append(name + " has joined\n");
    	}
        catch(Exception e){
            System.out.println("Message Failure");
            e.printStackTrace();
        };
    }
    
    public void sendMessage(String name, String message) throws RemoteException
    {
        try {
    	    textArea.append(name + " says: " + message + "\n");
    	}
        catch(Exception e){
            System.out.println("Message Failure");
            e.printStackTrace();
        };
    }
    
    public void exitMessage(String name) throws RemoteException {
        try {
    	    textArea.append(name + " has left the building.\n");
    	}
        catch(Exception e){
            System.out.println("Message Failure");
        };
    }
    
    //Notice this one not called remotely
    public void setName(String name) throws RemoteException {
    	this.name = name;
    }
    
    public String getName() {
    	return name;
    }
}
