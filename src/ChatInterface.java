
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatInterface extends Remote {
    public void join(Notify n, String name) throws RemoteException;
    
    public void talk(String name, String s) throws RemoteException;
    
    public void leave(Notify n, String name) throws RemoteException;
}