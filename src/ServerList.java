import java.util.ArrayList;
import java.util.Collection;

class ServerList {
	
	private Collection<Notify> threadList = new ArrayList<Notify>();
	private int counter = 0;
	
	//  Get the lock on theadList, and wait until the counter is zero - that
	//is, no reads are taking place. Then it's safe to add the thread.
	
	public synchronized void add(Notify item) {
		try {
			while (counter > 0) {
				wait();
			}
			threadList.add(item);
	    }
	    catch (InterruptedException e) {
	    	System.out.println("Addition interrupted.");
	    }
	    finally{
	        notifyAll();
	    }
	}
	
	// Similarly for removal.
	public synchronized void remove(Notify item) {
	    try {
			while (counter > 0) {
				wait();
			}
			threadList.remove(item);
		}
	    catch (InterruptedException e) {
	    	System.out.println("Removal interrupted.");
	    }
	    finally {
	        notifyAll();
	    }
	}
	
	// Similarly for changing counter
	public synchronized void incCounter() {
		counter++;
		notifyAll();
	}
	
	public synchronized void decCounter() {
		counter--;
		notifyAll();
	}
	
	//This is because it would be too much effort to make this class implement
	//Collection, return it's own Iterator etc. etc...\
	//Note it is *not* a bug that it isn't synchronized
	public Collection<Notify> getCollection() {
		return threadList;
	}
}