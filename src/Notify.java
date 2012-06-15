
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Notify extends Remote {

	public void joinMessage(String name) throws RemoteException;
	
    public void sendMessage(String name, String message) throws RemoteException;
    
    public void exitMessage(String name) throws RemoteException;
    
}