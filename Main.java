
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;


public class Main extends UnicastRemoteObject implements ChatInterface {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private ServerList serverList = new ServerList();
    
    public Main() throws RemoteException {
    }
    
    public void join(Notify n, String name) throws RemoteException {
		serverList.add(n);
		
		serverList.incCounter();
		for (Iterator i = serverList.getCollection().iterator();
				 i.hasNext();) {
			Notify client = (Notify)i.next();
			client.joinMessage(name);
		}
		serverList.decCounter();
    }
    
    public void talk(String name, String s)
            throws RemoteException {
		serverList.incCounter();
		for (Iterator i = serverList.getCollection().iterator();
				 i.hasNext();) {
			Notify client = (Notify)i.next();
			client.sendMessage(name,s);
		}
		serverList.decCounter();
    }
    
    public synchronized void leave(Notify n, String name) throws RemoteException {
    	serverList.remove(n);
    	
    	serverList.incCounter();
    	for (Iterator i = serverList.getCollection().iterator();
				 i.hasNext();) {
			Notify client = (Notify)i.next();
			client.exitMessage(name);
		}
		serverList.decCounter();
    }

    public static void main(String[] args) {
         	try {
			
			Main server = new Main();
			Naming.rebind("rmichat", server);
		
		}
		catch (java.net.MalformedURLException e) {
			System.out.println("Malformed URL for MessageServer name " + e.toString());
		}
		catch (RemoteException e) {
			System.out.println("Communication error " + e.toString());
		}
    }
    
}