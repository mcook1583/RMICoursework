import java.rmi.*;
import java.rmi.server.*;

public class NotificationSink extends UnicastRemoteObject implements NotificationSinkInterface {

	private String name;
	
	public NotificationSink(String name) throws RemoteException {
		super();
		this.name = name;
	}
	
	public String getName() throws RemoteException {
		return this.name;
	}

	public void receiveNotification(Notification notification) {
		//print out the name of the current sink and display the notification
		System.out.println(name + " received the message: \n\"" + notification.display()+"\"");
	}

}
