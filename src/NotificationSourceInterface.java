import java.rmi.*;

public interface NotificationSourceInterface extends Remote {
	public void registerSink(NotificationSinkInterface sink) throws RemoteException;
	public void unregisterSink(NotificationSinkInterface sink) throws RemoteException;
	public void notifySinks(Notification notification) throws RemoteException;
}
