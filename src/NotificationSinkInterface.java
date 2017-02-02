import java.rmi.*;

public interface NotificationSinkInterface extends Remote {
	public String getName() throws RemoteException;
	public void receiveNotification(Notification notification) throws RemoteException;
}
