import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

public class NotificationSource extends UnicastRemoteObject implements NotificationSourceInterface {

	private String name;
	private ArrayList<NotificationSinkInterface> registeredSinks;

	public NotificationSource(String name) throws RemoteException {
		super();
		this.name = name;
		registeredSinks = new ArrayList<NotificationSinkInterface>();
	}
	
	public String getName(){
		return this.name;
	}

	public synchronized void registerSink(NotificationSinkInterface sink) throws RemoteException {
		// store the sink reference in the arraylist if it isn't already in it
		if (!(registeredSinks.contains(sink))) {
			registeredSinks.add(sink);
			System.out.println(name + " registered the sink: " + sink.getName());
		}
	}

	public synchronized void unregisterSink(NotificationSinkInterface sink) throws RemoteException {
		//check if sink is in the arraylist
		if (registeredSinks.contains(sink)) {
			//remove if it is in the arraylist
			registeredSinks.remove(sink);
			System.out.println("Unregistered sink.");
		} else {
			//print message if not
			System.out.println("Sink not registered in the first place.");
		}
	}

	public synchronized void notifySinks(Notification notification) throws RemoteException {
		// loop through registered sinks
		for (NotificationSinkInterface nextClient : registeredSinks) {
			// invoke the notification receive method on each sink with the notification
			nextClient.receiveNotification(notification);
		}
		System.out.println(name + " sent a notification to all of its sinks.");
	}

}
