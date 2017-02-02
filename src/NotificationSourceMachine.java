import java.rmi.*;
import java.rmi.registry.LocateRegistry;


public class NotificationSourceMachine {
	public static void main(String args[]) {
		try {
			//create the registry this machine will use
			LocateRegistry.createRegistry(1099);
			//instantiate sources and bind them to their names
			NotificationSource source1 = new NotificationSource("Source1");
			Naming.rebind("rmi://localhost/"+source1.getName(), source1);
			System.out.println("Source Machine Ready: " + source1.getName());
			NotificationSource source2 = new NotificationSource("Source2");
			Naming.rebind("rmi://localhost/"+source2.getName(), source2);
			System.out.println("Source Machine Ready: " + source2.getName());
			NotificationSource source3 = new NotificationSource("Source3");
			Naming.rebind("rmi://localhost/"+source3.getName(), source3);
			System.out.println("Source Machine Ready: " + source3.getName());
			//wait 20 seconds for sinks to connect
			Thread.sleep(20000);
			//notify all the sinks for all the sources
			source1.notifySinks(new Notification("Source1", "Example Message"));	
			source2.notifySinks(new Notification("Source2", "Example Message"));	
			source3.notifySinks(new Notification("Source3", "Example Message"));	
		}
		catch (Exception e) {
			//print relevant error
			System.out.println(e);			
		} 
	} 

} 
