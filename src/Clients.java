import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class Clients {

	public static void main(String args[]) {
		try {
			//connect to the same registry that the source(s) use
			LocateRegistry.getRegistry("localhost",1099);
			// find the source objects, casting them to their interface
			// and bind to their news channel names
			NotificationSourceInterface bbcnews = (NotificationSourceInterface) Naming.lookup("rmi://localhost/bbcnews");
			NotificationSourceInterface skynews = (NotificationSourceInterface) Naming.lookup("rmi://localhost/skynews");
			NotificationSourceInterface telegraph = (NotificationSourceInterface) Naming.lookup("rmi://localhost/telegraph");
			//instantiate sink objects for users using their names
			NotificationSink alan = new NotificationSink("Alan");
			NotificationSink brian = new NotificationSink("Brian");
			NotificationSink chris = new NotificationSink("Chris");
			NotificationSink daniel = new NotificationSink("Daniel");
			// register sinks each with various sources
			bbcnews.registerSink(alan);
			bbcnews.registerSink(brian);
			bbcnews.registerSink(daniel);
			skynews.registerSink(alan);
			skynews.registerSink(brian);
			skynews.registerSink(chris);
			telegraph.registerSink(brian);
			telegraph.registerSink(chris);
			telegraph.registerSink(daniel);
		}
		catch (Exception e) {
			System.out.println(e);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.out.println("Trying again...");
			main(args);
		} 
	}
}
